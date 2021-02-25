<template>
  <div class="div">
    <mHeader>钱包充值</mHeader>
    <div class="notice">
       <img src="/static/img/tongzhi.png" style="float:left;height:1.3em;width:1.3em;" >
        <span>当前电子钱包账户余额为：￥{{amount}}</span>
    </div>
    <div style="margin-top:1px;text-align: left">
      <mt-cell title="电子钱包充值"/>
      <mt-field
        label="充值金额"
        placeholder="充值金额"
        required
        type="number"
        v-model="money"
      />
    </div>
    <mt-button @click="charge" block class="cell" plain size="large" square style="margin-top: 20px"
                type="danger">确认充值
    </mt-button>

<mt-field label="ID" v-model="text" readonly>
  充值时间
</mt-field>
<div v-for="item in info" :key="item.id">
    <mt-field :label="item.id" v-model="item.amount" readonly>
       <slot  style="margin-top:1px;text-align: left" >{{item.updTime}}</slot> 
    </mt-field>
</div>

  </div>
</template>

<script>
  import mHeader from './header'

  export default {
    data () {
      return {
        amount: null,
        money: 0,
        info:[],
        text:"充值金额"
      }
    },
    methods: {
      charge () {
        if (this.money > 0)
          this.$post(this.$api.API_URL_CUSTOMER_Recharge_Account, {
            userId: this.$store.getters.user.id,
            amount: this.money
          }).then (res => {
            if (res.errorCode == 0) {
              this.$dialog.alert ({message: '充值成功'})
              this.getBalance ()
              this.money = 0
            } else this.$dialog.alert ({message: '网络超时！'})
          })
        else this.$dialog.alert ({message: '请输入大于0的值'})
      },
      getBalance () {
        this.$post(this.$api.API_URL_CUSTOMER_Account, {
          userId: this.$store.getters.user.id
        }).then (res => {
          if (res.errorCode == 0) {
            this.amount = res.data.amount;
            this.$post("http://127.0.0.1:8088/Shopping/api/v1/account/getAccountInfo",{
              userName : this.$store.getters.user.userName
            }).then (res=>{
              this.info = res.data
            })
          } else {
            this.$dialog.alert ({message: '网络超时！'})
          }
        })
      }
    },
    created () {
      this.getBalance ()

    },
    components: {mHeader}
  }

</script>

<style lang="stylus" scoped>

.notice
  text-align left;
  padding 5px 20px;
  box-sizing border-box; 
  background-color #fffbe8;
  color #ed6a0c;
  font-size 14px;
  img 
    margin-right 5px;
  
</style>
