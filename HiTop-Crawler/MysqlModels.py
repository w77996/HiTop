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
        feature = {
            'hot': hot
        }
        if exist:
            print(key, feature)
            self.update_exist_hot(key, hot)
            return
        # weibo_hot_item = {
        #     'title': title,
        #     'url': url,
        #     'type': 1,
        #     'url_key': key,
        #     'feature': json.dumps(feature)
        # }
        weibo_hot_item = (title, url, '1', key, json.dumps(feature, ensure_ascii=False))
        print(str(weibo_hot_item))
        self.insert_mysql(weibo_hot_item)
        # insert_mongo(self.db.weibo_hot, weibo_hot_item)

    # 持久化知乎热榜
    def save_zhihu_hot(self, title, url, desc, hot):
        key = 'zhihu_hot_' + hashlib.md5(url.encode(encoding='UTF-8')).hexdigest()
        feature = {
            'desc': desc,
            'hot': hot
        }
        exist = self.check_url_exist(key)
        if exist:
            self.update_exist_desc_hot(key, desc, hot)
            return

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
    def save_github_trending(self, title, url, desc):
        key = 'github_trending_' + hashlib.md5(url.encode(encoding='UTF-8')).hexdigest()
        exist = self.check_url_exist(key)
        if desc is None:
            feature = None
        else:
            feature = {
                'desc': desc,
            }
        if exist and feature is not None:
            self.update_exist_desc(key, desc)
            return
        # github_trending_item = {
        #     'title': title,
        #     'url': url,
        #     'type': 3,
        #     'url_key': 'github_trending_' + time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()) + '_' + hashlib.md5(
        #         url.encode(encoding='UTF-8')).hexdigest()
        # }
        github_trending_item = (title, url, 3, key, json.dumps(feature, ensure_ascii=False))
        print(str(github_trending_item))
        self.insert_mysql(github_trending_item)
        # insert_mongo(self.db.github_trending, github_trending_item)

    def save_weixin(self):
        print(self)

    def check_url_exist(self, key):
        cur = self.conn.cursor()
        rows = cur.execute('select * from t_top where url_key = %s', (key))
        cur.close()
        if rows == 0:
            return False
        return True

    def update_exist_desc_hot(self, key, desc, hot):
        cur = self.conn.cursor()
        cur.execute('update t_top set feature = json_set(feature,\'$.desc\',%s,\'$.hot\',%s) where url_key = %s',(desc, hot, key))
        cur.close()

    def update_exist_hot(self, key, hot):
        cur = self.conn.cursor()
        cur.execute('update t_top set feature = json_set(feature,\'$.hot\',%s) where url_key = %s', (hot, key))
        cur.close()

    def update_exist_desc(self, key, desc):
        cur = self.conn.cursor()
        cur.execute('update t_top set feature = json_set(feature,\'$.desc\',%s) where url_key = %s', (desc, key))
        cur.close()

    def insert_mysql(self, data):
        cur = self.conn.cursor()
        cur.execute('insert into t_top(title, url ,type ,url_key, feature) values (%s,%s,%s,%s,%s)', data)
        self.conn.commit()
        cur.close()
