<template>
  <div>
    <mHeader>订单编号：{{this.$route.params.id}}.00</mHeader>
    <div style="margin-top: 20px;text-align: left">
      <mt-cell :value="((detail.price)/100)+'.00'" title="订单金额"/>
      <mt-cell :value="detail.createTime" title="订单时间"/>
      <mt-cell title="订单状态" v-if="detail.status==0|| detail.status==null" value="订单编辑中"/>
      <mt-cell title="订单状态" timt-celltle="订单状态" v-if="detail.status==1" value="已下单"/>
      <mt-cell title="订单状态" v-if="detail.status==2" value="配送中"/>
      <mt-cell title="订单状态" v-if="detail.status==3" value="配送完成"/>
      <mt-cell title="订单状态" v-if="detail.status==4" value="订单取消"/>
      <mt-cell :value="detail.contactName" title="联系人"/>
      <mt-cell :value="detail.contactMobile" title="联系人电话"/>
      <mt-cell :value="detail.contactMobile" title="物流单号"/>
      <mt-cell :value="detail.contactMobile" title="物流公司"/>
    </div>
    <div :key="index0" style="margin-top: 20px;text-align: left"
                    v-for="(item,index0) of detail.orderItemList">
      <div v-for="items of item.productList" :key="items.id">
        <mt-cell  title="商品名称" class="goods">
           <span>{{items.name}}</span>
        </mt-cell>
        <mt-cell :value="'￥'+items.price/100+'.00'" title="单价"/>
      </div>
      <mt-cell :value="item.quantity" title="数量"/>
      <mt-cell :value="'￥'+item.price/100+'.00'" title="合计金额"/>
    </div>
  </div>
</template>

<script>
  import mHeader from './header'

  export default {
    methods: {
      getDetail() {
        this.$post(this.$api.API_URL_ORDER_DETAIL, {
          userId: this.$store.getters.user.id,
          orderNum: this.$route.params.id
        }).then(res => {
          if (res.errorCode == 0) this.detail = res.data
          else this.$dislog.alert({message: '网络超时'})
        })
      }
    },
    created() {
      this.getDetail()
    },
    components: {mHeader},
    data() {
      return {
        detail: {}
      }
    }
  }
</script>

<style lang="stylus" scoped>
span{
  display block;
  text-align right;
  width:300px;
  font-size 14px;
}

</style>
