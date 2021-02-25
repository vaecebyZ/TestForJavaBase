<template>
  <div class="car">
    <vHeader>我的购物车</vHeader>
    <div class="div" v-if="cartList">
      <div class="notice">
       <img src="/static/img/tongzhi.png" style="float:left;height:1.3em;width:1.3em;" >
        <span>当前购物车:{{count}}件商品,合计金额:{{cartList.price/100}}</span>
      </div>
      <div :key="index0" class="cell0" style="margin-top: 20px;text-align: left"
           v-for="(item0,index0) of cartList.productList">
          <div :right="[{content:'删除',style: {background: 'red', color: '#fff'}, handler: () => remove(item0.id)}]">
              <mt-cell  title="商品名称">
                  <span class="good">{{item0.name}}</span>
              </mt-cell>
              <mt-cell :value="(item0.shopPrice)/100" title="单价"/>
              <mt-cell :value="item0.quantity" title="数量"/>
          </div>    
      </div>
      <mt-button @click="payment" block class="cell" large size="large" square
                  style="margin-top: 20px" type="primary">立即支付
      </mt-button>
      <mt-button @click="removeAll" block class="cell" size="large" square style="margin-top: 20px" type="danger">清空购物车
      </mt-button>
    </div>
  </div>
</template>

<script>
  import vHeader from './other/header'

  export default {
    components: {
      vHeader
    },
    data() {
      return {
        cartList: {},
        count: 0,
        removeIndex: '0'
      }
    },
    created() {
      if (!this.$store.getters.user) this.$router.push({path: '/login'})
      else this.getList()
    },
    methods: {
      getList() {
        this.$post(this.$api.API_URL_CART, {
          userId: this.$store.getters.user.id,
        }).then(res => {
          let i = 0
          if (res.data) res.data.productList.forEach(value => {
            i += value.quantity
          })
          this.cartList = res.data
          this.count = i
        })
      },
      onClose(clickPosition, instance) {
        switch (clickPosition) {
          case 'left':
          case 'cell':
          case 'outside':
            instance.close();
            break;
          case 'right':
            this.$dialog.confirm({
              message: '确定删除吗？'
            }).then(() => {
              this.remove(this.removeIndex)
              instance.close();
            });
            break;
        }
      },
      removeAll() {
        this.$post(this.$api.API_URL_CART_ITEMS_REMOVEALL, {
        userId: this.$store.getters.user.id
        }).then(res => {
          this.getList()
        })
      },
      remove(id) {
        this.$post(this.$api.API_URL_CART_ITEMS_REMOVE, {
          productId: id,
          userId: this.$store.getters.user.id
        }).then(res => {
          if (res.errorCode == 0) this.$dialog.alert({message: '删除成功！'})
          else this.$dialog.alert({message: '删除失败！'})
          this.getList()
        })
      },
     payment(){
        var vm = this
        var userId = vm.$store.getters.user.id
        if (userId) {
          var orderItemList = []
          var orderList = this.cartList.productList
          for (var order in orderList) {
            var orderItem = {}
            orderItem["productId"] = orderList[order].id
            orderItem["quantity"] = orderList[order].quantity
            orderItemList.push (orderItem)
          }
        }
         this.$post(this.$api.API_URL_CART_COMMIT, {
          orderItemList:orderItemList,
          userId:userId
        }).then(res => {
          vm.$router.push({path: '/samsara/payment'})
          vm.removeAll()   
        })
     }
    }
  }

</script>

<style lang="stylus">
.notice
  text-align left;
  padding 5px 20px;
  box-sizing border-box; 
  background-color #fffbe8;
  color #ed6a0c;
  font-size 14px;
  img 
    margin-right 5px;

.good
  display block;
  width 260px;
  text-align right;
  
</style>
