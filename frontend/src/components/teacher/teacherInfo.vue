<template>
    <section class="add">
      <h2 class="title">教师个人信息</h2>
      <div class="info-row">
        <label>ID:</label>
        <span>{{ teacher.id }}</span>
      </div>
      <div class="info-row">
        <label>大学:</label>
        <span v-if="!isEditing.university">{{ teacher.university }}</span>
        <el-input
          class="input-field"
          v-model="temp.university"
          v-else
        ></el-input>
        <el-button
          v-if="!isEditing.university"
          class="edit-button"
          @click="isEditing.university = true"
        >编辑</el-button>
        <el-button
          v-else
          class="save-button"
          @click="saveTeacher('university')"
        >保存</el-button>
      </div>
      <div class="info-row">
        <label>学院:</label>
        <span v-if="!isEditing.college">{{ teacher.college }}</span>
        <el-input
          class="input-field"
          v-model="temp.college"
          v-else
        ></el-input>
        <el-button
          v-if="!isEditing.college"
          class="edit-button"
          @click="isEditing.college = true"
        >编辑</el-button>
        <el-button
          v-else
          class="save-button"
          @click="saveTeacher('college')"
        >保存</el-button>
      </div>
      <div class="info-row">
        <label>工号:</label>
        <span v-if="!isEditing.employeeId">{{ teacher.employeeId }}</span>
        <el-input
          class="input-field"
          v-model="temp.employeeId"
          v-else
        ></el-input>
        <el-button
          v-if="!isEditing.employeeId"
          class="edit-button"
          @click="isEditing.employeeId = true"
        >编辑</el-button>
        <el-button
          v-else
          class="save-button"
          @click="saveTeacher('employeeId')"
        >保存</el-button>
      </div>
      <div class="info-row">
        <label>职称:</label>
        <span v-if="!isEditing.title">{{ teacher.title }}</span>
        <el-input
          class="input-field"
          v-model="temp.title"
          v-else
        ></el-input>
        <el-button
          v-if="!isEditing.title"
          class="edit-button"
          @click="isEditing.title = true"
        >编辑</el-button>
        <el-button
          v-else
          class="save-button"
          @click="saveTeacher('title')"
        >保存</el-button>
      </div>
      <el-button class="submit-button" @click="submitTeacher">提交</el-button>
    </section>
  </template>
  
  <script>
  export default {
    data() {
      return {
        teacher: {
          id: '0001', // 在这里设置教师ID的初始值
          university: '南开大学',
          college: '计算机学院',
          employeeId: '1111111',
          title: '辅导员'
        },
        isEditing: {
          university: false,
          college: false,
          employeeId: false,
          title: false
        },
        temp: {
          university: '',
          college: '',
          employeeId: '',
          title: ''
        }
      };
    },
    methods: {
      saveTeacher(field) {
        if (this.temp[field] !== '') {
          this.teacher[field] = this.temp[field];
        }
        this.temp[field] = '';
        this.isEditing[field] = false;
      },
      submitTeacher() {
        // 调用后端API提交教师信息
        // 这里可以使用axios或其他HTTP库发送POST请求
        // 例如：axios.post('/api/teachers', this.teacher)
        // 提交成功后的处理逻辑可以在这里编写
  
        // 清空临时值
        this.temp = {
          university: '',
          college: '',
          employeeId: '',
          title: ''
        };
      }
    }
  };
  </script>
  
  <style lang="less" scoped>
  .add {
    padding: 0px 40px;
    width: 400px;
  }
  
  .title {
    margin-bottom: 20px;
  }
  
  .info-row {
    margin-bottom: 15px;
  }
  
  label {
    display: inline-block;
    width: 80px;
    font-weight: bold;
  }
  
  .input-field {
    width: 200px;
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 3px;
  }
  
  .edit-button,
  .save-button,
  .submit-button {
    display: inline-block;
    padding: 6px 10px;
    margin-left: 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .save-button:hover,
  .submit-button:hover {
    background-color: #0056b3;
  }
  </style>
  