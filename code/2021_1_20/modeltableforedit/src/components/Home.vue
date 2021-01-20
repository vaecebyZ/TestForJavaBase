<template>
  <div class="home">

    <div style="background-color:rgb(47,109,169);text-align:left;color:White">
      <span><b>添加品牌</b></span>
    </div>

    <div style="padding-top:10px">
      id： <input v-model="id">
      name: <input v-model="name">
      <button class="btn btn-primary" @click="add">添加</button>
      搜索关键字：<input v-model="keywords">
      <button class="btn btn-primary" @click="search">搜索</button>
    </div>

    <div style="padding-top:20px">
      <table class="table table-hover">
        <thead></thead>
        <tr>
          <td><b>ID</b></td>
          <td><b>品牌名称</b></td>
          <td><b>添加时间</b></td>
          <td><b>操作</b></td>
        </tr>
        <tbody>
          <tr v-for="item in datas" :key="item.id">
            <td>{{item.id}}</td>
            <td>{{item.name}}</td>
            <td>{{item.addTime}}</td>
            <td>
              <a href="#" @click="toEdit(item)" data-toggle="modal" data-target=".bs-example-modal-sm">编辑</a>|
              <a href="#" @click="del(item)">删除</a>
            </td>
          </tr>

        </tbody>
      </table>
    </div>



    <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
            <h6 class="modal-title">修改信息</h6>
          </div>
          <div class="modal-body">
            <div class="col-md-12">
              <div class="row">
                <div>
                  id：
                </div>
                <div><input v-model="editItem.id"></div>
              </div>
              <div class="row">
                <div>名称：</div>
                <div><input v-model="editItem.name"></div>

              </div>
              <div class="row">
                <div> 添加日期：</div>
                <div><input v-model="editItem.addTime"></div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button class="btn btn-primary" data-dismiss="modal" @click="editCommit">提交</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>

        </div>
      </div>
    </div>

  </div>
</template>



<script>
  export default {
    name: 'Home',
    data() {
      return {
        datas: [],
        id: "",
        name: "",
        keywords: "",
        searchDates: [],
        editItem: {
          id: "",
          name: "",
          addTime: ""
        },
        editId:""//真正的数据下标
      }
    },
    methods: {
      editCommit() { 

          if(this.editItem.id !=""&&!isNaN(this.editItem.id) && this.editItem.name!="" && this.editItem.addTime!=""){//非空

          this.searchDates.forEach((item,index)=>{//修改数据
            if(this.editId == index){
                item.name= this.editItem.name
                item.id= this.editItem.id
                item.addTime= this.editItem.addTime
            }
          })

          this.datas = this.searchDates //更新表格

          localStorage.setItem("data", JSON.stringify(this.datas))

            alert("修改成功")
          
          
          
          }else{
              alert("请填写必要的信息,或者输入有误")
          }
  
      },
      toEdit(item) {

       if(this.searchDates == ""){
       this.searchDates =  this.datas
       }

       this.searchDates.forEach((i,index)=>{ //通过备份数组查到真正在搜索前的数组下标

          if(i==item){

            this.editId = index
         
          }

       })


        //数据绑定
        this.editItem.name = item.name
        this.editItem.id = item.id
        this.editItem.addTime = item.addTime

     
       
      },
      search() {

        if (this.keywords != "") {

          let tempArr = [];

          this.searchDates = this.datas; //备份

          this.datas.forEach(item => {

            if (item.name.search(this.keywords) != -1) {

              tempArr.push(item)
              // console.log(this.datas);
            }


          });

          this.datas = tempArr;

        } else {
          this.datas = JSON.parse(localStorage.getItem("data"))
        }

      },
      add() {


        if (this.id != "" && this.name != "" && !isNaN(this.id)) {

          let date = new Date()
          let y = date.getFullYear()
          let m = date.getMonth()
          let d = date.getDay()

          let fullDate = y + "-" + (m + 1) + "-" + d

          let tableData = {
            id: this.id,
            name: this.name,
            addTime: fullDate
          }

          this.datas.push(tableData)

          localStorage.setItem("data", JSON.stringify(this.datas))

        } else {
          alert("请添加必要的数据,或者输入正确的ID")
        }
      },
      //不能确认唯一键
      del(table) {

        if (this.searchDates == "") {
          this.searchDates = this.datas
        }

        if (confirm("确认？")) {

          this.datas.forEach((item, index) => {

            if (item == table) {

              this.datas.splice(index, 1)

              this.searchDates = this.searchDates.filter((item) => {
                return item != table
              })

              console.log(this.searchDates);
            }
          })

          localStorage.setItem("data", JSON.stringify(this.searchDates))
        }
      }
    },
    mounted() {
      if (localStorage.getItem("data") == null) {
        alert("载入初始数据")
        this.datas = [{
            id: 1,
            name: "sanka",
            addTime: "2018-1-1"
          },
          {
            id: 2,
            name: "vaecebyz",
            addTime: "2018-1-1"
          },
          {
            id: 3,
            name: "sankarea",
            addTime: "2018-1-1"
          },
          {
            id: 4,
            name: "vaeceby2",
            addTime: "2018-1-1"
          }
        ]
        localStorage.setItem("data", JSON.stringify(this.datas));
      } else {
        this.datas = JSON.parse(localStorage.getItem("data"))
      }


    }
  }
</script>

<style scoped>

</style>