<template>
  <div>
    <van-nav-bar
      title="Hi热榜"
      right-text="按钮"
      @click-right="onClickRight">
      <van-icon name="service-o" slot="right"/>
    </van-nav-bar>

    <van-tabs v-model="active" @click="onTabClisk" swipeable>
      <van-tab v-for="index in tanItem" :key="index" :title="index">
        <van-list
          v-model="loading"
          :finished="finished"
          finished-text="没有更多了"
          @load="onLoad"
        >
          <van-cell
            v-for="item in weiboHotList"
            :key="item"
            :title="item.title"
            :value="item.feature.hot"
          />
        </van-list>
      </van-tab>
    </van-tabs>


  </div>


</template>

<script>
  import {Toast} from "vant";
  import api from "../constant/api";

  export default {
    name: "Index",
    data() {
      return {
        weiboHotList: [],
        zhihuHotList:[],
        githubTrendList:[],
        tanItem: ['微博热门', '知乎热门', 'Github趋势'],
        loading: false,
        finished: false,
        active:0
      };
    },
    methods: {
      onClickLeft() {
        Toast('返回');
      },
      onClickRight() {
        Toast('按钮');
        this.onLoadWeiboHot()
      },
      onLoad() {
        // 异步更新数据

      },
      onTabClisk(name) {
        Toast(name)
      },
      onLoadWeiboHot() {
        this.axios.get(api.weibo_hot)
          .then(response => {
            console.log(response)
            let resCode = response.data.code
            if (resCode == 200) {
              console.log(response.data.data.list)
              this.list = response.data.data.list
              this.finished = true
            }

          })
      },
      onLoadZhihuHot() {
        this.axios.get(api.zhihu_hot)
          .then(response => {
            console.log(response)
            let resCode = response.data.code
            if (resCode == 200) {
              console.log(response.data.data.list)
              this.zhihuHotList = response.data.data.list
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
              this.githubTrendList = response.data.data.list
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

</style>
