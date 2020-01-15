from lxml import etree
from urllib import parse
import aiohttp
import asyncio
from concurrent.futures import ThreadPoolExecutor
from models import DataModels

thread_pool = ThreadPoolExecutor(100)


class CrawlHotData:

    def __init__(self):
        # 爬虫header
        self.headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36',
        }
        self.data_models = DataModels()

    # 爬取微博热门
    async def get_weibo_host(self, url):
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
            hot = item.xpath('td[2]/span/text()')
            print(title, url, hot)
            thread_pool.submit(self.data_models.save_weibo(str(title), str(url), str(hot)))


if __name__ == '__main__':
    self = CrawlHotData()
    loop = asyncio.get_event_loop()
    task = [self.get_weibo_host('https://s.weibo.com/top/summary')]
    # CrawlHotData.get_weibo_host(self, 'https://s.weibo.com/top/summary')
    loop.run_until_complete(asyncio.gather(*task))
    loop.close()
