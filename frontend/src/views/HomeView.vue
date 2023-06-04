<!--学生考试首页-->
<template>
  <div class = "home">
      <div class = "head">
          <ul class = "navigation">
              <li><router-link to="/login">考试报名</router-link></li>
              <li><router-link to="/login">进入考试</router-link></li>
              <li><router-link to="/login">查看成绩</router-link></li>
              <li><router-link to="/login">管理信息</router-link></li>
              <li class="right">
                <li><router-link to="/login">登录</router-link></li>
              </li>
          </ul>
      </div>
    <div :style = "background" class ="bottom">
       <span>六级报考系统</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      role: 0,
      background:{
        backgroundImage: 'url(' + require("./background.png") + ')',
        backgroundRepeat: 'no-repeat',
        backgroundSize: 'cover',
        backgroundColor: '#000',
        backgroundPosition: 'center top'
      }
    }
  },
  created() {
    this.getCookies()
  },
  methods: {
    getCookies() {  //获取cookie
      this.role = this.$cookies.get("role")
      if(this.role){
        switch(this.role) {
          case "1":  //管理员
            this.$router.push({path: '/index' })
            break
          case "2": //学生
            this.$router.push({path: '/student' })
            break
          case "3": //教师
            this.$router.push({path: '/teacher'})
            break
        }
      }
    },
  }
}
</script>

<style scoped>
.home .head {
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
.home .navigation{
display: flex;
border-top: 1px solid #eee;
border-bottom: 1px solid #eee;
}
.home .navigation li {
padding: 0 20px;
cursor: pointer;
}
.home .navigation li:hover {
background-color: #7e3ac2;
transition: all 2s ease;
}
.home .navigation li:hover a {
color: #fff;
}
.home .navigation .right {
margin-left: auto;
position: relative;
}
.home .navigation li.right :hover a {
color: #000;
}
.home .bottom{
width: 100%;
height: 700px;
display: flex;
}
.home .bottom span{
font-size: 50px;
padding-top: 100px;
padding-left: 100px;
}
</style>