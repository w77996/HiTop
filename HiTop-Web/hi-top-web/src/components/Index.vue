<template>
  <div>
    <van-nav-bar
      title="Hi热榜"
      right-text="按钮"
      @click-right="onClickRight">
      <van-icon name="service-o" slot="right"/>
    </van-nav-bar>
    <van-list
      v-model="loading"
      :finished="finished"
      finished-text="没有更多了"
      @load="onLoad"
    >
      <van-tabs swipeable>
        <van-tab v-for="index in tanItem" :key="index" :title="index">
          <van-cell
            v-for="item in list"
            :key="item"
            :title="item.title"
            :value="item.feature"

          />
        </van-tab>
      </van-tabs>

    </van-list>
  </div>


</template>

<script>
  import {Toast} from "vant";
  import api from "../constant/api";

  export default {
    name: "Index",
    data() {
      return {
        list: [],
        tanItem: ['微博热门','知乎热门','Github趋势'],
        loading: false,
        finished: false
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
        setTimeout(() => {
          for (let i = 0; i < 10; i++) {
            this.list.push(this.list.length + 1);
          }
          // 加载状态结束
          this.loading = false;

          // 数据全部加载完成
          if (this.list.length >= 40) {
            this.finished = true;
          }
        }, 500);
      },
      onLoadWeiboHot() {
        this.axios.get(api.weibo_hot)
          .then(response => {
            console.log(response)
            let resCode = response.data.code
            if(resCode == 200){
              console.log(response.data.data.list)
              this.list = response.data.data.list
            }

          })
      },
      onLoadZhihuHot() {

      },
      onLoadGithubTrend() {

      }

    },
    mounted(){
      this.onLoadWeiboHot()
    },
    created(){

    }

  }
</script>

<style scoped>

</style>
