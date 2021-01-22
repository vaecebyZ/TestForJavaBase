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
                            <el-button size="mini" style=" background-color: red;" slot="append" icon="el-icon-search">
                            </el-button>
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

                            <el-row>

                                <el-row :gutter="20">
                                    <el-col :span="6">
                                        <el-row ><img class="tableimgs" src="../assets/1.jpg"></el-row>
                                        <el-row class="content">没啥能说的</el-row>
                                        <el-row class="price"><del>15Y</del>14Y</el-row>
                                    </el-col>
                                    <el-col :span="6">
                                         <el-row  ><img class="tableimgs" src="../assets/2.jpg"></el-row>
                                        <el-row class="content">没啥能说的</el-row>
                                         <el-row class="price"><del>15Y</del>14Y</el-row>
                                    </el-col>
                                    <el-col :span="6">
                                         <el-row ><img class="tableimgs" src="../assets/3.jpg"></el-row>
                                        <el-row class="content">没啥能说的</el-row>
                                         <el-row class="price"><del>15Y</del>14Y</el-row>
                                    </el-col>
                                    <el-col :span="6">
                                         <el-row ><img class="tableimgs" src="../assets/4.jpg"></el-row>
                                        <el-row class="content">没啥能说的</el-row>
                                         <el-row class="price"><del>15Y</del>14Y</el-row>
                                    </el-col>
                                </el-row>

                                <el-row :gutter="20">
                                    <el-col :span="6">
                                         <el-row  ><img class="tableimgs" src="../assets/5.jpg"></el-row>
                                        <el-row class="content">没啥能说的</el-row>
                                         <el-row class="price"><del>15Y</del>14Y</el-row>
                                    </el-col>
                                    <el-col :span="6">
                                         <el-row  ><img class="tableimgs" src="../assets/6.jpg"></el-row>
                                        <el-row class="content">没啥能说的</el-row>
                                         <el-row class="price"><del>15Y</del>14Y</el-row>
                                    </el-col>
                                    <el-col :span="6">
                                         <el-row  ><img class="tableimgs" src="../assets/11.jpg"></el-row>
                                        <el-row class="content">没啥能说的</el-row>
                                         <el-row class="price"><del>15Y</del>14Y</el-row>
                                    </el-col>
                                    <el-col :span="6">
                                         <el-row  ><img class="tableimgs" src="../assets/12.jpg"></el-row>
                                        <el-row class="content">没啥能说的</el-row>
                                         <el-row class="price"><del>15Y</del>14Y</el-row>
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


    }
</script>

<style scoped>
.tableimgs{
    width: 150px;
    height: 150px;

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