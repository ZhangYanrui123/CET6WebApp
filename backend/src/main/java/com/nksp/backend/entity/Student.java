package com.nksp.backend.entity;

import lombok.Data;

import java.time.Year;
import java.util.Date;
@Data
public class Student {
    int uuid;
    String suniversity;
    String scollege;
    String smajority;
    int sno;
    String  sclass;
    Year sgrade;

    public Student(int uuid, String suniversity, String scollege, String smajority, int sno, String sclass, Year sgrade) {
        this.uuid = uuid;
        this.suniversity = suniversity;
        this.scollege = scollege;
        this.smajority = smajority;
        this.sno = sno;
        this.sclass = sclass;
        this.sgrade = sgrade;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public void setSuniversity(String suniversity) {
        this.suniversity = suniversity;
    }

    public void setScollege(String scollege) {
        this.scollege = scollege;
    }

    public void setSmajority(String smajority) {
        this.smajority = smajority;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public void setSgrade(Year sgrade) {
        this.sgrade = sgrade;
    }
}
