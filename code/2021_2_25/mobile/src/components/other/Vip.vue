<template>
  <div class="div">
    <mHeader>会员设置</mHeader>
    <div style="margin-top: 20px;text-align: left">
      <mt-cell title="完善基本信息"/>
      <mt-field
        label="昵称"
        placeholder="请输入昵称"
        required
        type="text"
        v-model="name"/>
      <mt-field
        label="Email"
        placeholder="请输入邮箱"
        required
        type="text"
        v-model="email"/>
    </div>
    <mt-button @click="updateUser" block class="cell" plain size="large" square style="margin-top: 20px"
                type="primary">修改用户信息
    </mt-button>

    <div style="margin-top: 20px;text-align: left">
      <mt-cell title="密码修改"/>
      <mt-field
        label="原始密码"
        placeholder="请输入密码"
        required
        type="text"
        v-model="pass_"
      />
      <mt-field
        label="新密码"
        placeholder="请输入密码"
        required
        type="password"
        v-model="pass__"
      />
      <mt-field
        label="确认密码"
        placeholder="请输入密码"
        required
        type="password"
        v-model="pass___"
      />
    </div>
    <mt-button @click="updatePass" block class="cell" plain size="large" square style="margin-top: 20px"
                type="primary">修改密码
    </mt-button>

  </div>
</template>

<script>
  import mHeader from './header'

  export default {
    name: 'setting',
    components: {mHeader},
    data () {
      return {
        name: '',
        email: '',
        pass_: '',
        pass__: '',
        pass___: ''
      }
    },
    methods: {
      updateUser () {
        var vm = this;
       
        this.$post(this.$api.API_URL_UPDATE_CUSTOMER, {
          id: this.$store.getters.user.id,
          nickname: this.name,
          email: this.email
        }).then (res => {
          if (res.errorCode == 0) {
            this.$dialog.alert ({message: '修改成功'});
          } else {
            this.$dialog.alert ({message: '网络超时！'})
          }
        })

      },
      updatePass () {
        if (this.pass__ == this.pass___ && this.pass__.length)
          this.$post(this.$api.API_URL_CHANGE_PASSWORD, {
            id: this.$store.getters.user.id,
            oldpassword: this.pass_,
            rpassword: this.pass__
          }).then (res => {
            if (res.errorCode == 0) this.$dialog.alert ({message: '修改成功'})
            else this.$dialog.alert ({message: '当前密码不正确'})
          })
        else this.$dialog.alert ({message: '请重新核对新密码'})
      }
    },
    mounted () {
      this.$post(this.$api.API_URL_CUSTOMER_BASIC, {
        id: this.$store.getters.user.id
      }).then (res => {
        this.name = res.data.nickname;
        this.email = res.data.email;
      })
    }
  }

</script>

<style lang="stylus" scoped>

</style>
