import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui' //element-ui的全部组件
import 'element-ui/lib/theme-chalk/index.css'//element-ui的css
import VueCookies from 'vue-cookies'
import axios from 'axios'
Vue.use(VueCookies)
import axios from 'axios'

Vue.use(ElementUI) //使用elementUI



Vue.prototype.$axios = axios

Vue.prototype.$axios = axios

new Vue({
<<<<<<< HEAD
axios,
router,
store,
render: h => h(App)
=======
  axios,
  router,
  store,
  render: h => h(App)
>>>>>>> 62fad54ac64d8d3ba35e0e55ed3fa4265be44589
}).$mount('#app')

