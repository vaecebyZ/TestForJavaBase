<template>
  <div class="index">
    <van-search
      placeholder="请输入搜索关键词"
      show-action
      v-model="value">
      <div @click="$router.push('/samsara/search/'+value)" slot="action">搜索</div>
    </van-search>

    <div class="container" infinite-scroll-disabled="loading" infinite-scroll-distance="10"
         v-infinite-scroll="loadMore">
      <div class="bg"></div>
      <div class="col" style="margin-right: 10px">
        <div @click="nav(item.id)" class="item" v-for="item of img1">
          <img :src="$api.BASE_SERVER_URL+item.attachment_rows_data[0].attachmentPath" alt="" class="img">
          <div class="desc">{{item.noteContent}}</div>
          <div class="user">
            <div class="left">
              <img alt="" src="static/1.jpg">
              <span>{{item.userName}}</span>
            </div>
            <div class="right">
              <van-icon name="like-o"/>
              <span>{{item.praiseNum}}</span>
            </div>
          </div>
        </div>
      </div>
      <div class="col">
        <div @click="nav(item.id)" class="item" v-for="item of img2">
          <img :src="$api.BASE_SERVER_URL+item.attachment_rows_data[0].attachmentPath" alt="" class="img">
          <div class="desc">{{item.noteContent}}</div>
          <div class="user">
            <div class="left">
              <img alt="" src="static/1.jpg">
              <span>{{item.userName}}</span>
            </div>
            <div class="right">
              <van-icon name="like-o"/>
              <span>{{item.praiseNum}}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Index',
    data () {
      return {
        img1: [],
        img2: [],
        value: '',
        content: [],
        count: 1,
        pageCount: 0
      }
    },
    props: {},
    methods: {
      nav (id) {
        this.$router.push (`/samsara/indexdetail/${id}`)
      },
      loadMore () {
        if (this.count <= this.pageCount) {
          this.count = this.count + 1
          this.$post (this.$api.API_getDict, {
            dataType: 2,
            makeEmp: this.$store.getters.user.id,
            nowPage: this.count
          }).then (res => {
            this.$store.commit ('addDict', res.data.rows_data)
            res.data.rows_data.forEach ((val, index) => {
              if (index % 2) this.img2.push (val)
              else this.img1.push (val)
            })
          })
        } else return 0
      }
    },
    components: {},
    mounted () {
      this.$post (this.$api.API_getDict, {
        dataType: 2,
        nowPage: this.count
      }).then (res => {
        this.pageCount = res.data.pageCount
        this.$store.commit ('setDict', res.data.rows_data)
        res.data.rows_data.forEach ((val, index) => {
          if (index % 2) this.img2.push (val)
          else this.img1.push (val);
        })
      })
    },
    beforeDestroy () {
    }
  }
</script>

<style lang='stylus' scoped>
  #body {
    height: 93vh;
    overflow: auto;
  }

  .search
    height auto

  .container
    display flex
    justify-content space-between
    padding 10px

    .bg
      width 100vw
      height 100vh
      position fixed
      top 0
      background #e7eef5
      z-index -1
      left 0

    .col
      display flex
      flex-direction column
      flex 1

      .item
        border-radius 10px
        margin-bottom 10px
        background white

        .img
          width 100%
          border-radius 10px 10px 0 0

        .desc
          font-size .8em
          padding 2px 5px
          display: -webkit-box
          -webkit-box-orient vertical
          -webkit-line-clamp 2
          overflow hidden

        .user
          padding 0 5px 5px
          display flex
          font-size .6em
          justify-content space-between
          align-items center

          img
            width 20px
            height 20px
            border-radius 50%
            vertical-align middle

</style>
