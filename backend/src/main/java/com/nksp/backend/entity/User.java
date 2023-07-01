package com.nksp.backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;

@Data
public class User {
    int uuid;
    String uname;
    String usex;
    Date ubirth;
    String utel;
    String umail;
    int udoctype;
    String udocno;
    String upwd;
    int ustate;
    int utype;

    public User() {
    }

    public User(int uuid, String uname, String usex, Date ubirth, String utel, String umail, int udoctype, String udocno, String upwd, int ustate, int utype) {
        this.uuid = uuid;
        this.uname = uname;
        this.usex = usex;
        this.ubirth = ubirth;
        this.utel = utel;
        this.umail = umail;
        this.udoctype = udoctype;
        this.udocno = udocno;
        this.upwd = upwd;
        this.ustate = ustate;
        this.utype = utype;
    }

    public void updateInfo(String usex, String utel, String umail, int udoctype, String udocno, String upwd){
        this.usex = usex == null ? this.usex : usex;
        this.utel = utel == null ? this.utel : utel;
        this.umail = umail == null ? this.umail : umail;
        this.udoctype = udoctype == 0 ? this.udoctype : udoctype;
        this.udocno = udocno == null ? this.udocno : udocno;
        this.upwd = upwd == null ? this.upwd : upwd;
    }
}
