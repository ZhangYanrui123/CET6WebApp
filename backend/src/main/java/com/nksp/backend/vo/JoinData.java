package com.nksp.backend.vo;

import com.nksp.backend.util.TimeHelper;
import lombok.Data;

import java.util.Date;

@Data
public class JoinData {
    //获取用户可报名信息，需要考试eid，科目，开始时间，结束时间，报名状态和金额
    int eid;
    int esubject;
    String ebegin;
    String eend;
    int jstate;
    float famount;

    public void setInfo(int eid, int esubject, Date estart, Date eend, int jstate, float famount){
        this.eid = eid;
        this.esubject = esubject;
        this.ebegin = TimeHelper.getTime(estart);
        this.eend = TimeHelper.getTime(eend);
        this.jstate = jstate;
        this.famount = famount;
    }
}
