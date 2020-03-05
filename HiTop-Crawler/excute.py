from apscheduler.schedulers.blocking import BlockingScheduler
from crawler import CrawlHotData
import asyncio


def excute_crawler():
    print('4444')
    crawl_hot_data = CrawlHotData()
    # loop = asyncio.get_event_loop()
    loop = asyncio.new_event_loop()
    asyncio.set_event_loop(loop)
    # task = [crawl_hot_data.get_github_hot('https://github.com/trending')]
    # task = [crawl_hot_data.get_weibo_hot('https://s.weibo.com/top/summary')]
    # task = [crawl_hot_data.get_zhihu_hot('https://www.zhihu.com/hot')]
    task = [crawl_hot_data.get_weibo_hot('https://s.weibo.com/top/summary'),
            crawl_hot_data.get_zhihu_hot('https://www.zhihu.com/hot'),
            crawl_hot_data.get_github_hot('https://github.com/trending')]
    # CrawlHotData.get_weibo_host(self, 'https://s.weibo.com/top/summary')
    loop.run_until_complete(asyncio.gather(*task))
    loop.close()


if __name__ == '__main__':
    print("开始执行定时器")
    # excute_crawler()
    scheduler = BlockingScheduler()
    #
    scheduler.add_job(excute_crawler, 'cron',  hour='0-23')
    scheduler.start()
