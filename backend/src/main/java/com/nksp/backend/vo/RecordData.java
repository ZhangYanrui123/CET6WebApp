package com.nksp.backend.vo;

import lombok.Data;

@Data
public class RecordData {
    int rid;
    String qtype;
    String qstem;
    String ranswer;
    float rscore;

    public RecordData() {
    }

    public void setRecordData(int rid, int type, String qstem, String ranswer, float rscore){
        this.rid = rid;
        if(type == 5){
            this.qtype = "5";
        }else if(type == 6){
            this.qtype = "6";
        }
        this.qstem = qstem;
        this.ranswer = ranswer;
        this.rscore = rscore;
    }
}
