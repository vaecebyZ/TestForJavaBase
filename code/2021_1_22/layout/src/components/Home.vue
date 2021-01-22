<template>
  <div>


    <el-row id="userTitle">
      <span>欢迎1XXXXXXXX</span>|
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
            <div v-for="o in 8" :key="o" class="text item">
              {{'分类 ' + o }}
              <el-divider></el-divider>
            </div>
          </el-card>

        </el-aside>


        <el-container>
          <el-main>
            <el-row class="content-main" :gutter="20">
              <el-col class="title">
                <span>每日好货</span>


              </el-col>

              <el-row>

                <el-col :span="8">
                  <hr>
                  <el-row>
                    <el-col :span="12">
                      <img class="goodsimg" src="../assets/1.jpg">
                    </el-col>
                    <el-col :span="12">
                      <el-row class="goodstitle"><b>青菜</b></el-row>
                      <el-row class="goodscontent">没啥好说的就是新鲜的蔬菜。</el-row>
                      <el-row class="goodscomments">16人评价</el-row>
                    </el-col>
                  </el-row>
                  <el-col :span="12">
                    <img class="goodsimg" src="../assets/2.jpg">
                  </el-col>
                  <el-col :span="12">
                    <el-row class="goodstitle"><b>豌豆苗</b></el-row>
                    <el-row class="goodscontent">没啥好说的就是新鲜的蔬菜。</el-row>
                    <el-row class="goodscomments">13人评价</el-row>
                  </el-col>

                </el-col>

                <el-col :span="8">
                  <hr>
                  <el-row>
                    <el-col :span="12">
                      <img class="goodsimg" src="../assets/3.jpg">
                    </el-col>
                    <el-col :span="12">
                      <el-row class="goodstitle"><b>咸蛋</b></el-row>
                      <el-row class="goodscontent">没啥好说的就是新鲜的咸蛋。</el-row>
                      <el-row class="goodscomments">没啥人评价</el-row>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <img class="goodsimg" src="../assets/4.jpg">
                    </el-col>
                    <el-col :span="12">
                      <el-row class="goodstitle"><b>鸡蛋</b></el-row>
                      <el-row class="goodscontent">没啥好说的就是新鲜的鸡蛋。</el-row>
                      <el-row class="goodscomments">1人评价</el-row>
                    </el-col>
                  </el-row>

                </el-col>
                <el-col :span="8">
                  <hr>
                  <el-row>
                    <el-col :span="12">
                      <img class="goodsimg" src="../assets/5.jpg">
                    </el-col>
                    <el-col :span="12">
                      <el-row class="goodstitle"><b>梨子</b></el-row>
                      <el-row class="goodscontent">没啥好说的就是新鲜的水果。</el-row>
                      <el-row class="goodscomments">9人评价</el-row>
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-col :span="12">
                      <img class="goodsimg" src="../assets/6.jpg">
                    </el-col>
                    <el-col :span="12">
                      <el-row class="goodstitle"><b>橙子</b></el-row>
                      <el-row class="goodscontent">没啥好说的就是新鲜的水果。</el-row>
                      <el-row class="goodscomments">11人评价</el-row>
                    </el-col>
                  </el-row>
                </el-col>
                <el-row>
                  <el-button type="text" icon="el-icon-refresh">换组看看</el-button>
                </el-row>
              </el-row>
            </el-row>

            <el-row :gutter="10">
              <el-col :span="12">

                <el-row class="leftimgboxtitle">
                  <h5>
                    <div style="margin-left:10px"><b>果蔬</b></div>
                  </h5>
                </el-row>
                <div class="leftimgs">
                  <el-row>
                    <el-col :span="8">

                      <img style="width: 100px; height: 100px" src="../assets/1.jpg" >

                    </el-col>
                    <el-col :span="8">
                      <img style="width: 100px; height: 100px" src="../assets/2.jpg" >
                    </el-col>
                    <el-col :span="8">
                      <img style="width: 100px; height: 100px" src="../assets/3.jpg" > 
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-row>
                      <el-col :span="8">
                        <img  style="width: 100px; height: 100px;margin-top:2px" src="../assets/11.jpg">
                      </el-col>
                      <el-col :span="8">
                        <img  style="width: 100px; height: 100px;margin-top:2px" src="../assets/12.jpg" >
                      </el-col>
                      <el-col :span="8">
                        <img  style="width: 100px; height: 100px;margin-top:2px" src="../assets/13.jpg" >
                      </el-col>
                    </el-row>
                  </el-row>
                </div>

              </el-col>
              <el-col :span="12">

                <el-row class="rightimgboxtitle">
                  <h5>
                    <div style="margin-left:10px"><b>肉蛋</b></div>
                  </h5>
                </el-row>
                <div class="rightimgs">
                  <el-row>
                    <el-col :span="8">
                      <img  style="width: 100px; height: 100px" src="../assets/4.jpg" >
                    </el-col>
                    <el-col :span="8">
                      <img  style="width: 100px; height: 100px" src="../assets/5.jpg" >
                    </el-col>
                    <el-col :span="8">
                      <img  style="width: 100px; height: 100px" src="../assets/6.jpg" >
                    </el-col>
                  </el-row>
                  <el-row>
                    <el-row>
                      <el-col :span="8">
                        <img  style="width: 100px; height: 100px;margin-top:2px" src="../assets/21.jpg" >
                      </el-col>
                      <el-col :span="8">
                        <img  style="width: 100px; height: 100px;margin-top:2px" src="../assets/22.jpg" >
                      </el-col>
                      <el-col :span="8">
                        <img  style="width: 100px; height: 100px;margin-top:2px" src="../assets/23.jpg" >
                      </el-col>
                    </el-row>
                  </el-row>
                </div>
              </el-col>
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


  }
</script>

<style scoped>
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