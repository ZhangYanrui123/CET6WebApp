<!--获取试卷并跳转到添加题库-->
<template>
  <div class="exam">
<!--    <el-button @click="addData">请添加试卷</el-button>-->
    <el-table :data="pagination.records" border>
      <el-table-column fixed="left" prop="cname" label="考场" width="200"></el-table-column>
      <el-table-column prop="ebegin" label="开始时间" width="240"></el-table-column>
      <el-table-column prop="eend" label="结束时间" width="240"></el-table-column>
      <el-table-column prop="esubject" label="考试科目" width="200">
          <template slot-scope="scope">
              <span v-if="scope.row.esubject === 1">六级口语</span>
              <span v-else-if="scope.row.esubject === 2">六级笔试</span>
              <span v-else>{{ scope.row.esubject }}</span>
          </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button @click="add(scope.row.paperId,scope.row.source)" type="primary" size="small">编辑考卷</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[4, 8, 10, 20]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total" class="page">
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {}, //保存点击以后当前试卷的信息
      pagination: { //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 4, //每页条数
        records: [] // 初始化为一个空数组
      },
    }
  },
  created() {
    this.getExamInfo()
    //this.addData("001","2023-05-15 10:00","2023-05-15 12:00","六级笔试")
    //this.addData("002","2023-06-04 10:00","2023-06-04 12:00","六级口语")
  },
  methods: {
    dateTransfer(dateTimeString){
// 创建一个 Date 对象并解析日期时间字符串
        const dateTime = new Date(dateTimeString);
// 获取年、月、日、小时、分钟和秒数
        const year = dateTime.getUTCFullYear();
        const month = dateTime.getUTCMonth() + 1; // 月份从 0 开始，需要加 1
        const day = dateTime.getUTCDate();
        const hours = dateTime.getUTCHours();
        const minutes = dateTime.getUTCMinutes();
        const seconds = dateTime.getUTCSeconds();

// 将各个部分格式化为两位数的字符串
        const formattedYear = year.toString().slice(-2).padStart(2, "0");
        const formattedMonth = month.toString().padStart(2, "0");
        const formattedDay = day.toString().padStart(2, "0");
        const formattedHours = hours.toString().padStart(2, "0");
        const formattedMinutes = minutes.toString().padStart(2, "0");
        const formattedSeconds = seconds.toString().padStart(2, "0");

// 拼接成 yymmddhhmmss 格式的字符串
        const formattedDateTime = formattedYear +"-"+ formattedMonth +"-"+ formattedDay +" " +formattedHours + ":"+ formattedMinutes + ":"+ formattedSeconds;
        console.log(formattedDateTime)
        return formattedDateTime
    },
    getExamInfo() { //分页查询所有试卷信息
      this.$axios.post(`http://127.0.0.1:8081/api/exam/getAllExams`)
          .then(res => {
          this.pagination.records = res.data.data;
          this.pagination.records.forEach(record => {
              record.ebegin = this.dateTransfer(record.ebegin);
              record.eend = this.dateTransfer(record.eend);
          });
      }).catch(error => {
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
    add(paperId,source) { //增加题库
      this.$router.push({path:'/addAnswerChildren',query: {paperId: paperId,subject:source}})
    },
    addData(id, ebegin, eend, subject) {
      // 创建新的数据对象
      let newData = {
        id: id,
        ebegin: ebegin,
        eend: eend,
        subject: subject,
      };
      // 将新数据添加到数据源中
      this.pagination.records.push(newData);
    }
  },
};
</script>
<style lang="less" scoped>
.exam {
  padding: 0px 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit{
    margin-left: 20px;
  }
}
</style>
