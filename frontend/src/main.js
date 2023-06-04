import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui' //element-ui的全部组件
import 'element-ui/lib/theme-chalk/index.css'//element-ui的css
import VueCookies from 'vue-cookies'
import axios from 'axios'
Vue.use(VueCookies)

Vue.use(ElementUI) //使用elementUI


Vue.config.productionTip = false

Vue.prototype.$axios = axios

new Vue({
  axios,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
