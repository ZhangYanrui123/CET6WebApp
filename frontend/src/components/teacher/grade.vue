<template>
    <div class="question-review">
    <h2 class="title">试题批阅</h2>

    <!-- 选择题目类型 -->
    <div class="type-selection" v-if="this.isVisible">
        <el-radio v-model="recordsList[curIdx].qtype" label="6">写作</el-radio>
        <el-radio v-model="recordsList[curIdx].qtype" label="5">翻译</el-radio>
    </div>
    <div class="type-selection" v-else>
        <el-radio >写作</el-radio>
        <el-radio >翻译</el-radio>
    </div>
  
    <!-- 显示题目内容 -->
    <div class="content" v-if="this.isVisible">
        <h3 class="section-title">题目内容：</h3>
        <div class="question-text">{{ recordsList[curIdx].qstem }}</div>
    </div>
    <div class="content" v-else>
        <h3 class="section-title">题目内容：</h3>
        <div class="question-text">没有题目啦</div>
    </div>
  
    <!-- 显示考生答案 -->
    <div class="content" v-if="this.isVisible">
      <h3 class="section-title">考生答案：</h3>
      <p class="answer-text">{{ recordsList[curIdx].ranswer }}</p>
    </div>
    <div class="content" v-else>
        <h3 class="section-title">考生答案：</h3>
        <p class="answer-text">没有答案啦</p>
    </div>

    <!-- 评分表单 -->
    <div class="score-section" v-if="this.isVisible">
      <h3 class="section-title">评分：</h3>
      <input class="score-input" v-model="recordsList[curIdx].rscore" min="0" max="100" />
      <button class="submit-button" @click="submitScore">提交评分</button>
      <button class="next-button"@click="showLastData">上一位</button>
      <button class="next-button"@click="showNextData">下一位</button>
    </div>
    <div class="score-section" v-else>
      <button class="next-button"@click="relax">点击休息</button>
    </div>
  </div>
</template>
  
<script>
//   import axios from 'axios';
  
  import * as url from "url";

export default {
    data() {
      return {
        recordsList:[{
          rid: 0,
          ranswer:"目前没有待批阅试题",   // 考生答案
          qtype: "6",     // 5:traslation, 6:writing
          qstem: "目前没有待批阅试题",    // 题目内容
          rscore: 0.0,   // 评分
          },{
            rid: 0,
            ranswer:"目前没有待批阅试题",   // 考生答案
            qtype: "5 ",     // 5:traslation, 6:writing
            qstem: "目前没有待批阅试题",    // 题目内容
            rscore: 0.0,   // 评分
        }
        ],
        curIdx: 0,      // 当前list的索引
        isVisible: true,
        relaxIdx: 0,
        userInfo: {
            uname : 'xuechen',
            uuid : 1,
            urole: 0,
        },
      };
    },
    created() {
        this.getQuestionAndAnswer()
    },
    methods: {
      showNextData(){
          this.curIdx++;
          if(this.curIdx>=this.recordsList.length){
              this.isVisible = false;
          }
      },
      showLastData(){
          this.curIdx--;
          if(this.curIdx<0){
              this.curIdx++;
              this.$message.info("到头了");
          }
      },
      relax(){
          this.relaxIdx++;
          // 信息消息提示框
          this.$message.info("你已经休息了"+this.relaxIdx+"次！");
      },
      // 从后端获取题目内容和考生答案
      getQuestionAndAnswer() {
        this.$axios({
            url:"http://127.0.0.1:8081/api/teacher/getAllRecords",
            method: 'post',
            }).then(response => {
                this.recordsList = response.data.data
        }).catch(error => {
          console.error(error);
        });
      },
      // 提交评分给后端
      submitScore() {
          console.log(this.recordsList[this.curIdx])
        this.$axios({
            url: `http://127.0.0.1:8081/api/teacher/updateScore`,
            method: 'post',
            data: {
                ...this.recordsList[this.curIdx]
            },

        }).then(response => {
            this.$message.warning("提交评分成功");
        }).catch(error => {
          console.error(error);
        });
      }
    },
    // watch: {
    //   questionType() {
    //     this.questionContent = '';
    //     this.studentAnswer = '';
    //
    //     if (this.questionType) {
    //       this.getQuestionAndAnswer();
    //     }
    //   }
    // }
  };
</script>

<style scoped>
.question-review {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.title {
  text-align: center;
  margin-bottom: 20px;
}

.type-selection {
  margin-bottom: 20px;
}

.section-title {
  font-size: 18px;
  margin-bottom: 10px;
}

.question-text {
  word-wrap: break-word; /* 超出屏幕宽度自动换行 */
}

.answer-text {
  word-wrap: break-word; /* 超出屏幕宽度自动换行 */
}

.score-section {
  margin-top: 20px;
  text-align: center;
}

.score-input {
  padding: 5px 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  outline: none;
}

.submit-button {
  margin-left: 10px;
  padding: 5px 10px;
  border: none;
  border-radius: 5px;
  background-color: #4caf50;
  color: #fff;
  cursor: pointer;
}

.next-button {
    margin-left: 10px;
    padding: 5px 10px;
    border: none;
    border-radius: 5px;
    background-color: blue;
    color: #fff;
    cursor: pointer;
}
</style>
