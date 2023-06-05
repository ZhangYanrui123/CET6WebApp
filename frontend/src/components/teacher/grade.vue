<template>
    <div class="question-review">
      <h2 class="title">试题批阅</h2>
  
      <!-- 选择题目类型 -->
      <div class="type-selection">
        <label>
          <input type="radio" v-model="questionRecord.questionType" value="writing" /> 写作题
        </label>
        <label>
          <input type="radio" v-model="questionRecord.questionType" value="translation" /> 翻译题
        </label>
      </div>
  
    <!-- 显示题目内容 -->
    <div class="content" v-if="questionRecord.questionContent">
        <!-- 显示题目内容 -->
        <div class="content" v-if="questionRecord.questionContent">
            <h3 class="section-title">题目内容：</h3>
            <div class="question-text">{{ questionRecord.questionContent }}</div>
        </div>
    </div>
  
      <!-- 显示考生答案 -->
      <div class="content" v-if="questionRecord.studentAnswer">
        <h3 class="section-title">考生答案：</h3>
        <p class="answer-text">{{ questionRecord.studentAnswer }}</p>
      </div>
  
      <!-- 评分表单 -->
      <div class="score-section" v-if="questionRecord.questionContent && questionRecord.studentAnswer">
        <h3 class="section-title">评分：</h3>
        <input class="score-input" v-model="score" min="0" max="100" />
        <button class="submit-button" @click="submitScore">提交评分</button>
      </div>
    </div>
  </template>
  
  <script>
//   import axios from 'axios';
  
  import * as url from "url";

export default {
    data() {
      return {
        questionRecord:{
          questionType: 'writing', // 题目类型，'writing'或'translation'
          questionContent: '', // 题目内容
          studentAnswer: '', // 考生答案
        },
          score: 0.0, // 评分
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
      // 从后端获取题目内容和考生答案
      getQuestionAndAnswer() {
        const rid = 14; // 考生的序号，根据实际情况设置
        this.$axios({
            url:"http://127.0.0.1:8081/api/getrecord",
            method: 'post',
            contentType: "application/json;charset=UTF-8",
            data:{rid: rid}
            }).then(response => {
                this.questionRecord = response.data.data
        }).catch(error => {
          console.error(error);
        });
      },
      // 提交评分给后端
      submitScore() {
        const rid = 14; // 考生的序号，根据实际情况设置
          console.log(this.score)
        this.$axios({
            url: `http://127.0.0.1:8081/api/score`,
            method: 'post',
            data: {
                uuid: 1,
                eid: 1,
                qid: 13,
                rid: 13,
                rscore: this.score
            }
        }).then(response => {
          console.log('评分提交成功');
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
  </style>
  