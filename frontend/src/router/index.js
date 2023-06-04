import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import('@/components/common/login.vue')
  },
  {
    path: '/student',
    name: 'student',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import('@/components/student/index.vue'),
    children: [
      {path:'/examTable', component: () => import('@/components/student//examTable.vue')},
      {path:'/application', component: () => import('@/components/student//application.vue')},
      {path:'/scoreTable', component: () => import('@/components/student//scoreTable.vue')},
      {path:'/manage', component: () => import('@/components/student//manage.vue')}
    ]
  },
  {path:'/exam',name: 'exam',component: () => import('@/components/student//exam.vue')},
  {path:'/payment',name: 'payment',component: () => import('@/components/student//payment.vue')},
  {path:'/register',name: 'register',component: () => import('@/components/common//register.vue')}
]

const router = new VueRouter({
  routes
})

export default router
