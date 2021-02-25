<template>
  <div :class="status?'header0':'header1'">
    <span  v-if="status" class="icon1 mintui mintui-back" @click="$router.go(-1)" ></span>
    <!-- <van-icon v-if="status" name="arrow-left" @click="$router.go(-1)"/> -->
    <slot></slot>
    <!-- <van-icon v-if="status" name="home-o" @click="$router.push('/home')"/> -->
    <span  v-if="status" @click="$router.push('/home')">
      <img src="/static/img/home1.png" style="float:left;height:1.2em;width:1.2em;" >
    </span>
  </div>
  
</template>

<script>
  export default {
    name: 'v-header',
    data () {
      return {}
    },
    props: {},
    methods: {},
    components: {},
    mounted () {
    },
    computed: {
      status () {
        return this.$route.path.includes ('samsara')
      }
    },
    beforeDestroy () {
    }
  }
</script>

<style scoped lang='stylus'>
  .header1
    background #717171
    color white
    font-size 1.2em
    line-height 1.2em
    padding 10px
  .header0
    background #717171
    color white
    font-size 1.2em
    line-height 1.2em
    padding 10px
    display flex
    align-items center
    justify-content space-between

  span
    margin-right 10px;

    
</style>
