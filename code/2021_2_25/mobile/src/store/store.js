import Vue from 'vue'
import Vuex from 'vuex'

Vue.use (Vuex)

export default new Vuex.Store ({
  state: {
    keyword: '',
    user: null,
    count: 0,
    address: null,
    file: null,
    dict: []
  },
  getters: {
    user (state) {
      return state.user ? state.user : JSON.parse (localStorage.getItem ('user'))
    },
    getDict: (state) => (id) => {
      state.dict.forEach (val => {
        if (val.id == id) return val
        debugger
      })
      return {}
    }
  },
  mutations: {
    setUser (state, user) {
      if (user) {
        state.user = user
        localStorage.setItem ('user', JSON.stringify (user))
      } else {
        state.user = null
        localStorage.removeItem ('user')
      }

    },
    setFile (state, file) {
      state.file = file
    },

    setStore (state, count) {
      state.count = count
    },
    
    setAddress (state, item) {
      state.address = {
        id: item.id,
        name: item.name,
        tel: item.mobile,
        addressDetail: item.address,
      }
    },
    addDict (state, list) {
      state.dict = state.dict.concat (list)
    },
    setDict (state, list) {
      state.dict = list
    }
  },
  actions: {}
})
