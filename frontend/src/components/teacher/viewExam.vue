<!--考生答题界面-->
<template>
    <div class="answer">
        <div class="top">
            <span>全卷共<b class = "count">{{this.totalcount}}</b>题</span>
        </div>
        <div class="answerArea">
            <div>请考生在规定时间内完成所有题目，并点击提交</div>
            <div class="content">
                <ul class = "question">
                    <li v-for="(item,index) in this.questions" :key="index">
                        <span class = "stem" v-if = "item.qnum>0">{{item.qstem}}</span>
                        <span class = "stem" v-if = "item.qnum==0">{{index}}. {{item.qstem}}</span>
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
            seconds: 60, //考试剩余时间
            user: {
                uname : this.$cookies.get("uname"),
                uuid : this.$cookies.get("uuid")
            },
            eid: null,
            pid:null,
            count: 0,//题目总数
            totalcount: 0,
            examData: { //考试信息
            },
            topic: {  //试卷信息
            },
            // questions: [{qnum: 2,qtype: 4,qstem: "yuedu"},
            //     {qnum: 0,qtype: 7,qstem: "yueduxiaoti1"},
            //     {qnum: 0,qtype: 7,qstem: "yueduxiaoti2"},
            //     {qnum: 0,qtype: 5,qstem: "fanyi"}
            // ],  //所有试题
            questions: [],
            number: 1,
            answer: [], //学生答案
            // objectOption : [
            //     [null],
            //     [{ocontent: "a"},{ocontent: "b"},{ocontent: "c"},{ocontent: "d"},],
            //     [{ocontent: "a1"},{ocontent: "b1"},{ocontent: "c1"},{ocontent: "d1"},],
            // ],  //所有客观题选项
            objectOption: [],
            objectAnswer: null,  //学生选择题作答
            subjectAnswer: []   //学生主观题作答
        }
    },
    created() {
        //this.getCookies()
        //this.getExamData()
    },
    mounted() {
        this.eid = this.$route.query.eid;
        console.log("eid")
        console.log(this.eid);
        this.getExamData();
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
            this.$axios({
                url: 'http://127.0.0.1:8081/api/exam',
                method: 'post',
                data: {
                    eid: this.eid
                }}).then(res => {  //通过eid请求试卷详细信息（用到了结束时间）
                this.examData = res.data.data //获取考试详情
            })
            this.$axios({
                url: 'http://127.0.0.1:8081/api/getpid',
                method: 'post',
                data: {
                    eid: this.eid
                }}).then(res => {  //通过eid获取试卷号
                let paperId = res.data.data
                this.pid = paperId
                this.$axios({
                    url: 'http://127.0.0.1:8081/api/getpaper',
                    method: 'post',
                    data: {pid: paperId}
                }).then(res => {  //通过paperId获取试题题目信息(题号和分数)
                    this.topic = res.data.data
                    let qids = this.topic.pquestions.split("&")
                    console.log("qids len")
                    console.log(qids.length)
                    for(var i = 0; i < qids.length; i++){
                        let qid = Number(qids[i])
                        this.$axios({url: 'http://127.0.0.1:8081/api/getq',
                            method: 'post',
                            data: {qid: qid}
                        }).then(res =>{ //通过subqid获取问题(题号，类型，题干，qnum)
                            if(this.isChoice(res.data.data.qtype)){
                                this.questions.push(res.data.data)
                                this.count+=1
                                this.totalcount+=1
                                if(this.questions[this.count-1].qnum>0){
                                    if(this.isChoice(this.questions[this.count-1].qtype)){
                                        this.$axios({url: 'http://127.0.0.1:8081/api/getsubqlist',
                                            method: 'post',
                                            data: {
                                                qid: this.questions[this.count-1].qid
                                            }
                                        }).then(res =>{//通过题干qid，循环查小题
                                            let sqids = res.data.data
                                            console.log("sqids len")
                                            console.log(sqids.length)
                                            this.totalcount-=1;
                                            for(var j = 0; j < sqids.length; j++){
                                                this.$axios({url: 'http://127.0.0.1:8081/api/getq',
                                                    method: 'post',
                                                    data: {qid: sqids[j]}
                                                }).then(res =>{//通过qid获取小题(题号，类型，题干，qnum)
                                                    this.questions.push(res.data.data)
                                                    this.count+=1
                                                    this.totalcount+=1
                                                    console.log('count(sub)')
                                                    console.log(this.count)
                                                })
                                            }
                                        })
                                    }
                                }
                            }
                        })
                    }
                })
            })
            return new Promise((resolve) => {
                setTimeout(() => {
                    // 在这里调用 getOptions() 和 getExamDataSub() 函数
                    this.getOptions();
                    this.getExamDataSub();
                    resolve(1);
                }, 1000);
            });
        },
        getExamDataSub() { //获取当前试卷所有信息
            this.count = 0
            this.$axios({
                url: 'http://127.0.0.1:8081/api/exam',
                method: 'post',
                data: {
                    eid: this.eid
                }}).then(res => {  //通过eid请求试卷详细信息（用到了结束时间）
                this.examData = res.data.data //获取考试详情
            })
            this.$axios({
                url: 'http://127.0.0.1:8081/api/getpid',
                method: 'post',
                data: {
                    eid: this.eid
                }}).then(res => {  //通过eid获取试卷号
                let paperId = res.data.data
                this.pid = paperId
                this.$axios({
                    url: 'http://127.0.0.1:8081/api/getpaper',
                    method: 'post',
                    data: {pid: paperId}
                }).then(res => {  //通过paperId获取试题题目信息(题号和分数)
                    this.topic = res.data.data
                    let qids = this.topic.pquestions.split("&")
                    console.log("qids len")
                    console.log(qids.length)
                    for(var i = 0; i < qids.length; i++){
                        let qid = Number(qids[i])
                        this.$axios({url: 'http://127.0.0.1:8081/api/getq',
                            method: 'post',
                            data: {qid: qid}
                        }).then(res =>{ //通过subqid获取问题(题号，类型，题干，qnum)
                            if(!this.isChoice(res.data.data.qtype)){
                                this.questions.push(res.data.data)
                                this.count+=1
                                this.totalcount+=1
                            }
                        })
                    }
                })
            })
            return 1
        },
        getOptions(ret){
            console.log('op count')
            console.log(this.count)
            for(var i = 0; i < this.count; i++){ //初始化选项和答案
                if(this.isChoice(this.questions[i].qtype)&&this.questions[i].qnum==0){
                    this.$axios({url: 'http://127.0.0.1:8081/api/getolist',
                        method: 'post',
                        data: {qid: this.questions[i].qid}}
                    ).then(res =>{//通过qid获取所有选项(选项内容)
                        let options = res.data.data
                        this.objectOption.push(options)
                    })
                    this.answer.push(0)
                }
                else{
                    this.objectOption.push(null)
                    this.answer.push(null)
                }
            }
        },
    }
}
</script>

<style>
ul{
    list-style-type:none
}
.answer .top{
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
.answer .top b{
    color: #ba1111c6;
}
.answer .top .count{
    color: #2868de;
}
.answer .top span{
    padding: 30px;
}
.answerArea{
    width: 100%;
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
    margin-top: 25px;
    width: 80%;
}
</style>
