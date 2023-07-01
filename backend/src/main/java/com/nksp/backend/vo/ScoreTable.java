package com.nksp.backend.vo;

import com.nksp.backend.util.TimeHelper;
import lombok.Data;

import java.util.Date;

@Data
public class ScoreTable {
    int esubject;
    String ebegin;
    String eend;
    float gdictation;
    float gwriting;
    float gtotal;

    public void setInfo(int esubject, Date estart, Date eend, float gdictation, float gwriting){
        this.esubject = esubject;
        this.ebegin = TimeHelper.getTime(estart);
        this.eend = TimeHelper.getTime(eend);
        this.gdictation = gdictation;
        this.gwriting = gwriting;
        if(gwriting < 0)
            this.gtotal = 0;
        else
            this.gtotal = gdictation + gwriting;
    }
}
