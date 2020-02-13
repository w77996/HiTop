from apscheduler.schedulers.blocking import BlockingScheduler
from crawler import CrawlHotData
import asyncio


def excute_crawler():
    print('4444')
    crawl_hot_data = CrawlHotData()
    # loop = asyncio.get_event_loop()
    loop = asyncio.new_event_loop()
    asyncio.set_event_loop(loop)
    task = [crawl_hot_data.get_github_hot('https://github.com/trending')]
    # # CrawlHotData.get_weibo_host(self, 'https://s.weibo.com/top/summary')
    loop.run_until_complete(asyncio.gather(*task))
    loop.close()


if __name__ == '__main__':
    scheduler = BlockingScheduler()

    scheduler.add_job(excute_crawler, 'interval',  seconds=50)
    scheduler.start()
