import time
import hashlib
import pymysql
import json


def insert_mysql(collections, data):
    # try:
    result = collections.insert(data)
    print('enter' + str(result) + str(data))


# exec


class DataModels:

    def __init__(self):
        # 初始化mysql连接
        self.conn = pymysql.connect(host='116.62.144.245', user='root', password='1047239335', database='hi-top',
                                    charset='utf8')

    # 持久化微博热搜
    def save_weibo_hot(self, title, url, hot):
        key = 'weibo_hot_' + hashlib.md5(url.encode(encoding='UTF-8')).hexdigest()
        exist = self.check_url_exist(key)
        if exist:
            return
        feature = {
            'hot': hot
        }
        # weibo_hot_item = {
        #     'title': title,
        #     'url': url,
        #     'type': 1,
        #     'url_key': key,
        #     'feature': json.dumps(feature)
        # }
        weibo_hot_item = (title, url, '1', key, json.dumps(feature,ensure_ascii=False))
        print(str(weibo_hot_item))
        self.insert_mysql(weibo_hot_item)
        # insert_mongo(self.db.weibo_hot, weibo_hot_item)

    # 持久化知乎热榜
    def save_zhihu_hot(self, title, url, desc, hot):
        key = 'zhihu_hot_' + hashlib.md5(url.encode(encoding='UTF-8')).hexdigest()
        exist = self.check_url_exist(key)
        if exist:
            return
        feature = {
            'desc': desc,
            'hot': hot
        }
        # zhihu_hot_item = {
        #     'title': title,
        #     'url': url,
        #     'type': 2,
        #     'url_key': key,
        #     'future': json.dumps(feature)
        # }
        zhihu_hot_item = (title, url, '2', key, json.dumps(feature, ensure_ascii=False))
        print(str(zhihu_hot_item))
        self.insert_mysql(zhihu_hot_item)
        # insert_mongo(self.db.zhihu_hot, zhihu_hot_item)

    # 持久化Github热榜
    def save_github_trending(self, title, url):
        key = 'github_trending_' + hashlib.md5(url.encode(encoding='UTF-8')).hexdigest()
        exist = self.check_url_exist(key)
        if exist:
            return
        # github_trending_item = {
        #     'title': title,
        #     'url': url,
        #     'type': 3,
        #     'url_key': 'github_trending_' + time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()) + '_' + hashlib.md5(
        #         url.encode(encoding='UTF-8')).hexdigest()
        # }
        github_trending_item = (title, url, 3, key, None)
        print(str(github_trending_item))
        self.insert_mysql(github_trending_item)
        # insert_mongo(self.db.github_trending, github_trending_item)

    def save_weixin(self):
        print(self)

    def check_url_exist(self, key):
        cur = self.conn.cursor()
        rows = cur.execute('select * from top where url_key = %s', (key))
        cur.close()
        if rows == 0:
            return False
        return True

    def insert_mysql(self, data):
        cur = self.conn.cursor()
        cur.execute('insert into top(title, url ,type ,url_key, feature) values (%s,%s,%s,%s,%s)', data)
        self.conn.commit()
        cur.close()
