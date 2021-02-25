<template>
  <div class="Payment">
    <mHeader>支付</mHeader>
    <template v-if="cartList && cartList.orderItemList.length > 0">
    <div class="notice">
       <img src="/static/img/tongzhi.png" style="float:left;height:1.3em;width:1.3em;" >
        <span>当前购物车:{{count}}件商品,合计金额:{{cartList.price/100}}</span>
    </div>
    <div
      :key="index0"
      class="cell0"
      style="margin-top: 20px;text-align: left"
      v-for="(item0,index0) of cartList.orderItemList"
    >
      <div :key="index1" class="cell1" v-for="(item1,index1) of item0.productList">  
          <mt-cell :value="item1.name" title="商品名称"/>
          <mt-cell :value="(item1.price)/100" title="单价"/>
          <mt-cell :value="item0.quantity" title="数量"/>   
      </div>
    </div>

    <div v-for="(item,index) of optionList" style="margin-top: 20px">
      <mt-cell
        :title="item.name"
        v-model="key[index]"
        @click.native="item.show=true;optionIndex=index"
        style="text-align: left"
      ></mt-cell>
      <mt-popup v-model="item.show" position="bottom" style="width:100%">
        <div class="c cell">
          <div class="left" @click="onchange(index,item)">确定</div>
          <div class="picker-toolbar-title b">{{item.name}}</div>
          <div class="right" @click="item.show=!item.show">取消</div>
        </div>
        <mt-picker :slots="item.columns" @change="valuesChange"></mt-picker>
      </mt-popup>
    </div>
    <mt-button  @click="pay"  block  class="cell"  large  size="large" square style="margin-top: 20px" type="primary" >立即支付</mt-button>
    </template>
    <template v-else>
      <div class="NoOrder">
        <h2>暂无未支付订单！</h2>
      </div>
    </template>
  </div>
</template>

<script>
import mHeader from "./other/header";

export default {
  name: "Payment",
  data() {
    return {
      concatList: [],
      cartList: [],
      balance: [],
      count: 0,
      optionIndex: 0,
      option: { values: [] },
      key: ["", "支付宝"],
      cx: "",
      optionList: [
        {
          name: "选择联系人",
          show: false,
          columns: [],
          picked: 0,
          value: []
        },
        {
          name: "付款方式",
          show: false,
          columns: [{ values: ["支付宝", "微信", "电子钱包付款"] }],
          picked: 0,
          value: [0, 1, 2]
        }
      ]
    };
  },
  props: {},
  methods: {
    valuesChange(picker, values) {
      this.cx = values[0];
    },
    onchange(index, item) {
      this.key.splice(index, 1, this.cx);
      item.show = !item.show;
    },
    pay() {
      
      if (this.balance.amount >= this.cartList.price / 100)
        this.$post(this.$api.API_URL_ORDER_PAYMENT, {
          orderNum: this.cartList.orderNum,
          id: this.optionList[0].value[this.optionList[0].picked],
          paymentType: this.optionList[1].value[this.optionList[1].picked],
          userId: this.$store.getters.user.id
        }).then(res => {
          if (res.errorCode == 0) {
            this.$dialog.alert({ message: "支付成功！您将进入历史订单" });
            this.$router.replace("history");
            if (this.optionList[1].value[this.optionList[1].picked] == 2)
              this.$post(this.$api.API_URL_CUSTOMER_Pay_Order, {
                amount: -(this.cartList.price / 100),
                userId: this.$store.getters.user.id
              });
          } else this.$dialog.alert({ message: "网络超时" });
        });
      else this.$dialog.alert({ message: "余额不足" });
    }
  },
  components: { mHeader },
  created() {
    this.$post(this.$api.API_URL_CUSTOMER_Account, {
      userId: this.$store.getters.user.id
    }).then(res => {
      this.balance = res.data;
    });
    this.$post(this.$api.API_URL_CONTECT, {
      userId: this.$store.getters.user.id
    }).then(res => {
      let data = res.data.data;
      this.concatList = data;
      data.forEach(value => {
        if (value.contactFlag) {
          this.option.values.unshift(value.name);
          this.optionList[0].value.unshift(value.id);
        } else {
          this.option.values.push(value.name);
          this.optionList[0].value.push(value.id);
          this.optionList[0].columns.push(this.option);
        }
      });
      data.forEach(value => {
        this.key.splice(0, 1, value.name);
      });
    });
    this.$post(this.$api.API_URL_CART_ORDER, {
      userId: this.$store.getters.user.id,
      type: 1
    }).then(res => {
      let i = 0;
      if (res.data)
        res.data.orderItemList.forEach(value => {
          i += value.quantity;
        });
      this.cartList = res.data;
      this.count = i;
    });
  },
  beforeDestroy() {}
};
</script>

<style scoped lang='stylus'>
.c {
  padding: 20px 20px;
  position: relative;
  box-sizing: border-box;
  font-size: 16px;

  .left {
    float: left;
  }

  .right {
    float: right;
    position: absolute;
    right: 20px;
    top: 20px;
  }

  .b {
    text-align: center;
    margin-left: -30px;
    width: 100%;
    height: 20px;
  }
}
.notice
  text-align left;
  padding 5px 20px;
  box-sizing border-box; 
  background-color #fffbe8;
  color #ed6a0c;
  font-size 14px;
  img 
    margin-right 5px;
.NoOrder
  width: 100%
  text-align center
  font-size 14px
  position absolute
  top 50%
  transform translateY(-50%)
</style>
