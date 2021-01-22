import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Main from '../components/Main.vue'
import 'jquery'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path:'/main',
    component: Main
  }
]

const router = new VueRouter({
  routes
})

export default router
