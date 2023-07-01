package com.nksp.backend.vo;

import lombok.Data;

import java.time.Year;

@Data
public class StudentInfo {
    public String getSuniversity() {
        return suniversity;
    }

    public void setSuniversity(String suniversity) {
        this.suniversity = suniversity;
    }

    public String getScollege() {
        return scollege;
    }

    public void setScollege(String scollege) {
        this.scollege = scollege;
    }

    public String getSmajority() {
        return smajority;
    }

    public void setSmajority(String smajority) {
        this.smajority = smajority;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public Year getSgrade() {
        return sgrade;
    }

    public void setSgrade(Year sgrade) {
        this.sgrade = sgrade;
    }

    public String getUtel() {
        return utel;
    }

    public void setUtel(String utel) {
        this.utel = utel;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public int getUdoctype() {
        return udoctype;
    }

    public void setUdoctype(int udoctype) {
        this.udoctype = udoctype;
    }

    public String getUdocno() {
        return udocno;
    }

    public void setUdocno(String udocno) {
        this.udocno = udocno;
    }

    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    String suniversity;
    String scollege;
    String smajority;
    int sno;
    String uname;
    String sclass;
    Year sgrade;
    String utel;

    String usex;
    int udoctype;
    String udocno;
    String umail;
    String upwd;

    public void setStudentInfo(String u, String co, String m, int no, String n, String c, Year g, String t) {
        this.suniversity = u;
        this.scollege = co;
        this.smajority = m;
        this.sno = no;
        this.uname = n;
        this.sclass = c;
        this.sgrade = g;
        this.utel = t;
    }

    public void setStudentUserInfo(String usex, int udoctype, String udocno, String umail, String upwd){
        this.usex = usex;
        this.udoctype = udoctype;
        this.udocno = udocno;
        this.umail = umail;
        this.upwd = upwd;
    }
}
