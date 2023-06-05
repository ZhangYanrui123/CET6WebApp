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
        path: '/addExam', //添加考试
        component: () => import('@/components/teacher/addExam')
      },
      {
        path: '/addAnswer', //增加题库主界面
        component: () => import('@/components/teacher/addAnswer')
      },
      {
        path: '/addAnswerChildren', //点击试卷跳转到添加题库页面
        component: () => import('@/components/teacher/addAnswerChildren')
      },
      {
        path: '/selectExam', //查询所有考试
        component: () => import('@/components/teacher/selectExam')
      },
      {
        path: '/teacherInfo', 
        component: () => import('@/components/teacher/teacherInfo')
      },
      {
        path: '/grade', 
        component: () => import('@/components/teacher/grade')
      },
      {
        path: '/studentManage', //学生管理界面
        component: () => import('@/components/teacher/studentManage')
      },
      {
        path: '/addStudent', //添加学生
        component: () => import('@/components/teacher/addStudent')
      },
      {
        path: '/teacherManage',
        component: () => import('@/components/admin/tacherManage')
      },
      {
        path: '/addTeacher',
        component: () => import ('@/components/admin/addTeacher')
      }
    ]
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
