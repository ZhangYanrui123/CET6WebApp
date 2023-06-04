<!--考生答题界面-->
<template>
    <div class="answer">
        <div class="top">
            <span>全卷共<b class = "count">{{this.count}}</b>题</span>
            <span>剩余时间：<b>{{Math.floor(seconds/3600)}}</b>小时<b>{{Math.floor(seconds/60)%60}}</b>分钟<b>{{seconds%60}}</b>秒</span>
        </div>
        <div class="answerArea">
            <div class="content">
                <ul class = "question">
                    <li v-for="(item,index) in this.questions" :key="index">
                        <span class = "stem" v-if = item.qstem>{{item.qstem}}</span>
                        <div v-if="isChoice(item.qtype)&&item.qnum==0"> 
                            <el-radio-group v-model="answer[index]">
                                <el-radio v-for="(option,i) in objectOption[index]" :label="i+1">{{String.fromCharCode(65+i)}}.{{option.ocontent}}</el-radio>
                            </el-radio-group>
                        </div>
                        <div v-if="!isChoice(item.qtype)&&item.qnum==0">
                            <el-input class = "input" type="textarea" placeholder="请输入答案" v-model="answer[index]"></el-input>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="final">
                <el-button @click="toCommit = true">结束考试</el-button>
            </div>
        </div> 
        <el-dialog title='提交试卷' center :visible.sync='toCommit'>
                确定要提交试卷吗？<br/>
                <el-button @click = "toCommit = false;commit()">确定</el-button>
                <el-button @click = "toCommit = false">取消</el-button>
            </el-dialog>
        </div>
    </template>
  
  <script>
  export default {
    data() {
      return {
        toCommit: false,
        seconds: 100, //考试剩余时间
        user: {
            uname : null,
            uuid : null
        },
        eid: null,
        count: 10,//题目总数
        examData: { //考试信息
        },
        topic: {  //试卷信息
        },
        questions: [{qnum: 2,qtype: 4,qstem: "yuedu"},
            {qnum: 0,qtype: 7,qstem: "yueduxiaoti1"},
            {qnum: 0,qtype: 7,qstem: "yueduxiaoti2"},
            {qnum: 0,qtype: 5,qstem: "fanyi"}
        ],  //所有试题
        number: 1, 
        answer: [], //学生答案
        objectOption : [
            [null],
            [{ocontent: "a"},{ocontent: "b"},{ocontent: "c"},{ocontent: "d"},],
            [{ocontent: "a1"},{ocontent: "b1"},{ocontent: "c1"},{ocontent: "d1"},],
        ],  //所有客观题选项
        objectAnswer: null,  //学生选择题作答
        subjectAnswer: []   //学生主观题作答
      }
    },
    created() {
      //this.getCookies()
      //this.getExamData()
      //this.countDown()
    },
    methods: {
      isChoice(type){
        return type != 0 && type != 5 && type != 6
      },
      getCookies() {  //获取cookie
            this.user.uname = this.$cookies.get("uname")
            this.user.uuid = this.$cookies.get("uuid")
        },
      getExamData() { //获取当前试卷所有信息
        this.eid = this.$route.query.eid //获取路由传递过来的试卷编号
        this.$axios({url: '/api/score',method: 'post',data: {eid: this.eid}}).then(res => {  //通过eid请求试卷详细信息（用到了结束时间）
            this.examData = res.data.data //获取考试详情
            let now = new Date()
            let end = new Date(parseInt(this.examData.eend.replace("/Date(", "").replace(")/" , "").split( "+")[0]));
            this.seconds = (end - now)/1000 //计算剩余秒数
        })
        this.$axios({url: '/api/score',method: 'post',data: {eid: this.eid}}).then(res => {  //通过eid获取试卷号
            let paperId = res.data.data
            this.$axios({url: '/api/score',method: 'post',data: {pid: paperId}}).then(res => {  //通过paperId获取试题题目信息(题号和分数)
                this.topic = res.data.data
                let qids = topic.pquestions.split("&")
                for(var i = 0; i < qids.length; i++){
                    let qid = Number(qids[i])
                    this.$axios({url: '/api/score',method: 'post',data: {qid: qid}}).then(res =>{ //通过qid获取问题(题号，类型，题干，qnum)
                        this.questions.push(res.data.data)
                        this.count+=1
                        if(this.questions[this.count].qnum>0){
                            this.$axios({url: '/api/score',method: 'post',data: {qid: this.questions[this.count].qid}}).then(res =>{//通过qid获取小题qid
                                let sqids = res.data.data
                                for(var j = 0; j < sqids.length; j++){
                                    this.$axios({url: '/api/score',method: 'post',data: {qid: sqids[j]}}).then(res =>{//通过qid获取小题(题号，类型，题干，qnum)
                                        this.questions.push(res.data.data)
                                        this.count+=1
                                    })
                                }
                            })
                        }
                    })
                }

                for(var i = 0; i < this.count; i++){ //初始化选项和答案
                    if(isChoice(this.questions[i].qtype)&&this.questions[i].qnum==0){
                        this.$axios({url: '/api/score',method: 'post',data: {qid: this.questions[i].qid}}).then(res =>{//通过qid获取所有选项(选项内容)
                            let options = res.data.data
                            this.objectOption.push(options)
                        })
                        answer.push(0)
                    }
                    else{
                        this.objectOption.push(null)
                        answer.push(null)
                    }
                }
            })
          })
      },
      commit() { 
        for(var i = 0; i < this.questions.length; i++){
            //获取选择题答案
            if(isChoice(this.questions[i].qtype)&&this.questions[i].qnum==0){
                if(this.answer[i] == 0){
                     this.objectAnswer = this.objectAnswer == null? "0" : this.objectAnswer + "&0"
                }
                else {
                    this.objectAnswer = this.objectAnswer == null? String.fromCharCode(64+answer[i]) : this.objectAnswer + "&" + String.fromCharCode(64+answer[i])
                }
            }
            //获取主观题答案
            else if(!isChoice(this.questions[i].qtype)&&this.questions[i].qnum==0){
                this.$axios({url: '/api/score', method: 'post',  //传输主观题答案
                data: {
                    uuid: this.user.uuid,
                    eid: this.eid,
                    qid: this.questions[i].qid,
                    ranswer: this.answer[i]
                }})
            }
        }
        this.$axios({url: '/api/score', method: 'post',  //传输客观题答案字符串
                    data: {
                        uuid: this.user.uuid,
                        eid: this.eid,
                        aobjective: this.objectAnswer
                    }})
        this.$axios({url: '/api/score', method: 'post',  //设置用户的该考试状态为结束
                    data: {
                        uuid: this.user.uuid,
                        eid: this.eid
                    }})
      },
      countDown () { //倒计时
        setInterval(() => {
          this.seconds -= 1
          if(this.seconds == 0) {
            this.$message("考试已结束",{
                type: 'warning'
            })
            this.commit()
          }
        },1000)
      }
    }
  }
  </script>

  <style>
  ul{
  list-style-type:none
  }
  .top{
    height: 70px;
    font-size: 30px;
    background-color: #3e73d69b;
    box-shadow: 1px 5px blur #840ada4c;
    font-family: SimSun;
    color: #000;
    display: flex;
	align-items: center;
    justify-content: center;
  }
  .top b{
    color: #ba1111c6;
  }
  .top .count{
    color: #2868de;
  }
  .top span{
    padding: 30px;
  }
  .answerArea{
    width: 70%;
    display: inline-block;
    vertical-align: middle;
  }
  .content .question li{
    margin: 0px 20px;
    padding: 15px;
    border-bottom : 1px solid #ada5a54f;
  }
  .content .question .el-radio-group{
    display: flex;
  }
  .content .question .stem{
    display: flex;
    margin: 0 5%;
  }
  .content .question .el-radio{
    width:15%;
    margin-top: 15px;
    margin-right: 0px;
    padding-right:0px;
  }
  .content .question .input{
    margin-top: 15px;
    width: 90%;
  }
  </style>
  