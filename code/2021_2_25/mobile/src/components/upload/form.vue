<template>
  <div class="form">
    <mHeader>提交动态</mHeader>
    <div>
      <div class="img">
        <img :key="index" :src="item" alt="" v-for="(item,index) in src">
      </div>
    </div>

    <div style="min-height: 30vh" >
      <mt-cell class="cell" :title="talk" @click.native="show=true" icon="hot-o" is-link/>
      <mt-cell class="cell" :title="columns[item].name" :key="item" v-for="(item,index) in result"/>
      <mt-field class="field" placeholder="这一刻，想说点什么..." type="textarea"
                 v-model="values"/>
    </div>
    <mt-popup :overlay="true" position="bottom" v-model="show" class="popup">
       <van-checkbox-group v-model="result">  
        <mt-cell
          :key="item.id"
          :title="`${item.name}`"
          @click="toggle(index)"
          clickable
          v-for="(item,index) in columns">
          <van-checkbox :name="index" ref="checkboxes"/>
        </mt-cell>
       </van-checkbox-group>
    </mt-popup>
    <mt-button @click="send" size="large" type="primary">发布动态</mt-button>
  </div>
</template>


<script>
  import mHeader from '../other/header'

  export default {
    name: "vForm",
    components: {mHeader},
    data () {
      return {
        talk: '选择标签',
        src: [],
        result: [],
        values: '',
        show: false,
        columns:[],
        value:[],
      }     
    },
    mounted () {
      this.$post(this.$api.API_getLabel, {
        dictType: 10
      }).then (res => {
        this.columns = res.data.map (val => {
          return {id: val.id, name: val.dictName}
        })
      })
      this.src = this.$store.state.file
      this.attachment_rows_data=this.Storage.Local.get('resources')
    },
    methods: {
      checkon: function(){
  	},
      toggle (index) {
        this.$refs.checkboxes[index].toggle ()
      },
      onConfirm (values, index) {
        this.talk = values
        this.show = false
      },
      onCancel () {
        this.talk = '选择标签'
        this.show = false
      },
      send () {
       let list = this.result.map (val => {
          return {dataDictId: this.columns[val].id}
        })
        let attachmentList = []
        attachmentList.push(this.attachment_rows_data[0])    
        for(var item in attachmentList){
          var attachment={}
          attachment = {'attachmentName':attachmentList[item].attachmentName,'attachmentPath':attachmentList[item].attachmentPath}
        }
        let attachment_rows_data = []
        attachment_rows_data.push(attachment)
        this.$post (this.$api.API_SET_NOTEDATA, {
          makeEmp: this.$store.getters.user.id,
          label_rows_data: list,
          attachment_rows_data:attachment_rows_data,
          noteContent: this.values,
          dataDictId: 0
        }).then (res => {
          if (res.data != null) this.$router.push ('/')
          else this.$dialog.alert ({message: '网络超时'})
        })
      }
    },
    watch: {}
  }
</script>

<style lang='stylus' scoped>
  .form .img
    display flex
    flex-wrap wrap

    img
      width 100px
      height 100px
      margin 10px
      border-radius 10%
      object-fit cover
.popup
  width 100%;
.cell
  text-align left;
mt-cell
  font-size 10px;
.checklist
  text-align left;
.field
  width 100%;


</style>