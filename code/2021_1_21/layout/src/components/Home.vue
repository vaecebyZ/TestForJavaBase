<template>
  <div class="home">
    <div class="panel panel-default">    
      <div class="panel-body">
        <table class="table table-hover">
          <thead>
            <tr class="active">
              <td><b>ID</b></td>
              <td><b>品牌名称</b></td>
              <td><b>添加时间</b></td>
              <td><b>操作</b></td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in datas" :key="item.id">
              <td>{{item.id}}</td>
              <td>{{item.name}}</td>
              <td>{{item.addTime}}</td>
              <td>
                <a href="#" data-toggle="modal" data-target=".bs-example-modal-sm">编辑</a>|
                <a href="#">删除</a>
              </td>
            </tr>
          </tbody>
        </table>
        <hr>
        <div class="">
          <div class="row ">
            <div>
              <div class="col-xs-6"><input type="radio" name="name" id="">增值税发票<input type="radio" name="name"
                  id="">普通发票</div>
              <div></div>
            </div>
            <div></div>
          </div>


          <form>
            <div class="form-inline">
              <div class="form-group form-group-sm">
                <label for="">单位名称</label>
                <input type="text" class="form-control">
              </div>
              <div class="form-group form-group-sm">
                <label for="">纳税人识别码</label>
                <input type="text" class="form-control">
              </div>
            </div>
            <div class="form-inline ">
              <div class="form-group form-group-sm">
                <label for="">注册地址</label>

                <input type="text" class="form-control">
              </div>
              <div class="form-group form-group-sm">
                <label for="">注册电话</label>
                <input type="text" class="form-control">
              </div>
            </div>
            <div class="form-inline">
              <div class="form-group form-group-sm">
                <label for="">开户银行</label>
                <input type="text" class="form-control">
              </div>
              <div class="form-group form-group-sm">
                <label for="">银行账户</label>
                <input type="text" class="form-control">
              </div>
              <div class="col-sm-offset-3 col-sm-10">
                <button type="submit" class="btn btn-primary">保存</button>
              </div>
            </div>
           
          </form>

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
        editId: "" //真正的数据下标
      }
    },
    methods: {






    },
    mounted() {
      if (localStorage.getItem("data") == null) {
        alert("载入初始数据")
        this.datas = [{
            id: 1,
            name: "测试",
            addTime: "2018-1-1"
          },
          {
            id: 2,
            name: "测试",
            addTime: "2018-1-1"
          },
          {
            id: 3,
            name: "测试",
            addTime: "2018-1-1"
          },
          {
            id: 4,
            name: "测试",
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