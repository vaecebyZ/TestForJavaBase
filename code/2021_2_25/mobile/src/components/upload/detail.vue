<template>
  <div class="detail">
    <mHeader>笔记详情</mHeader>
    <div>
      <div class="user">
        <div class="left">
          <img src="/static/1.jpg" alt="">
          <span>sasasa</span>
        </div>
        <div class="right">
          <vshare></vshare>
        </div>
      </div>
    </div>
    <mt-swipe :aria-autocomplete="3000" indicator-color="white" class="swipe">
      <mt-swipe-item v-for="(item,index) in dict.attachment_rows_data" :key="index">
        <img class="tab" :src="$api.BASE_SERVER_URL+ item.attachmentPath" alt="">
      </mt-swipe-item>
    </mt-swipe>
    <div style="margin-bottom: 20px">
      <div class="content" v-html="dict.noteContent"></div>
      <div class="footer">
        <div class="left">
          <span class="tag" mark style="margin-right: 10px" type="danger" plain v-for="(item,index) in dict.label_rows_data"
                   :key="index">{{item.labelName}}
          </span>
        </div>
        <div class="right">
          <div class="item" @click.once="thumbs">
            <!-- <van-icon name="like-o"/> -->
             <img src="/static/img/like-1.png" style="float:left;height:1.2em;width:1.2em;" >
            <span>{{thumbNum}}</span>
          </div>
          <div class="item">
            <!-- <van-icon name="comment-o"/> -->
             <img src="/static/img/pinglun.png" style="float:left;height:1.2em;width:1.2em;" >
            <span>{{dict.commentaryNum}}</span>
          </div>
        </div>
      </div>
    </div>
    <div>
      <mt-cell class="mt-cell" :key="index" v-for="(item,index) in dict.interaction_rows_data" :title="nickName+' : ' +item.commentaryDescription" size="small"/>
    </div>
    <mt-field
      v-model="talk"
      class="input"
      clearable
      placeholder="请输入评论"
    >
      
    </mt-field>
    <mt-button slot="button" @click="send" size="small" type="primary">发送评论</mt-button>
  </div>
</template>
<script>
  import mHeader from '../other/header'

  export default {
    name: "detail",
    components: {mHeader},
    data () {
      return {
        talk: '',
        list: [1, 2, 3, 4, 5, 6],
        nickName: '',
        thumbUp: false,
        thumbNum: 0,
        comment: [
          {user: '单身小仙女', comment: '虽然这次没过，但考虫真的很棒'},
          {user: '单身小仙女', comment: '虽然这次没过，但考虫真的很棒'},
          {user: '单身小仙女', comment: '虽然这次没过，但考虫真的很棒'},
          {user: '单身小仙女', comment: '虽然这次没过，但考虫真的很棒'}
        ],
        html: [' <div data-v-54a5b46e="">\n' +
        '      <div data-v-5806d7a1="" class="content"><h1 data-v-5806d7a1="" class="as-p">科学增高法//30天长高2厘米/5个月长高8厘米😱</h1>\n' +
        '        <p data-v-5806d7a1="">\n' +
        '          相信每一位小姐姐都希望自己有双大长腿，穿衣打扮自信好看，可是难道说个子低的小姐姐只能穿过穿搭为了显腿长而费劲心思吗❓其实没有必要的，虽然说遗传对身高起了60%-70%的因素,但是剩下30%-40%可以通过后期饮食睡眠运动和其它干预突破长高的😙😙你去问每一个骨科医生他们都会告诉你，采用骨细微裂的方式是可以二次长高的，所以只要方法正确在配合一些铺助运动，期间营养跟得_上长高自然就不是问题<img\n' +
        '          data-v-5806d7a1="" src="//ci.xiaohongshu.com/xy_emo_h_chelian.png?v=2" class="shubaobao-expression"><img\n' +
        '          data-v-5806d7a1="" src="//ci.xiaohongshu.com/xy_emo_h_chelian.png?v=2" class="shubaobao-expression"></p>\n' +
        '        <p data-v-5806d7a1=""><img data-v-5806d7a1="" src="//ci.xiaohongshu.com/xy_emo_r_xieyan.png?v=2"\n' +
        '                                   class="shubaobao-expression">长高常识</p>\n' +
        '        <p data-v-5806d7a1="">人体骨骺线闭合后只有依靠骨微裂才能实现后天增高，而唤醒骨微裂只有两种方式：</p>\n' +
        '        <p data-v-5806d7a1="">1、通过关节骨骼剧烈运动，如反复跳跃高!</p>\n' +
        '        <p data-v-5806d7a1="">2、通过中药不断对穴位刺激其对应腿部部位持之以恒来实现增高。加快骨骼的新陈代谢，产生骨细微裂，(鄙人这里说的就是用第二种方式，可以让你轻松长高)</p>\n' +
        '        <p data-v-5806d7a1="">正确方法+辅助拉伸可以长得更快!可以让你实实在在的长骨头长身体永不反弹，长到多少就是多少</p>\n' +
        '        <p data-v-5806d7a1=""><img data-v-5806d7a1="" src="//ci.xiaohongshu.com/xy_emo_r_xieyan.png?v=2"\n' +
        '                                   class="shubaobao-expression">下面拉伸运动甩一波伸展运动</p>\n' +
        '        <p data-v-5806d7a1="">①颈部四项拉伸，上下左右都要拉伸到。</p>\n' +
        '        <p data-v-5806d7a1="">②收肩仰头，头向.上抬，手勾住伸直并尽可能向后，双腿与肩同宽，不要弯曲膝盖。</p>\n' +
        '        <p data-v-5806d7a1="">③拉伸腹肌和胸腔，驼背要舒展身体前侧。</p>\n' +
        '        <p data-v-5806d7a1="">④拉伸腿后侧，背部，肩膀，可以开肩矫正腿型。⑤靠墙拉伸手臂，延长手臂和上半身交界处6踮脚伸手，舒展四肢。</p>\n' +
        '        <p data-v-5806d7a1=""><img data-v-5806d7a1="" src="//ci.xiaohongshu.com/xy_emo_r_maibao.png?v=2"\n' +
        '                                   class="shubaobao-expression">饮食建议:</p>\n' +
        '        <p data-v-5806d7a1="">①补钙，蛋白质:鱼、虾、豆制品。</p>\n' +
        '        <p data-v-5806d7a1="">②少吃盐、糖、零食和防腐剂添加剂多的食物多吃蔬菜:白菜、胡萝卜、海带等。I4睡前最好喝一杯高钙奶!</p>\n' +
        '        <p data-v-5806d7a1=""><img data-v-5806d7a1="" src="//ci.xiaohongshu.com/xy_emo_r_xieyan.png?v=2"\n' +
        '                                   class="shubaobao-expression">生活建议:</p>\n' +
        '        <p data-v-5806d7a1="">①一定要早睡， 保持充足睡眠!</p>\n' +
        '        <p data-v-5806d7a1="">②平时多注意坐姿、站姿、驼背、不良体态会让你视觉上看起来更矮。</p>\n' +
        '        <p data-v-5806d7a1="">③多运动，做一些正确的运动也可以帮助你长高。④运动建议穿爆汗服，5分钟就能感觉身上的汗都滴下来了，瘦身效果事半功倍!</p>\n' +
        '        <p data-v-5806d7a1="">☝以上的拉伸动作，小姐姐们都可以赶紧学起来，等你长高了，你会发现你还可以更美勒💃💃💃</p></div>\n' +
        '    </div>'],
        dict: {}
      }
    },
    mounted () {
      this.$store.state.dict.forEach (val => {
        if (val.id == this.$route.params.id) this.dict = val
        //console.log (this.$route.params.id, val.id)
      })

      this.$post(this.$api.API_URL_CUSTOMER_BASIC, {
        id: this.$store.getters.user.id
      }).then (res => {
        this.nickName = res.data.nickname;
      })

      this.$post(this.$api.API_SET_SELECTCOUNT, {
        noteDataId: this.$route.params.id
      }).then (res => {
        this.thumbNum = res.data;
      })
    },
    methods: {
      send () {
        if (!this.$store.getters.user) this.$toast ('请先登录！')
        else this.$post (this.$api.API_sendComment, {
          noteDataId: this.$route.params.id,
          makeEmp: this.$store.getters.user.id,
          dataType: 3,
          commentaryDescription: this.talk
        }).then (res => {
          if (res.errorCode == '0') this.dict.interaction_rows_data.push ({commentaryDescription: this.talk})
          else this.$toast ('网路错误')
          this.talk = ''
        })
      },
      thumbs () {
        this.thumbUp = !this.thumbUp;
        if(this.thumbUp == true){
          this.thumbNum+=1;
        }else{
          this.thumbNum-=1;
        }
        this.$post (this.$api.API_sendComment, {
          noteDataId: this.$route.params.id,
          makeEmp: this.$store.getters.user.id,
          dataType: 1
        }).then (res => {
          
        })
      }
    },
    watch: {}
  }
</script>
<style scoped lang='stylus'>
  .user
    padding 10px 5px
    display flex
    font-size .6em
    justify-content space-between
    align-items center
    img
      width 20px
      height 20px
      border-radius 50%
      vertical-align middle
  .tab
    width 100%
    height 400px
    object-fit cover

  .content
    padding 10px
    font-size .8em

  .footer
    display flex
    justify-content space-between
    padding 10px

    .right
      width 40%
      display flex
      justify-content space-around

      i
        vertical-align sub

.swipe
  height 400px;
.mt-cell
  text-align left;
.tag
  border 1px solid red;
  color red;
  border-radius 5px;
  padding 1px;
  font-size 12px;

</style>

