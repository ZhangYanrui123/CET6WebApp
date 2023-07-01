package com.nksp.backend.entity;

import lombok.Data;

@Data
public class Record {
    int rid;
    int uuid;
    int eid;
    int qid;
    String ranswer ;
    float rscore;

    public Record() {
    }

    public Record(int rid, int uuid, int eid, int qid, String ranswer, float rscore) {
        this.rid = rid;
        this.uuid = uuid;
        this.eid = eid;
        this.qid = qid;
        this.ranswer = ranswer;
        this.rscore = rscore;
    }
}
