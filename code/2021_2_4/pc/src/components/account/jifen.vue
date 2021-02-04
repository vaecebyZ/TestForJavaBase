<template>
  <div id="app-content">
    <section class="page-header page-header-xs">
      <div class="container">
        <h1>个人中心</h1>
        <!-- breadcrumbs -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>个人中心</el-breadcrumb-item>
          <el-breadcrumb-item>我的积分</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- /breadcrumbs -->
        <!-- page tabs -->
        <ul class="page-header-tabs list-inline">
          <li >
            <a @click="goto('/account/order/list','OrderList')">我的订单</a>
          </li>
          <li>
            <a @click="goto('/account/contact/list','ContactList')">常用联系人</a>
          </li>
          <li>
            <a @click="goto('/account/wishlist','WishList')">我的收藏</a>
          </li>
          <li>
            <a @click="goto('/account/setting','UserSetting')">个人设置</a>
          </li>
          <li>
            <a @click="goto('/account/ewallet','EWallet')">充值</a>
          </li>
          <li>
            <a  @click="goto('/account/payment','Payment')">未支付订单</a>
          </li>
           <li class="active">
            <a >我的积分</a>
          </li>
        </ul>
        <!-- /page tabs -->
      </div>
    </section>
    <section>
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h2 class="panel-title">我的订单</h2>
          </div>
          <h4>积分：{{sumjifen}}</h4>
          <el-table border stripe :data="orderList.orderListData" style="width: 100%;">
              
            <el-table-column prop="orderNum" label="订单编号"></el-table-column>
             <el-table-column prop="price" label="订单金额" >
              <template slot-scope="scope">
                <span>{{(scope.row.price/100).toFixed(1)}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="jifen" label="积分" ></el-table-column>
            
          
          </el-table>
        </div>

        
      </div>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
        sumjifen:0,
      orderList: {
        orderListData:[]
      },
      pages: []
    };
  },
  created() {
    this.getOrderList(1);
  },
  methods: {
    getOrderList(nowPage) {
      var vm = this;
      var user = vm.Storage.Local.get("User");
      
      if (user) {
        var userId = user.id;
        vm.$post(vm.API.API_JIFEN, {
          id: userId
        }).then(res => {
            this.orderList.orderListData = res.data
             this.orderList.orderListData.forEach(element => {
               this.sumjifen += element.jifen
               console.log(this.sumjifen);
            });
        });
      }
    },
    goto(path, name, val) {
      this.$router.push({
        path: path,
        name: name
      });
      if (val) {
        this.Storage.Session.set("data", {
          orderNum: val
        });
      }
    },
    gotoPage(page) {
      if (0 < this.orderList.nowPage && this.orderList.nowPage <= this.orderList.pageCount) {
        this.getOrderList(page);
      }
    }
  }
};
</script>

<style scoped>
</style>
