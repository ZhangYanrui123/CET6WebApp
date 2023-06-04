<template>
    <div class="question-review">
      <h2 class="title">试题批阅</h2>
  
      <!-- 选择题目类型 -->
      <div class="type-selection">
        <label>
          <input type="radio" v-model="questionType" value="writing" /> 写作题
        </label>
        <label>
          <input type="radio" v-model="questionType" value="translation" /> 翻译题
        </label>
      </div>
  
    <!-- 显示题目内容 -->
    <div class="content" v-if="questionContent">
        <!-- 显示题目内容 -->
        <div class="content" v-if="questionContent">
            <h3 class="section-title">题目内容：</h3>
            <div class="question-text">{{ questionContent }}</div>
        </div>
    </div>
  
      <!-- 显示考生答案 -->
      <div class="content" v-if="studentAnswer">
        <h3 class="section-title">考生答案：</h3>
        <p class="answer-text">{{ studentAnswer }}</p>
      </div>
  
      <!-- 评分表单 -->
      <div class="score-section" v-if="questionContent && studentAnswer">
        <h3 class="section-title">评分：</h3>
        <input class="score-input" type="number" v-model="score" min="0" max="100" />
        <button class="submit-button" @click="submitScore">提交评分</button>
      </div>
    </div>
  </template>
  
  <script>
//   import axios from 'axios';
  
  export default {
    data() {
      return {
        questionType: '', // 题目类型，'writing'或'translation'
        questionContent: '1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', // 题目内容
        studentAnswer: '111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', // 考生答案
        score: 0 // 评分
      };
    },
    methods: {
      // 从后端获取题目内容和考生答案
      getQuestionAndAnswer() {
        const uid = 123; // 考生的序号，根据实际情况设置
        axios.get(`/api/question/${uid}?type=${this.questionType}`).then(response => {
          this.questionContent = response.data.questionContent;
          this.studentAnswer = response.data.studentAnswer;
        }).catch(error => {
          console.error(error);
        });
      },
      // 提交评分给后端
      submitScore() {
        const uid = 123; // 考生的序号，根据实际情况设置
        axios.post(`/api/score/${uid}`, { score: this.score }).then(response => {
          console.log('评分提交成功');
        }).catch(error => {
          console.error(error);
        });
      }
    },
    watch: {
      questionType() {
        this.questionContent = '';
        this.studentAnswer = '';
  
        if (this.questionType) {
          this.getQuestionAndAnswer();
        }
      }
    }
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
  