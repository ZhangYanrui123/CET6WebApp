<template>
  <div class="exam">
    <ul class="top">
      <span class="order">考试列表</span> <!--可以添加考试查询，按照分类-->
    </ul>
    <ul class="paper">
      <li class="item" v-for="(item,index) in pagination.records" :key="index">
        <div class="info">
          考试类型：
          <span v-show="item.esubject == 1">
            六级口语
          </span>
          <span v-show="item.esubject == 2">
            六级笔试
          </span>&nbsp;&nbsp;&nbsp;&nbsp;
          开始时间：<span>{{item.ebegin}}</span>&nbsp;&nbsp;结束时间：<span>{{item.eend}}</span><br/>
          考试状态：
          <span v-show="item.estate == 1">
            未分配考场
          </span>
          <span v-show="item.estate == 2">
            考试未开始
          </span>
          <span v-show="item.estate == 3">
            考试正在进行
          </span>
          <span v-show="item.estate == 4">
            考试已结束
          </span>
          <span v-show="item.estate == 5">
            考试成绩已出
          </span>&nbsp;&nbsp;&nbsp;&nbsp;
          考场信息：
          <span v-show="item.estate < 2">还未分配</span>
          <span class = "classroom" v-show="item.estate >= 2" @click="open = true;searchClassroom(item.cid)">
            点击查看
          </span><br/>
          <span v-show="item.estate == 3">
            <el-button class = "enter" @click="toExam(item.eid)">进入考试</el-button>
          </span>
        </div>
      </li>
    </ul>
    <el-dialog title='考场信息' center :visible.sync='open'>
      学校：<span>{{this.classroom.cuniversity}}</span><br/>
      位置：<span>{{this.classroom.cclassroon}}</span><br/>
      开放时间：<span>{{this.classroom.copen}}</span><br/>
      关闭时间：<span>{{this.classroom.cclose}}</span><br/>
    </el-dialog>
    <div class="pagination">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagination.current"
        :page-sizes="[6]"
        :page-size="pagination.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pagination.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      open: false,
      pagination: { //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 6, //每页条数
        records: [{esubject : 1,ebegin : 2,eend : 3,estate : 3},{esubject : 2,ebegin : 2,eend : 3,estate : 3}]
      },
      classroom:{
        cuniversity: 1,
        cclassroon: 2,
        copen: 3,
        cclose: 4
      },
      uuid: null
    }
  },
  created() {
    this.getCookies()
    this.getExamInfo()
  },
  methods: {
    getCookies() {  //获取cookie
            this.uuid = this.$cookies.get("uuid")
    },
    //获取学生考试信息
    getExamInfo() {
      this.$axios({url: '/api/score',method: 'post',data: {
        uuid:this.uuid,
        current: this.pagination.current,
        size: this.pagination.size
      }}).then(res => {
        this.pagination.records = res.data.data
      }).catch(error => {
        console.log(error)
      })
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val
      this.getExamInfo()
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getExamInfo()
    },
    //跳转到试卷页
    toExam(eid) {
      this.$router.push({path: '/exam', query: {eid: eid}})
    }
  },
  searchClassroom(cid) { 
    this.$axios(`/api/exams/${cid}`).then(res => {
        this.classroom = res.data.data
      }).catch(error => {
        console.log(error)
      })
  }
}
</script>

<style lang="less" scoped>
ul{
  list-style-type:none
}
.enter{
  border-radius: 4px;
  border: none;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  background-color: #3a69df;
  color: #fff;
}
.classroom{
  cursor: pointer;
  text-decoration: underline;
}
.pagination {
  padding: 20px 0px 30px 0px;
  .el-pagination {
    display: flex;
    justify-content: center;
  }
}
.paper .item a {
  color: #417ea1;
}
.paper .item {
  border-radius: 4px;
  border: 1px solid #eee;
  transition: all 0.6s ease;
}
.paper .item:hover {
  border: 1px solid #7e3ac2;
  transform: scale(1);
}
.paper .item .info {
  font-size: 20px;
  color: #000;
}
.paper .item span {
  color: #417ea1;
}
.paper * {
  margin: 10px 0;
}
.exam .paper {
  justify-content: space-around;
  flex-wrap: wrap;
}
.exam .top {
  border-bottom: 1px solid #eee;
  margin-bottom: 20px;
  display: flex;
}
.exam .top .order {
  margin: 20px;
}
.exam {
  width: 90%;
  margin: 0 auto;
  background-color: #dda6cb17;
}
</style>
