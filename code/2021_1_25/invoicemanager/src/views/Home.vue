<template>
  <div class="home">
    <el-table :data="tableDatas" border style="width: 100%">
      <el-table-column fixed prop="id" label="序号" width="50">
      </el-table-column>

      <el-table-column prop="type" label="发票类型" width="120">
      </el-table-column>

      <el-table-column prop="cpName" label="发票抬头" width="120">
      </el-table-column>
      <el-table-column prop="cpNum" label="企业税号" width="120">
      </el-table-column>
      <el-table-column prop="address" label="地址" width="200">
      </el-table-column>
      <el-table-column prop="phone" label="电话" width="120">
      </el-table-column>
      <el-table-column prop="bankName" label="开户银行" width="120">
      </el-table-column>
      <el-table-column prop="bankId" label="开户银行账户" width="120">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="Edit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="Delete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-form ref="form" :model="form" label-width="80px">

      <el-form-item>
        <el-radio-group v-model="form.type">
          <el-radio label="增值税专用发票"></el-radio>
          <el-radio label="普通发票"></el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="单位名称">

        <el-col :span="11">
          <el-input v-model="form.cpName"></el-input>

        </el-col>

        <el-col class="line" :span="2">纳税识别码</el-col>
        <el-col :span="11">
          <el-input v-model="form.cpNum"></el-input>
        </el-col>
      </el-form-item>


      <el-form-item label="注册地址">

        <el-col :span="11">
          <el-input v-model="form.address"></el-input>
        </el-col>

        <el-col class="line" :span="2">注册电话</el-col>
        <el-col :span="11">
          <el-input v-model="form.phone"></el-input>
        </el-col>
      </el-form-item>


      <el-form-item label="开户银行">

        <el-col :span="11">
          <el-input v-model="form.bankName"></el-input>
        </el-col>

        <el-col class="line" :span="2">银行账户</el-col>
        <el-col :span="11">
          <el-input v-model="form.bankId"></el-input>
        </el-col>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="commit">保存</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
  export default {
    name: 'Home',
    data() {
      return {
        isEdit: false,
        tableDatas: [],
        form: {
          id:'',
          type: '',
          cpName: '',
          cpNum: '',
          address: '',
          phone: '',
          bankName: '',
          bankId: ''
        }
      }
    },
    methods: {
      init() {
        this.form = {
          id:'',
          type: '',
          cpName: '',
          cpNum: '',
          address: '',
          phone: '',
          bankName: '',
          bankId: ''
        }
        
        this.$http.post("http://localhost:8088/Shopping/api/v1/invoice/getinvoice").then((res) => {
          // console.log(res.data);
          if (res.data != null) {
            let tempArr = res.data.data;
            tempArr.forEach(e => {
              if (e.type == 0) {
                e.type = "普通发票"
              } else {
                e.type = "增值税专用发票"
              }
            })
            this.tableDatas = tempArr;
          }
        })
      },
      Edit(i, r) {
        this.form = r;
      },
      Delete(i, r) {
          this.tableDatas.forEach(element => {
              if(element == r){
                
                console.log(r.id);

                this.$http.post("http://localhost:8088/Shopping/api/v1/invoice/delinvoice",{
                  "id":r.id
                }).then(res => {
                if(res.data.data){
                  this.$message.success("删除成功！")
                }
                this.init()
              })
              
              }
          });
      },
      commit() {
        
        if(this.form.type==""){
           this.$message.error('请至少选择一种选择发票类型！')
           return;
        }

        if (this.form.cpName != "" && this.form.cpNum != "") {
          
          if (this.form.type == "增值税专用发票") {
            if (this.form.address == "" || this.form.phone == "" || this.form.bankName == "" || this.form.bankId ==
              "") {
              this.$message.error('增值税发票需要填写全部信息')
            } else {
              this.form.type = 1
            }
          }else{
            this.form.type = 0
          }

          if(this.form.id != ""){
              this.$http.post("http://localhost:8088/Shopping/api/v1/invoice/editinvoice", this.form).then(res => {
                if(res.data.data){
                  this.$message.success("编辑成功！")
                }
                this.init()
              })


          }else{
            this.$http.post("http://localhost:8088/Shopping/api/v1/invoice/addinvoice", this.form).then(res => {
                if(res.data.data){
                  this.$message.success("添加成功！")
                }
                this.init()
              })
          }

        } else {
          this.$message.error('请输入必要的信息');
        }

      }
    },
    mounted() {
        this.init()
    }
  }
</script>