<template>
  <div class="exam">
    <ul class="paper">
      <li class="item" v-for="(item,index) in pagination.records" :key="index">
        <div class="info">
          <div class="type">
            考试类型：
            <span v-show="item.esubject == 1">
              六级笔试
            </span>
            <span v-show="item.esubject == 2">
              六级口语
            </span>
          </div>
          <div class="time">
            开始时间：<span>{{item.ebegin}}</span> 结束时间：<span>{{item.eend}}</span>
          </div>
          <div class="status">
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
            </span>
          </div>
          <div class="classroom">
            考场信息：
            <span v-show="item.estate < 2">还未分配</span>
            <span class="classroom-link" v-show="item.estate >= 2" @click="open = true;this.searchClassroom(item.cid)">
              点击查看
            </span>
          </div>
          <div class="enter-exam" v-show="item.estate == 3">
            <el-button class="enter" @click="toExam(item.eid)">进入考试</el-button>
          </div>
          <div class="enter-exam" v-show="item.estate != 3">
            <el-button class="noEnter" disabled>进入考试</el-button>
          </div>
        </div>
      </li>
    </ul>
    <el-dialog title="考场信息" center :visible.sync="open">
      <div class="classroom-info">
        学校：<span>{{this.classroom.cuniversity}}</span>
        <br />
        位置：<span>{{this.classroom.cclassroon}}</span>
        <br />
        开放时间：<span>{{this.classroom.copen}}</span>
        <br />
        关闭时间：<span>{{this.classroom.cclose}}</span>
        <br />
      </div>
    </el-dialog>
    <div class="pagination">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-sizes="[6]"
          :page-size="pagination.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      open: false,
      pagination: {
        //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 6, //每页条数
        records: [
          { esubject: 1, ebegin: 2, eend: 3, estate: 3 },
          { esubject: 2, ebegin: 2, eend: 3, estate: 3 },
        ],
      },
      classroom: {
        cuniversity: "南开大学",
        cclassroon: "公教B402",
        copen: "2023-06-05 10:22:33",
        cclose: "2023-06-05 12:14:09",
      },
      uuid: null,
    };
  },
  created() {
    this.getCookies();
    this.getExamInfo();
  },
  methods: {
    getCookies() {
      //获取cookie
      this.uuid = this.$cookies.get("uuid");
    },
    //获取学生考试信息
    getExamInfo() {
      this.$axios({
        url: "http://127.0.0.1:8081/api/getallexam",
        method: "post",
        data: {
          uuid: this.uuid,
          // current: this.pagination.current,
          // size: this.pagination.size
        },
      })
          .then((res) => {
            this.pagination.records = res.data.data;
          })
          .catch((error) => {
            console.log(error);
          });
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getExamInfo();
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getExamInfo();
    },
    //跳转到试卷页
    toExam(eid) {
      this.$router.push({ path: "/exam", query: { eid: eid } });
    },
    searchClassroom(cid) {
      this.$axios
          .get(`http://127.0.0.1:8081/api/classroom/${cid}`)
          .then((res) => {
            this.classroom = res.data.data;
          })
          .catch((error) => {
            console.log(error);
          });
    },
  },
};
</script>

<style lang="less" scoped>

.exam {
  width: 90%;
  margin: 0 auto;
  background-color: rgba(209, 166, 221, 0.09);
}

.top {
  border-bottom: 1px solid #eee;
  margin-bottom: 20px;
  position: center;
}

.order {
  margin: 20px;
}

.paper {
  justify-content: space-around;
  flex-wrap: wrap;
  padding: 20px;
}

.item {
  border-radius: 4px;
  border: 1px solid #eee;
  transition: all 0.6s ease;
}

.item:hover {
  border: 1px solid #7e3ac2;
  transform: scale(1);
}

.info {
  font-size: 20px;
  color: #000;
}

.type {
  margin-bottom: 10px;
}

.time {
  margin-bottom: 10px;
}

.status {
  margin-bottom: 10px;
}

.classroom {
  margin-bottom: 10px;
}

.classroom-info {
  margin-bottom: 10px;
}

.enter-exam {
  margin-bottom: 10px;
}

.enter {
  border-radius: 4px;
  border: none;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  background-color: #3a69df;
  color: #fff;
  margin-top: 10px;
  margin-bottom: 10px;
}

.noEnter {
  background-color: #ccc;
  cursor: not-allowed;
  border: none;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  color: #fff;
  margin-top: 10px;
  margin-bottom: 10px;
}

.classroom-link {
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

</style>
