package com.nksp.backend.entity;

import lombok.Data;

@Data
public class Option {
    int oid;
    int qid;
    String ocontent;
    boolean oisright;

    public void setOption(int qid, String ocontent, boolean oisright){
        this.qid = qid;
        this.ocontent = ocontent;
        this.oisright = oisright;
    }
}
