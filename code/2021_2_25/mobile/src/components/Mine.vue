<template>
  <div class="mine">
    <mHeader>我的</mHeader>
    <mt-cell style="margin-bottom: 30px" v-if="$store.getters.user">
      <template slot="icon">
        <img src="/static/1.jpg" style="float:left;height:5em;width:5em;border-radius: 50%">
      </template>
      <template slot="title">
        <div class="" style="text-align: right;top:-10px;position:relative">
          <p>{{this.name}}</p>
          <p>{{$store.getters.user.userName}}</p>
        </div>
      </template>
    </mt-cell>
    <mt-cell :key="index" :title="item.title" :to="item.url" is-link v-for="(item,index) of row">
      <template slot="icon">
        <img :src="item.icon" style="float:left;height:1em;width:1em">
      </template>
    </mt-cell>
    
    <mt-cell @click.native="setUser" icon="/static/img/out.png" is-link title="退出" to="/login">
       <template slot="icon">
        <img src="/static/img/out.png" style="float:left;height:1em;width:1em">
      </template>
    </mt-cell>

    <h3 style="margin-top:20%">Web应用软件开发平台V1.0</h3>
  </div>
</template>


<script>
  import mHeader from './other/header'

  export default {
    components: {mHeader},
    data () {
      return {
        row: [{
          title: '订单历史',
          icon: '../../static/img/history.png',
          url: '/samsara/history'
        }, {
          title: '常用联系人',
          icon: '../../static/img/2.png',
          url: '/samsara/concat'
        }, {
          title: '我的收藏',
          icon: '../../static/img/collect.png',
          url: '/samsara/collect'
        }, {
          title: '我的动态',
          icon: '../../static/img/message.png',
          url: '/samsara/myDict'
        }, {
          title: '会员设置',
          icon: '../../static/img/4.png',
          url: '/samsara/setting'
        },{
          title: '未支付订单',
          icon: '../../static/img/payment.png',
          url: '/samsara/payment'
        },{
          title: '电子钱包充值',
          icon: '../../static/img/account.png',
          url: '/samsara/wallet'
        }],
        user: '',
        name: '',
      }
    },
    mounted () {
      this.$post(this.$api.API_URL_CUSTOMER_BASIC, {
        id: this.$store.getters.user.id
      }).then (res => {
        this.name = res.data.nickname;
      })
    },
    methods: {
      setUser() {
        this.$store.commit('setUser',null);
        this.Storage.Local.clear();
      }
    }
  }

</script>

<style scoped>

</style>
