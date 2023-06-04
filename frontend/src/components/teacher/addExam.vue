<!-- 添加考试 -->
<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="考场号">
        <el-input v-model="form.cid"></el-input>
      </el-form-item>
      <el-form-item label="开始时间">
        <el-date-picker
          v-model="form.ebegin"
          type="datetime"
          placeholder="选择日期和时刻"
          style="width: 100%;"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间">
        <el-date-picker
          v-model="form.eend"
          type="datetime"
          placeholder="选择日期和时刻"
          style="width: 100%;"
        ></el-date-picker>
      </el-form-item>
      <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="考试科目">
        <el-select v-model="form.subject">
          <el-option label="六级笔试" value="1"></el-option>
          <el-option label="六级口语" value="2"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">立即创建</el-button>
        <el-button type="text" @click="cancel()">取消</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script>
export default {
  data() {
    return {
      form: { //表单数据初始化
        cid: null,
        description: null,
        institute: null,
        major: null,
        grade: null,
        examDate: null,
        totalTime: null,
        totalScore: null,
        type: null,
        tips: null,
        paperId: null,
      }
    };
  },
  methods: {
    formatTime(date) { //日期格式化
      let year = date.getFullYear()
      let month= date.getMonth()+ 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
      let day=date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
      let hours=date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
      let minutes=date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
      let seconds=date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      // 拼接
      return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
    },
    onSubmit() {
      let examDate = this.formatTime(this.form.examDate)
      this.form.examDate = examDate.substr(0,10)
      // this.$axios(`/api/examManagePaperId`).then(res => {
      //   this.form.paperId = res.data.data.paperId + 1 //实现paperId自增1
      //   this.$axios({
      //     url: '/api/exam',
      //     method: 'post',
      //     data: {
      //       ...this.form
      //     }
      //   }).then(res => {
      //     if(res.data.code == 200) {
      //       this.$message({
      //         message: '数据添加成功',
      //         type: 'success'
      //       })
      //       this.$router.push({path: '/selectExam'})
      //     }
      //   })
      // })
    },
    cancel() { //取消按钮
      this.form = {}
    },
    watch: {
    'form.eend': function(newVal) {
      console.log('form.eend changed:', newVal);
    }
  }
    
  }
};
</script>
<style lang="less" scoped>
.add {
  padding: 0px 40px;
  width: 400px;
}
</style>

