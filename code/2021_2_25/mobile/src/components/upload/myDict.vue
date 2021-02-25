<template>
  <div class="dict">
    <mHeader>我的动态</mHeader>

    <div class="container" infinite-scroll-disabled="loading" infinite-scroll-distance="10"
         v-infinite-scroll="loadMore">
      <div class="bg"></div>
      <div class="col" style="margin-right: 10px">
        <div @click="nav(item.id)" class="item" v-for="item of img1">
          <img :src="$api.BASE_SERVER_URL+ item.attachment_rows_data[0].attachmentPath" alt="" class="img">
          <div class="desc">{{item.noteContent}}</div>
          <div class="user">
            <div class="left">
              <img alt="" src="/static/1.jpg">
              <span>{{$store.getters.user.userName}}</span>
            </div>
            <div class="right">
              <!-- <van-icon name="like-o"/> -->
               <img src="/static/img/like-1.png" style="float:left;height:1.2em;width:1.2em;" >
              <span>{{item.praiseNum}}</span>
            </div>
          </div>
        </div>
      </div>
      <div class="col">
        <div @click="nav(item.id)" class="item" v-for="item of img2">
          <img :src="$api.BASE_SERVER_URL+ item.attachment_rows_data[0].attachmentPath" alt="" class="img">
          <div class="desc">{{item.noteContent}}</div>
          <div class="user">
            <div class="left">
              <img alt="" src="/static/1.jpg">
              <span>{{$store.getters.user.userName}}</span>
            </div>
            <div class="right">
              <!-- <van-icon name="like-o"/> -->
               <img src="/static/img/like-1.png" style="float:left;height:1.2em;width:1.2em;" >
              <span>{{item.praiseNum}}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang='stylus'>
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

<script>
  import mHeader from '../other/header'

  export default {
    name: "myDict",
    components: {mHeader},
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
            datType: 2,
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
    mounted () {
      this.$post (this.$api.API_getDict, {
        dataType: 1,
        makeEmp: this.$store.getters.user.id,
        nowPage: this.count
      }).then (res => {
        this.pageCount = res.data.pageCount
        this.$store.commit ('setDict', res.data.rows_data)
        res.data.rows_data.forEach ((val, index) => {
          if (index % 2) this.img2.push (val)
          else this.img1.push (val)
        })
      })
    },
  }
</script>
