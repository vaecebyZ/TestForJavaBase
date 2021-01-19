<template>
  <div class="home">

    <div style="background-color:rgb(47,109,169);text-align:left;color:White">
      <span><b>添加品牌</b></span>
    </div>

    <div style="padding-top:10px">
      id： <input v-model="id">
      name: <input v-model="name">
      <button class="btn-outline-primary" @click="add">添加</button>
      搜索关键字：<input v-model="keywords">
      <button class="btn-outline-primary" @click="search">搜索</button>
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
            <td><a href="#" @click="del(item)">删除</a></td>
          </tr>

        </tbody>
      </table>
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
        searchDates: []
      }
    },
    methods: {
      search() {

        if (this.keywords != "") {

          let tempArr = [];

          this.searchDates = this.datas; //备份

          this.datas.forEach(item => {
            if (item.name.search(this.keywords) != -1) {

              tempArr.push(item)
              // console.log(this.datas);
            }

            this.datas = tempArr;

          });

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
              
              this.searchDates =  this.searchDates.filter((item)=>{
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