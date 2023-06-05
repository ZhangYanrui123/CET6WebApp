<!--学生考试首页-->
<template>
    <div class = "student">
        <div class = "head">
            <ul class = "navigation">
                <li><router-link to="/application">考试报名</router-link></li>
                <li><router-link to="/examTable">进入考试</router-link></li>
                <li><router-link to="/scoreTable">查看成绩</router-link></li>
                <li><router-link to="/manage">管理信息</router-link></li>
                <li class="right" @mouseenter="flag = !flag" @mouseleave="flag = !flag">
                    <a href="javascript:;">xuechen</a>
                    <div class="ex" v-if="flag">
                        <p class="exit" @click="exit()">退出</p>
                    </div>
                </li>
            </ul>
        </div>
      <div class="router">
        <router-view></router-view>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        flag: false,
        uname: null
      }
    },
    created() {
      this.getCookies()
    },
    methods: {
      exit() {  //退出登录
        this.$cookies.set("uname", null)
        this.$cookies.set("uuid", null)
        this.$cookies.set("role", 0)
        this.$router.push({path:"/login"}) //跳转到登录页面
      },
      getCookies() {  //获取cookie
            this.uname = this.$cookies.get("uname")
        },
    }
  }
  </script>
  
  <style scoped>
.student .head {
  margin: 0 auto;
  padding: 0px 50px;
  background-color: #fff;
}
.navigation a {
  display:block;
  text-decoration: none;
  color: #334046;
}
li {
  list-style: none;
  height: 60px;
  line-height: 60px;
}
.student .navigation{
  display: flex;
  border-top: 1px solid #eee;
  border-bottom: 1px solid #eee;
}
.student .navigation li {
  padding: 0 20px;
  cursor: pointer;
}
.student .navigation li:hover {
  background-color: #7e3ac2;
  transition: all 2s ease;
}
.student .navigation li:hover a {
  color: #fff;
}
.student .navigation .right {
  margin-left: auto;
  position: relative;
}
.student .navigation li.right :hover a {
  color: #000;
}
.right .ex {
  text-align: center;
  position: absolute;
  top: 60px;
  left: 0px;
  display: flex;
  flex-direction: column;
  border-radius: 2px;
  background-color: #fff;
}
.right .ex p {
  height: 40px;
  line-height: 40px;
  width: 100px;
}
.right .ex p:hover {
  background-color: #7e3ac2;
  color: #fff;
}
</style>