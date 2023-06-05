package com.nksp.backend.entity;

import lombok.Data;

@Data
public class Grade {
    int gid;
    int uuid;
    int eid;
    float gdictation;
    float greading;
    float gwriting;
    float gtotal;

    public void setInfo(int uuid, int eid, float gdictation, float gwriting){
        // 这里就把gdictation当作客观题，gwriting当作主观题了
        this.uuid = uuid;
        this.eid = eid;
        this.gdictation = gdictation;
        this.gwriting = gwriting;
        this.gtotal = gdictation + gwriting;
    }

    public void setSubInfo(int uuid, int eid, float gwriting){
        this.uuid = uuid;
        this.eid = eid;
        this.gwriting = gwriting;
    }
}
