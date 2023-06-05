package com.nksp.backend.vo;

import lombok.Data;

@Data
public class RecordData {
    String questionType;
    String questionContent;
    String studentAnswer;

    public void setRecordData(int type, String content, String answer){
        if(type == 5){
            this.questionType = "translation";
        }else if(type == 6){
            this.questionType = "writing";
        }
        this.questionContent = content;
        this.studentAnswer = answer;
    }
}
