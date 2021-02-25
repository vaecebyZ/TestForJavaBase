<template>
  <div class="dev">
    <mHeader>我的收藏</mHeader>
    <ul class="wishList" v-if="wishProductlist.length > 0">
      <li v-for="wish in wishProductlist" @click="$router.push('/samsara/productdetail/'+wish.productId)">
        <div class="wishLeft">
          <img :src="$api.BASE_SERVER_URL+wish.product.defaultImg" />
        </div>
        <div class="wishRight">
          <p>{{wish.product.name}}</p>
          <div class="wishPrice">
            <h4>￥<span>{{(wish.product.shopPrice)/100}}</span></h4>
            <input @click.stop="deleteWish(wish.id)" class="deleteBtn" type="button" value="删除" />
          </div>
        </div>
      </li>
    </ul>
    <div class="NoWishProduct" v-else>
      <h2>暂无收藏商品！</h2>
    </div>
  </div>
</template>

<script>
  import mHeader from './header'

  export default {
    data () {
      return {
        wishProductlist: []
      }
    },
    created () {
      this.getWishList();
    },
    methods: {
      getWishList() {
        var userId = this.Storage.Local.get('user').id;
        this.$post(this.$api.API_URL_CUSTOMER_WISHLIST + '/1', {
          'userId': userId
        }).then (res => {
          this.wishProductlist = res.data.wishlist;
        })
      },
      deleteWish(wishId) {
        var userId = this.Storage.Local.get('user').id;
        this.$post(this.$api.API_URL_CUSTOMER_REMOVE_WISHLIST, {
          'userId': userId,
          'id': wishId
        }).then (res => {
          this.getWishList();
          this.$dialog.alert({ message: '删除成功' });
        })
      }
    },
    components: {mHeader}
  }

</script>

<style scoped>
.wishList{
  width: calc(100% - 1em);
  margin: 0 auto;
}
.wishList li{
  width: 100%; height: 8em;
  overflow: hidden;
}
.wishList li:after,.wishPrice:after{
  content: '';
  display: block;
  clear: both;
}
.wishLeft{
  width: 6em; height: 6em;
  margin: 1em 0;
  float: left;
}
.wishLeft>img{
  width: 100%; height: 100%;
  display: block;
}
.wishRight{
  width: calc(100% - 7em);
  float: right;
}
.wishRight>p{
  text-overflow: -o-ellipsis-lastline;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: left;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  line-clamp: 2;
  -webkit-box-orient: vertical;
}
.wishPrice>h4{
  color: red;
  line-height: 2em;
  float: left;
  margin: 0;
}
.wishPrice>h4>span{
  font-size: 1.2em;
}
.deleteBtn{
  width: 4em; height: 2em;
  float: right;
  background: no-repeat;
  border: 1px #e6e6e6 solid;
  border-radius: 1em;
}
.NoWishProduct{
  width: 100%;
  position: absolute;
  left: 0; top: 45%;
  text-align: center;
}
.NoWishProduct>h2{
  font-size: 1.4em;
}
</style>
