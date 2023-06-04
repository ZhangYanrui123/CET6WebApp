<template>
  <div class="add">
    <el-tabs v-model="activeName">
      <el-tab-pane name="first">
        <span slot="label" class="tab-label"><i class="el-icon-circle-plus"></i>添加试题</span>
        <section class="append">
          <ul>
            <li>
              <span class="label">题目类型:</span>
              <el-select v-model="optionValue" placeholder="请选择题型" class="w150">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </li>
          </ul>
        </section>
      </el-tab-pane>
    </el-tabs>
    <!-- 阅读 -->
    <div class="container" v-if="optionValue === 'reading'">
      <div class="section">
        <textarea v-model="readingContent" class="reading-content" placeholder="请输入阅读内容"></textarea>
      </div>
      <div class="question-section">
        <button @click="addQuestion" class="add-question-btn">添加小题</button>
        <div v-for="(question, questionIndex) in questions" :key="questionIndex" class="question-container">
          <div class="question">
            <textarea v-model="question.questionText" class="question-text" placeholder="请输入小题干"></textarea>
            <button @click="addOption(questionIndex)" class="add-option-btn">添加选项</button>
            <button @click="removeQuestion(questionIndex)" class="remove-question-btn">删除小题</button>
          </div>
          <div class="options">
            <div v-for="(option, optionIndex) in question.options" :key="optionIndex" class="option-container">
              <input v-model="option.optionText" class="option-input" placeholder="请输入选项">
              <button @click="removeOption(questionIndex, optionIndex)" class="remove-option-btn">删除选项</button>
            </div>
            <div class="correct-answer">
              <label>正确答案:</label>
              <select v-model="question.correctAnswer" class="option-select">
                <option v-for="(option, optionIndex) in question.options" :key="optionIndex" :value="optionIndex">
                  {{ String.fromCharCode(65 + optionIndex) }}
                </option>
              </select>
            </div>
          </div>
        </div>
      </div>
      <button @click="submitQuestions" class="submit-btn">提交题目</button>
    </div>
    <!-- 听力 -->
    <div class="container" v-if="optionValue === 'listening'">
      <el-upload
        class="upload-container"
        action="/your-upload-endpoint"
        :on-success="handleUploadSuccess"
        :on-error="handleUploadError"
        :auto-upload="false"
      >
        <el-button size="small" type="primary">点击上传音频文件</el-button>
        <div slot="tip" class="upload-tip">仅支持上传音频文件</div>
      </el-upload>

      <div class="question-section">
        <button @click="addQuestion" class="add-question-btn">添加小题</button>
        <div v-for="(question, questionIndex) in questions" :key="questionIndex" class="question-container">
          <div class="question">
            <textarea v-model="question.questionText" class="question-text" placeholder="请输入小题干"></textarea>
            <button @click="addOption(questionIndex)" class="add-option-btn">添加选项</button>
            <button @click="removeQuestion(questionIndex)" class="remove-question-btn">删除小题</button>
          </div>
          <div class="options">
            <div v-for="(option, optionIndex) in question.options" :key="optionIndex" class="option-container">
              <input v-model="option.optionText" class="option-input" placeholder="请输入选项">
              <button @click="removeOption(questionIndex, optionIndex)" class="remove-option-btn">删除选项</button>
            </div>
            <div class="correct-answer">
              <label>正确答案:</label>
              <select v-model="question.correctAnswer" class="option-select">
                <option v-for="(option, optionIndex) in question.options" :key="optionIndex" :value="optionIndex">
                  {{ String.fromCharCode(65 + optionIndex) }}
                </option>
              </select>
            </div>
          </div>
        </div>
      </div>
      <button @click="submitQuestions" class="submit-btn">提交题目</button>
    </div>
  </div>
</template>

<script>
import { MessageBox } from 'element-ui';
export default {
  data() {
    return {
      activeName: 'first',
      optionValue: 'reading', //题型选中值
      options: [ //题目类型
        {
          value: 'reading',
          label: '阅读题'
        },
        {
          value: 'listening',
          label: '听力题'
        },
        {
          value: 'writing',
          label: '写作题'
        },
        {
          value: 'translating',
          label: '翻译题'
        },
        {
          value: 'choosing',
          label: '选词题'
        },
        {
          value: 'paragraphing',
          label: '段落题'
        },
      ],
      difficultyValue: '',
      difficulty: [],
      changeNumber: '',
      fillNumber: '',
      judgeNumber: '',
      readingContent: '',
      questions: [{ questionText: '', options: [{ optionText: '' }], correctAnswer: null }],
      data:{
        stem: this.readingContent,
        options: this.questions
      }
    };
  },
  methods: {
    create() {
      // 添加题库的逻辑
    },
    addQuestion() {
      this.questions.push({ questionText: '', options: [{ optionText: '' }], correctAnswer: null });
    },
    removeQuestion(questionIndex) {
      this.questions.splice(questionIndex, 1);
    },
    addOption(questionIndex) {
      this.questions[questionIndex].options.push({ optionText: '' });
    },
    removeOption(questionIndex, optionIndex) {
      this.questions[questionIndex].options.splice(optionIndex, 1);
    },

    submitQuestions() {
        this.data.stem = this.readingContent
        this.data.options = this.questions
        console.log(this.data.stem)
        console.log(this.data.options)
      // 发送题目数据给后端的接口
        this.$axios({
            url: 'http://127.0.0.1:8081/api/question/add',
            method: 'post',
            data: this.data
        }).then(res => {
            if(res.status == 200){
                MessageBox.alert("添加成功!")
            }
        })
    },
    handleUploadSuccess(response, file, fileList) {
      // 处理上传成功的逻辑
      // 可以从后端返回的响应中获取音频文件的相关信息
      console.log('上传成功', response);
    },
    handleUploadError(err, file, fileList) {
      // 处理上传失败的逻辑
      console.error('上传失败', err);
    },
  }
};
</script>

<style scoped>
/* 主题样式 */
body {
  background-color: #f3f3fd;
}

.add {
  max-width: 800px;
  padding: 20px;
  margin: 0 auto;
  background-color: #fff;
  box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.1);
  font-family: Arial, sans-serif;
}

h3 {
  margin-bottom: 10px;
}

.append ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

.append li {
  margin-bottom: 10px;
}

.append span {
  display: inline-block;
  min-width: 100px;
}

.w150 {
  width: 150px;
}

.container {
  max-width: 800px;
  padding: 20px;
  margin-top: 20px;
  background-color: #fff;
  box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.1);
  font-family: Arial, sans-serif;
}

.section {
  margin-bottom: 20px;
}

.reading-content {
  width: 100%;
  height: 100px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

.upload-container {
  margin-bottom: 20px;
}

.question-section {
  margin-bottom: 20px;
}

.add-question-btn {
  margin-bottom: 10px;
  background-color: #775dd0;
  color: #fff;
  border: none;
  padding: 6px 12px;
  font-size: 12px;
  border-radius: 4px;
  cursor: pointer;
}

.question-container {
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.question {
  display: flex;
  align-items: center;
  margin-bottom: 5px;
}

.question-text {
  flex-grow: 1;
  height: 30px;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.add-option-btn {
  margin-left: 10px;
  background-color: #2196f3c2;
  color: #fff;
  border: none;
  padding: 6px 12px;
  font-size: 12px;
  border-radius: 4px;
  cursor: pointer;
}

.remove-question-btn {
  margin-left: 10px;
  background-color: #f44336c2;
  color: #fff;
  border: none;
  padding: 6px 12px;
  font-size: 12px;
  border-radius: 4px;
  cursor: pointer;
}

.options {
  margin-top: 10px;
}

.option-container {
  display: flex;
  align-items: center;
  margin-bottom: 5px;
}

.option-input {
  height: 30px;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.option-select {
  height: 30px;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.correct-answer {
  display: flex;
  align-items: center;
  margin-top: 5px;
}

.remove-option-btn {
  margin-left: 10px;
  background-color: #f44336c2;
  color: #fff;
  border: none;
  padding: 6px 12px;
  font-size: 12px;
  border-radius: 4px;
  cursor: pointer;
}

.submit-btn {
  background-color: #2195f3c2;
  color: #fff;
  border: none;
  padding: 6px 12px;
  font-size: 14px;
  border-radius: 4px;
  cursor: pointer;
}

.upload-tip {
  color: #999;
  margin-top: 5px;
}
</style>
