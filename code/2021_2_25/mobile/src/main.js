// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Router from 'vue-router'

import App from './App'
import router from './router'
import mint from 'mint-ui'
import 'mint-ui/lib/style.css'
import vshare from 'vshare'
import vant from 'vant'
import 'vant/lib/index.css'
import 'mint-ui/lib/style.css'
import store from './store/store'
import fetch from './util/fetch'
import Storage from '@/util/StorageUtil'
import api from './util/APIUtil'



const extend = Vue.prototype
Vue.prototype.Storage = Storage

Vue.config.productionTip = false
Vue.use (Router)
Vue.use (mint)
Vue.use (vshare)
Vue.use (vant)
extend.$post = fetch.post
extend.$get = fetch.get
extend.$form = fetch.upload
extend.$api = api

/* eslint-disable no-new */
new Vue ({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
})
