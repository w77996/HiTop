from pymongo import MongoClient
import time
import hashlib


def insert_mongo(collections, data):
    # try:
    result = collections.insert(data)
    print('enter' + str(result) + str(data))


# exec


class DataModels:

    def __init__(self):
        # 初始化mongo连接
        self.client = MongoClient('mongodb://admin:test@116.62.144.245:27017/')
        self.db = self.client.admin
        print(self)

    # 持久化微博热搜
    def save_weibo_hot(self, title, url, hot):
        weibo_hot_item = {
            'title': title,
            'url': url,
            'hot': hot,
            # 'create_time': time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
            'create_time': time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
        }
        print(str(weibo_hot_item))
        # insert_mongo(self.db.weibo_hot, weibo_hot_item)

    # 持久化知乎热榜
    def save_zhihu_hot(self, title, url, desc, hot):
        zhihu_hot_item = {
            'title': title,
            'url': url,
            'desc': desc,
            'hot': hot,
            'create_time': time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
        }
        print(str(zhihu_hot_item))
        # insert_mongo(self.db.zhihu_hot, zhihu_hot_item)

    # 持久化Github热榜
    def save_github_trending(self, title, url):
        github_trending_item = {
            'title': title,
            'url': url,
            'key': 'github_trending_' + time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()) + '_' + hashlib.md5(
                url.encode(encoding='UTF-8')).hexdigest(),
            'create_time': time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
        }
        print(str(github_trending_item))
        # insert_mongo(self.db.github_trending, github_trending_item)

    def save_weixin(self):
        print(self)
