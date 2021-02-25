 <template>
  <div class="index">
    <router-view id="body"/>
    <div class="mint-tabbar is-fixed">
      <a href="/" class="mint-tab-item"><div class="mint-tab-item-icon"><img src="/static/img/home.png"></div><div class="mint-tab-item-label">首页</div></a> 
      <a href="/home" class="mint-tab-item"><div class="mint-tab-item-icon"><img src="/static/img/shop.png"></div> <div class="mint-tab-item-label">商城</div></a> 
      <a href="javascript:void(0)" class="mint-tab-item"> 
        <div id="picker">  
          <img :src="active ? icon.active : icon.normal" style="height: 30px"> 
        </div>  
      </a>
      <a href="/car" class="mint-tab-item"><div class="mint-tab-item-icon">
      <div class="mint-cell-value">
         <img src="/static/img/cart.png">
         <span class="mint-badge is-error is-size-small">{{$store.state.count}}</span>
      </div>
      </div>
      <span class="mint-tab-item-label">购物车</span>
      </a>
      <a href="/me" class="mint-tab-item"><div class="mint-tab-item-icon"><img src="/static/img/me.png"></div> <div class="mint-tab-item-label">我的</div></a>
    </div>
  </div>
</template>

<script>
export default {
  name: "Main",
  data() {
    return {
      active: 0,
      icon: {
        active: "static/addthis.png",
        normal: "static/addthis1.png"
      }
    };
  },
  props: {},
  methods: {
    onRead(file) {
      if (file instanceof Array) this.$store.commit("setFile", file);
      else this.$store.commit("setFile", [file]);
      this.$router.push("/samsara/form");
    }
  },
  components: {},
  created () {
      if (!this.$store.getters.user) this.$router.push ({path: '/login'})
      this.$post(this.$api.API_URL_CART, {
        userId: this.$store.getters.user.id,
        type: 1
      }).then (res => {
        let i = 0
        if (res.data != [])
          res.data.productList.forEach(element => {
             i += element.quantity
          })
        this.$store.commit ('setStore', i)
      })
    },
  mounted() {
    let that = this,
      list = [],
      content = [];
    var uploader = WebUploader.create({
      auto: true,
      swf: "static/uploader/Uploader.swf",
      server: this.$api.API_uploadImg,
      pick: "#picker",
      resize: false
    });
    uploader.on("beforeFileQueued", function(file) {});
    uploader.on("fileQueued", function(file) {
      uploader.makeThumb(
        file,
        function(error, src) {
          if (!that.$store.getters.user) {
            that.$toast("请先登录");
            uploader.destroy();
            return;
          }
          if (error) {
            that.$toast(error);
            return;
          }
          list.push(src);
        },
        100,100);
    });
    uploader.on("uploadAccept", function(a, b) {
      content.push(b);
    });
    uploader.on("uploadFinished", function() {
      that.Storage.Local.set("resources", content);
      that.$store.commit("setFile", list);
      that.$router.push("/samsara/form");
      that.uploader = null;
    });
  },
  beforeDestroy() {}
};
</script>

<style lang='stylus' scoped>
#body {
  height: 93vh;
  overflow: auto;
}

.mint-tab-item-label
  color:black
  
.mint-badge
  position absolute;
  margin-top -40px;
  margin-left -15px
</style>
