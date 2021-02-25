<template>
  <div class="div">
    <mHeader>常用联系人</mHeader>
    <van-address-list
      :list="list"
      @add="$router.push({path:'/samsara/address/0'})"
      @edit="onEdit"
      @select="select"
      v-model="chosenAddressId"
    />
  </div>
</template>

<script>
import mHeader from "./header";

export default {
  methods: {
    onEdit(item, index) {
      this.$store.commit("setAddress", item);
      this.$router.push("/samsara/address/1");
    },
    select() {
      this.$post(this.$api.API_URL_CONTECT_DEFAULT, {
        id: this.chosenAddressId,
        userId: this.$store.getters.user.id
      }).then(res => {
        if (res.errorCode == 0) this.$toast("设置成功");
        else this.$dialog.alert({ message: "网络超时" });
      });
    }
  },
  mounted() {
    this.$post(this.$api.API_URL_CONTECT, {
      userId: this.$store.getters.user.id
    }).then(res => {
      let list = [];
      if (res.errorCode == 0) {
        res.data.data.forEach(value => {
          if (value.contactFlag) this.chosenAddressId = value.id;
          let obj = Object.assign({}, value);
          obj.tel = obj.mobile;
          obj.id = value.id; //-
          obj.label = obj.name + "  " + obj.mobile; //-
          list.push(obj);
        });
        this.list = list;
      } else this.$dialog.alert({ message: "网络超时" });
    });
  },
  components: { mHeader },
  data() {
    return {
      chosenAddressId: "1",
      list: []
    };
  }
};
</script>

<style lang="stylus" scoped>
.list {
  position: relative;
  height: 50px;
  font-size: 14px;
  text-align: left;
  padding: 10px 10px 10px 40px;
  border-bottom: 1px solid #ebedf0;

  .line {
    display: flex;

    .left {
      .address {
        width: 200px;
        margin-top: 5px;
        color: #7D7E80;
        font-size: 12px;
      }
    }
  }

  .mint-checkbox-core {
    position: absolute;
    margin: 13px 0px 0px -37px;
    padding: 0.5px;
  }
}

.check {
  text-align: left;
}

.button {
  position: fixed;
  bottom: 40px;
  left: 0;
  width: 100%;
}
</style>
