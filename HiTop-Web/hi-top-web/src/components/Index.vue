<template>
  <div>
    <van-nav-bar
      title="Hi热榜"
      right-text="按钮"
      @click-right="onClickRight">
      <van-icon name="search" slot="right"/>
    </van-nav-bar>

    <van-tabs v-model="tabActive" @change="onTabChange" swipeable sticky>
      <van-tab v-for="index in tanItem" :key="index" :title="index">
        <van-notice-bar
          color="#1989fa"
          background="#ecf9ff"
          left-icon="info-o"
        >
          通知内容
        </van-notice-bar>
        <van-list
          v-model="loading"
          :finished="finished"
          finished-text="-- 我到底部啦--"
        >
          <van-cell
            v-for="(item,index) in dataList"
            v-if="item.type == 1"
            :key="item.id"
            :title="(index+1) +'.'+item.title"
            :value="item.feature.hot"
            :url="item.url"
            title-class="title-class"
            clickable
            is-link>
          </van-cell>
          <van-cell
            v-for="(item,index) in dataList"
            v-if="item.type == 2"
            :key="item.id"
            :title="(index+1) +'.'+item.title"
            :value="item.feature.hot"
            :label="item.feature.desc"
            title-class="title-class"
            label-class="label-class"
            @click="onCellClick(item)"
            style="min-width:70%"
            center
            is-link
          />
          <van-cell
            center
            v-for="item in dataList"
            v-if="item.type == 3"
            :key="item.id"
            :title="item.title"
            @click="onCellClick(item)"
            is-link
          />
        </van-list>
      </van-tab>
    </van-tabs>


  </div>


</template>

<script>
  import {Toast} from "vant";
  import api from "../constant/api";
  import utils from "../utils/util";

  export default {
    name: "Index",
    data() {
      return {
        dataList: [],
        tanItem: ['微博热门', '知乎热门', 'Github趋势'],
        loading: false,
        finished: false,
        tabActive: 0
      };
    },
    methods: {
      onClickRight() {
        Toast('按钮');
        Toast(this.active)
      },
      onLoad() {
        // 异步更新数据

      },
      onCellClick(event) {

        window.location.href = event.url
        console.log(event)
      },
      onTabChange(index) {
        if (index == 0) {
          this.loading = true
          this.onLoadWeiboHot()
          this.loading = false;
        } else if (index == 1) {
          this.loading = true
          this.onLoadZhihuHot()
          this.loading = false
        } else if (index == 2) {
          this.loading = true
          this.onLoadGithubTrend()
          this.loading = false
        }
      },
      onLoadWeiboHot() {
        this.axios.get(api.weibo_hot)
          .then(response => {
            console.log(response)
            let resCode = response.data.code
            if (resCode == 200) {
              console.log(response.data.data.list)
              this.dataList = response.data.data.list
              this.finished = true
            }

          }).catch(error => {
              console.log(error)
          });
      },
      onLoadZhihuHot() {
        this.axios.get(api.zhihu_hot)
          .then(response => {
            console.log(response)
            let resCode = response.data.code
            if (resCode == 200) {
              console.log(response.data.data.list)
              this.dataList = response.data.data.list
              this.finished = true
            }

          })
      },
      onLoadGithubTrend() {
        this.axios.get(api.github_trend)
          .then(response => {
            console.log(response)
            let resCode = response.data.code
            if (resCode == 200) {
              console.log(response.data.data.list)
              this.dataList = response.data.data.list
              this.finished = true
            }
          })
      }
    },
    mounted() {
      this.onLoadWeiboHot()
    },
    created() {

    }

  }
</script>

<style scoped>
  .title-class {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    min-width: 70%
  }

  .label-class {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .tab {
    position: fixed;
  }
</style>
