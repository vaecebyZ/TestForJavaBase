<template>
  <div class="reg">
    <m-header>用户注册</m-header>
    <div class="container">
      <div class="cell">
         <div class="hezi">
         <img class="img" src="/static/img/dianhua.png" style="float:left;height:1.1em;width:1.1em;" >
          <mt-field label="电话号:" placeholder="请输入电话号码" type="tel" v-model="phone">
          </mt-field>
         <mt-button class="button" slot="button" size="small" @click="send" type="primary">发送验证码</mt-button>
        </div>
        

 <div class="hezi">
         <img class="img" src="/static/img/duanxin.png" style="float:left;height:1.1em;width:1.1em;" >
          <mt-field class="duanxin" label="短信验证码:" placeholder="请输入短信验证码" type="tel" v-model="sms">
          </mt-field>
        </div>
        
        <div class="hezi">
         <img class="img" src="/static/img/close-eye.png" style="float:left;height:1.1em;width:1.1em;" >
          <mt-field label="密码:" placeholder="请输入密码" type="password" v-model="userPassword">
          </mt-field>
        </div>
        
         <div class="hezi">
         <img class="img" src="/static/img/close-eye.png" style="float:left;height:1.1em;width:1.1em;" >
          <mt-field class="queren" label="确认密码:" placeholder="" type="password" v-model="rePassword">
          </mt-field>
        </div>
       
      </div>
      <mt-button @click="reg" block class="cell" large size="large" square type="primary">立即注册</mt-button>
    </div>
  </div>


</template>

<script>
  import header from './other/header'

  export default {
    data () {
      return {
        sms: '',
        userPassword: '',
        rePassword: '',
        phone: '',
        test: /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/
      }
    },
    components: {'m-header': header},
    methods: {
      reg () {
        if (!this.phone) this.$dialog.alert ({message: '请填写手机号❤'})
        else if (!this.userPassword) this.$dialog.alert ({message: '请填写密码❤'})
        else if (!this.test.test (this.phone)) this.$dialog.alert ({message: '请正确填写您的手机号码❤'})
        else if (this.phone.length > 11) this.$dialog.alert ({message: '手机号码必须为11位❤'})
        else if (!this.sms) this.$dialog.alert ({message: '请填写验证码❤'})
        else if (!this.userPassword) this.$dialog.alert ({message: '请填写密码❤'})
        else if (!this.rePassword) this.$dialog.alert ({message: '请重复密码❤'})
        else if (this.userPassword != this.rePassword) this.$dialog.alert ({message: '两次密码输入不一致❤'})
        else this.$post(this.$api.REGISTER, {userName: this.phone, pwd: this.userPassword, amount: 0}).then (res => {
            if (res.errorCode == '0') {
              this.$dialog.alert ({message: '注册成功'}).then (ree => {
                this.$router.push ({path: '/login'})
              })
            } else this.$dialog.alert ({message: '注册失败'})
          })
      },
      send () {
        if (!this.phone) this.$dialog.alert ({message: '请填写手机号❤'})
        else if (!this.test.test (this.phone)) this.$dialog.alert ({message: '请正确填写您的手机号码❤'})
        else if (this.phone.length > 11) this.$dialog.alert ({message: '手机号码必须为11位❤'})
        else this.$post(this.$api.API_URL_CUSTOMER, {userName: this.phone}).then (res => {
            if (res.errorCode == '-3') this.$post(this.$api.SEND_SMS, {userName: this.phone}).then (ret => {
              if (ret.errorCode == '0') this.$dialog.alert ({message: '短信已发送'}).then (() => this.sms = ret.data)
              else this.$dialog.alert ({message: '短信发送失败'})
            })
            else this.$dialog.alert ({message: '您已注册,请登录'})
          })
      }
    }
  }

</script>

<style scoped lang="stylus">
  .container
    margin 20vh 10px

    .cell
      margin-bottom 20px
.hezi
  position relative;
  top 0;
  left 0;
.button
     position absolute;
     right 0;
     top 10px;
.img
  position absolute;
  left 12px;
  top 15px;
  z-index  999;
.duanxin
  margin-left 17px;
.queren
  margin-left 6px;
</style>
