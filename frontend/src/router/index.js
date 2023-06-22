import Vue from 'vue'
import VueRouter from 'vue-router'

// Z: admin and home page
import Layout from "@/views/layout/Index.vue"
import Login from "@/views/login/Index.vue"
// import Login from "@/components/common/login.vue";
import Home from "@/views/Home.vue"
import About from "@/views/About.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/about',
    name: 'about',
    component: About
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },

  {
    path: '/admin',
    name: 'admin',
    component: Layout
  },
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // },
  // {
  //   path: '/login',
  //   name: 'login',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import('@/components/common/login.vue')
  // },
  {path:'/exam',name: 'exam',component: () => import('@/components/student//exam.vue')},
  {path:'/payment',name: 'payment',component: () => import('@/components/student//payment.vue')},
  {path:'/register',name: 'register',component: () => import('@/components/common//register.vue')},
  {
    path: '/teacher',
    name: 'teacher',
    component: () => import('@/components/teacher/index'),
    children: [
      {path: '/', component: () => import('@/components/common/hello')},    // 首页默认路由
      {path: '/addExam', component: () => import('@/components/teacher/addExam')},  // 添加考试
      {path: '/addAnswer', component: () => import('@/components/teacher/addAnswer')}, // 增加题库主界面
      {path: '/addAnswerChildren', component: () => import('@/components/teacher/addAnswerChildren')},  //点击试卷跳转到添加题库页面
      {path: '/selectExam', component: () => import('@/components/teacher/selectExam')},  // 查询所有考试
      {path: '/teacherInfo', component: () => import('@/components/teacher/teacherInfo')},
      {path: '/grade', component: () => import('@/components/teacher/grade')},  // 成绩管理
      {path: '/studentManage', component: () => import('@/components/teacher/studentManage')},  //学生管理界面
      {path: '/addStudent', component: () => import('@/components/teacher/addStudent')},  // 添加学生
      {path: '/teacherManage', component: () => import('@/components/admin/tacherManage')},  // 教师管理
      {path: '/addTeacher', component: () => import ('@/components/admin/addTeacher')}  // 添加教师
    ]
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
  }
]

const router = new VueRouter({
  routes
})

export default router
