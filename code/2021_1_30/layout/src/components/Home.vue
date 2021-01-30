<template>
  <div>


    <el-row id="userTitle">
      <span>欢迎{{userName}}</span>|
      <span>会员中心</span>|
      <span>我的订单</span>|
      <span>看不清服务</span>
    </el-row>
    <el-container>
      <el-header>
        <el-row>

          <el-col :span="8">
            <img style="height:40px;margin-top:-20px;" src="../assets/logo_without_text.png">
            <div style="margin-top:-40px;color:#ffffff"><b>中慧科技</b></div>
          </el-col>

          <el-col :span="8">
            <el-input size="mini" placeholder="请输入搜索关键字">
              <el-button size="mini" style=" background-color: red;" slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </el-col>

          <el-col :span="8">

            <el-badge :value="3" class="icos">
              <el-button size="mini" icon="el-icon-goods">
                查看购物车
              </el-button>
            </el-badge>

          </el-col>

        </el-row>

      </el-header>
      <el-container>
        <el-aside width="200px">

          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <b>分类</b>
            </div>
            <div v-for="item in cacategory" :key="item.id" class="text item">
              <el-button @click="toShowGoods(item)" type="text">{{item.name}} </el-button>
              <el-divider></el-divider>
            </div>
          </el-card>

        </el-aside>


        <el-container>
          <el-main v-if="isShow">
            <el-row class="content-main" :gutter="20">
              <el-col class="title">
                <span>每日好货</span>


              </el-col>

              <el-row>

                <el-col v-for="(item) in hotList" :key="item.id" :span="8">
                  <hr>
                  <el-row>
                    <el-col :span="12">
                      <img class="goodsimg" :src='"http://127.0.0.1:8088/Shopping/"+item.defaultImg'>
                    </el-col>
                    <el-col :span="12">
                      <el-row class="goodstitle"><b>{{item.name}}</b></el-row>
                      <el-row class="goodscontent">{{item.explain}}</el-row>
                      <el-row class="goodscomments">16人评价</el-row>
                    </el-col>
                  </el-row>
                </el-col>


                <el-row>
                  <el-button type="text" @click="swap()" icon="el-icon-refresh">换组看看</el-button>
                </el-row>
              </el-row>
            </el-row>

            <el-row :gutter="10">
              <el-col :span="12">

                <el-row class="leftimgboxtitle">
                  <h5>
                    <div style="margin-left:10px"><b>{{hotcacategoryonename}}</b></div>
                  </h5>
                </el-row>
                <div class="leftimgs">
                  <el-row>
                    <el-col v-for="item in hotcacategoryone" :key="item.id" :span="8">

                      <img style="width: 100px; height: 100px" :src='"http://127.0.0.1:8088/Shopping/"+item.defaultImg'>

                    </el-col>

                  </el-row>

                </div>

              </el-col>
              <el-col :span="12">

                <el-row class="rightimgboxtitle">
                  <h5>
                    <div style="margin-left:10px"><b>{{hotcacategorytwoname}}</b></div>
                  </h5>
                </el-row>
                <div class="rightimgs">

                  <el-row>
                    <el-col v-for="item in hotcacategorytwo" :key="item.id" :span="8">

                      <img style="width: 100px; height: 100px" :src='"http://127.0.0.1:8088/Shopping/"+item.defaultImg'>

                    </el-col>

                  </el-row>
                </div>
              </el-col>
            </el-row>


          </el-main>

          <el-main>

            <el-row class="content-main" :gutter="20">

              <el-row>

                <el-row :gutter="20">
                  <el-col style=" width: 330px;height: 270px;" :span="6" v-for="item in categorybyId" :key="item.id">
                    <el-row><img class="tableimgs" :src='"http://127.0.0.1:8088/Shopping/"+item.defaultImg'></el-row>
                    <el-row class="content">{{item.name}}</el-row>
                    <el-row class="price"><del>{{item.price/100}}Y</del>{{item.shopPrice/100}}Y</el-row>
                  </el-col>
                </el-row>



              </el-row>

            </el-row>




          </el-main>







          <el-footer>这是页脚</el-footer>
        </el-container>

      </el-container>
    </el-container>

  </div>
</template>



<script>
  export default {
    data() {
      return {
        userName: "",
        hotList: [],
        swapId: 0,
        hotcacategoryone: [],
        hotcacategorytwo: [],
        hotcacategoryonename: "",
        hotcacategorytwoname: "",
        cacategory: [],
        isShow: true,
        categorybyId: []
      }
    },
    methods: {
      toShowGoods(i) {
        this.isShow = false;
        console.log(i);
        this.$http.post("http://127.0.0.1:8088/Shopping/api/v1/getgoodes", {
          id: i.id
        }).then(res => {
          this.categorybyId = res.data.data

        })
      },
      init() {
        this.$http.post("http://localhost:8088/Shopping/api/v1/get/user/basic", {
          id: "362791"
        }).then(res => {
          //  console.log(res.data);
          this.userName = res.data.data.userName
        })

        this.$http.post("http://localhost:8088/Shopping/api/v1/getcategory", {
          id: "362791"
        }).then(res => {
          this.cacategory = res.data.data
        })



        this.$http.post("http://localhost:8088/Shopping/api/v1/gethotproducts", {}).then(
          res => {
            this.hotList = res.data.data
            // console.log(res.data.data);
          }
        )

        this.$http.post("http://127.0.0.1:8088/Shopping/api/v1/gethotlist").then(
          res => {

            this.hotcacategory = res.data.data
            this.hotcacategory.forEach(element => {
                if (element.categoryId == this.hotcacategory[0].categoryId) {
                  this.hotcacategoryone.push(element)
                } else {
                  this.hotcacategorytwo.push(element)
                }

              }

            );

            this.hotcacategoryone.splice(6)
            this.hotcacategorytwo.splice(6)

            this.hotcacategoryonename = this.hotcacategoryone[0].categoryName
            this.hotcacategorytwoname = this.hotcacategorytwo[0].categoryName

          }
        )


      },
      swap() {
        this.$http.post("http://localhost:8088/Shopping/api/v1/gethotproducts", {
          pageTag: ++this.swapId * 6
        }).then(
          res => {
            this.hotList = res.data.data
            console.log(res.data.data);
          }
        )

      }
    },
    mounted() {
      this.init()
    }

  }
</script>

<style scoped>
  .content {
    font-size: 8px;
  }

  .leftimgs {
    margin-left: -10px;
  }

  .rightimgs {
    margin-right: -10px;

  }

  .leftimgboxtitle {
    background: rgb(252, 96, 40);
    text-align: left;
    margin-top: 10px;
    margin-left: -10px;
    color: white;

  }

  .rightimgboxtitle {
    background: rgb(207, 118, 77);
    text-align: left;

    margin-top: 10px;
    margin-right: -10px;
    color: white;

  }

  .goodscomments {
    color: lightskyblue;
    text-align: left;
    font-size: 5px;
  }

  .goodstitle {
    text-align: left;
  }

  .goodscontent {
    font-size: 10px;
    color: #B3C0D1;
    text-align: left;
  }

  .goodsimg {
    width: 100px;
    height: 100px;
  }

  hr {
    margin: 0px;
  }

  .icos {
    line-height: 0px;
  }

  .title {
    line-height: 35px;
    text-align: left;
  }

  .content-main {
    background: white;
  }

  #userTitle {
    background: lightgray;
    text-align: center;
    font-size: 8px;
    color: gray;
  }

  .el-header {
    background: orangered;
    text-align: center;
    line-height: 60px;
  }



  .el-footer {
    background-color: #333;
    color: white;
    text-align: center;
    line-height: 60px;
  }



  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;

  }

  body>.el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>