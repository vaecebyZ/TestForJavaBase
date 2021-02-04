<template>
  <div id="app-content">
    <section>
      <div class="div" style="width: 20vw;position: relative;float: left; top: 0; z-index: 99;">
        <el-menu default-active="2">
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">分类</span>
          </el-menu-item>
          <el-menu-item
            index="3"
            v-if="index < 15"
            @click="goto('/category','Category',category.id)"
            v-for="(category,index) in
          categoryList"
            v-bind:key="category.id"
          >
            <i class="el-icon-location"></i>
            <span slot="title">{{category.name}}</span>
          </el-menu-item>
          <el-menu-item
            index="4"
            @click="goto('/categories','Categories')"
            v-if="categoryList.length >= 15"
          >
            <i class="el-icon-more"></i>
            <span slot="title">查看更多分类</span>
          </el-menu-item>
        </el-menu>
      </div>

      <div class="container">
        <div id="page"></div>
        <div class="row">
          <div class="col-lg-9 col-md-9 col-sm-9 col-lg-push-3 col-md-push-3 col-sm-push-3" style="left: 0;">
            <template v-for="(category,index) in categoryRelationList">
              {{category.name}}
              <div class="divider divider-border divider-center" v-bind:key="index">
                <!-- divider -->
                <i class="fa fa-users"></i>
              </div>
              <ul class="shop-item-list row list-inline nomargin" v-bind:key="category.name">
                <!-- 产品-->
                <li
                  class="col-lg-3 col-sm-3"
                  v-for="product in category.productRelationList"
                  v-bind:key="product.id"
                >
                  <div class="shop-item">
                    <div class="thumbnail">
                      <!-- product image(s) -->
                      <a @click="goto('/product','Product',product.id)" class="shop-item-image">
                        <img
                          class="img-responsive"
                          v-bind:src="API.BASE_SERVER_URL+product.defaultImg"
                        >
                      </a>
                      <!-- /product image(s) -->
                      <!-- hover buttons -->
                      
                      <!-- /hover buttons -->
                      <!-- product more info -->
                      <div class="shop-item-info"></div>
                      <!-- /product more info -->
                    </div>
                    <div class="shop-item-summary text-center">
                      <h2>{{product.name}}</h2>
                      <!-- price -->
                      <div class="shop-item-price">
                        <span class="line-through">￥{{(product.price / 100).toFixed(1)}}</span>
                        ￥{{(product.shopPrice /
                        100).toFixed(1)}}
                      </div>
                      <!-- /price -->
                    </div>
                    <!-- /buttons -->
                  </div>
                </li>
                <!-- /产品 -->
              </ul>
            </template>
            <hr>
            <!-- Pagination Default -->
            <div name="bootpagFooter" class="text-center"></div>
            <!-- /Pagination Default -->
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      categoryList: [],
      categoryRelationList: []
    };
  },
  created: function() {
    this.loadIndexData();
  },
  methods: {
    loadIndexData() {
      var vm = this;
      vm.$get(vm.API.API_URL_CATEGORY_CONDITION).then(res => {
        if (res.errorCode == 0) {
          vm.categoryList = res.data;
        }
      });
      vm.$post(vm.API.API_URL_CATALOG_INDEX_PRODUCTS + "/8").then(res => {
        if (res.errorCode == 0) {
          vm.categoryRelationList = res.data;
        }
      });
    },
    goto(path, name, val) {
      var user = this.Storage.Local.get("User");
      var vm = this;
      this.$router.push({
        path: path,
        name: name
      });
      var params = {
        id: val
      };
      this.Storage.Session.set("data", params);
    }
  }
};
</script>

<style scoped>
.loading-div {
  position: relative;
}

.loading-img {
  position: absolute;
  top: 50%;
  left: 50%;
}
</style>
