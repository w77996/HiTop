from lxml import etree
from urllib import parse
import aiohttp
import asyncio
from concurrent.futures import ThreadPoolExecutor
from models import DataModels
import re

thread_pool = ThreadPoolExecutor(100)


class CrawlHotData:

    def __init__(self):
        # 爬虫header
        self.headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36',
        }
        self.data_models = DataModels()

    # 爬取微博热门
    async def get_weibo_hot(self, url):
        async with aiohttp.ClientSession(headers=self.headers) as session:
            async with session.get(url) as response:
                if response.status == 200:
                    result_html = etree.HTML(await response.text())
                else:
                    print('获取{}失败'.format(url))
                    result_html = None

        items = result_html.xpath('//div[@class="data"]/table/tbody/tr')
        for item in items:
            title = item.xpath('td[2]/a/text()')[0]
            url = parse.urljoin('https://s.weibo.com', item.xpath('td[2]/a/@href')[0])
            if re.match('http', url) is None:
                continue
            try:
                hot = item.xpath('td[2]/span/text()')[0]
            except:
                hot = 0
            # print(title, url, hot)
            thread_pool.submit(self.data_models.save_weibo_hot(str(title), str(url), str(hot)))

    # 爬取知乎热门
    async def get_zhihu_hot(self, url):
        self.headers[
            'cookie'] = '_zap=d3b85524-608a-40a0-a0c8-6cb65d433f06; d_c0="ACAhXCO-BRCPTl_RLKNCOS6yneaLpF29q4E=|1568043294"; tgw_l7_route=f2979fdd289e2265b2f12e4f4a478330; _xsrf=cbc8c703-c14e-4a2f-8e4d-3d80c4da82da; capsion_ticket="2|1:0|10:1568095339|14:capsion_ticket|44:Zjg4ZmRjMmY2ZTJjNDVkNTgwMWNiNjA1NjU5NTYzMzA=|506899d0030a455c56324b2bdf2592270dce960f4187dd6111662ee61824fb12"; l_n_c=1; r_cap_id="MmM4NjBkNTI0YTc4NGFmNGFiM2FjYzUwY2RkMmE0ZmQ=|1568095343|54d1cfb6dc5004e521366794171e1b01c584ef6b"; cap_id="MTI2MTIwNThhNWRlNGI1YzhjMzU5ZDEzZTQxZTJiMzg=|1568095343|2d87297fbb65212e7c20c6de9b35e64bce784945"; l_cap_id="ODI1N2Y0YjVhMDViNDI1Mjk2ZTkyNTY1M2RlMGYxZTQ=|1568095343|82490ce4fb492327ee04ba45315ec8906b136728"; n_c=1; z_c0=Mi4xQ09MUkNRQUFBQUFBSUNGY0k3NEZFQmNBQUFCaEFsVk5lb3BrWGdENlJUT1Vwc3BIdkgzSVhXN0pGUExPVlRvZU1R|1568095354|fc1d394d3725d0934fc9fd6b606932e1bb7ad659; tshl=; tst=h; unlock_ticket="AJBihgA5qw0XAAAAYQJVTYVDd10T-ZK-VwMEje_V-X8D4kfV2fn6TA=="'
        async with aiohttp.ClientSession(headers=self.headers) as session:
            async with session.get(url) as response:
                if response.status == 200:
                    result_html = etree.HTML(await response.text())
                else:
                    print('获取{}失败', url)
                    result_html = None
            items = result_html.xpath('//div[@class="HotList-list"]/section')
            for item in items:
                title = item.xpath('div[2]/a/h2/text()')[0]
                desc = item.xpath('div[2]/a/p/text()')
                url = item.xpath('div[2]/a/@href')[0]
                hot = item.xpath('div[2]/div/text()')[0]
                if desc:
                    desc = desc[0].strip()
                else:
                    #desc = ''
                    continue
                print(title, url, desc, hot)
                thread_pool.submit(self.data_models.save_zhihu_hot(title=str(title), url=str(url), desc=str(desc), hot=str(hot)))

    # 爬取Github热门
    async def get_github_hot(self, url):
        async with aiohttp.ClientSession(headers=self.headers) as session:
            async with session.get(url) as response:
                if response.status == 200:
                    result_html = etree.HTML(await response.text())
                else:
                    print('获取{}失败', url)
                    result_html = None
            items = result_html.xpath('//article[@class="Box-row"]')
            for item in items:
                title = item.xpath('h1/a/@href')[0].strip()[1:]

                url = item.xpath('h1/a/@href')[0].strip()
                try:
                    content = item.xpath('p[contains(@class,"col-9")]/text()')[0].strip()
                except:
                    content = ''
                if title and url:
                    url = parse.urljoin('https://github.com/', url)
                print(title, url, content)
                thread_pool.submit(self.data_models.save_github_trending(title=str(title), url=str(url)))
                    # await Hot.addHot(title=str(title), url=str(url), block='Github', content=content)


if __name__ == '__main__':
    self = CrawlHotData()
    loop = asyncio.get_event_loop()
    task = [self.get_weibo_hot('https://s.weibo.com/top/summary'),self.get_zhihu_hot('https://www.zhihu.com/hot'),self.get_github_hot('https://github.com/trending')]
    # task = [self.get_weibo_hot('https://s.weibo.com/top/summary')]
    # task = [self.get_zhihu_hot('https://www.zhihu.com/hot')]
    # task = [self.get_github_hot('https://github.com/trending')]
    # CrawlHotData.get_weibo_host(self, 'https://s.weibo.com/top/summary')
    loop.run_until_complete(asyncio.gather(*task))
    loop.close()
