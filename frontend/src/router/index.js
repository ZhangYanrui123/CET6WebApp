import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  // {
  //   path: '/',
  //   name: 'home',
  //   component: HomeView
  // },
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // },
  {
    path: '/teacher',
    component: () => import('@/components/admin/index'),
    children: [
      {
        path: '/', //首页默认路由
        component: () => import('@/components/common/hello')
      },
      {
        path: 'addExam', //添加考试
        component: () => import('@/components/teacher/addExam')
      },
      {
        path: 'addAnswer', //增加题库主界面
        component: () => import('@/components/teacher/addAnswer')
      },
      {
        path: 'addAnswerChildren', //点击试卷跳转到添加题库页面
        component: () => import('@/components/teacher/addAnswerChildren')
      },
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
