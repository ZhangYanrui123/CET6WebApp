package com.nksp.backend.vo;

import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class ManageData {
//    editData: {uuid:null,suniversity : "nku",scollege:"jn",uname:"name",usex:"sex",udoctype:"sfz",udocno:"xxxxxxxxxxxxxxxxxx",
//            sgrade:"20",sclass:"1",smajority:"jsjkxyjs",sno:"2011111",ubirth:"2023/6/4"},
    int uuid;
    String suniversity;
    String scollege;
    String smajority;
    String uname;
    String usex;
    int udoctype;
    String udocno;
    String sgrade;
    String  sclass;
    Date ubirth;
    int sno;
    int utype;
    String utel;
    String umail;

    public void setInfo(User user, Student stu){
        this.uuid = user.getUuid();
        this.uname = user.getUname();
        this.usex = user.getUsex();
        this.udoctype = user.getUdoctype();
        this.udocno = user.getUdocno();
        this.ubirth = user.getUbirth();
        this.suniversity = stu.getSuniversity();
        this.scollege = stu.getScollege();
        this.smajority = stu.getSmajority();
        this.sclass = stu.getSclass();
        this.sgrade = stu.getSclass();
        this.sno = stu.getSno();
        this.utype = user.getUtype();
        this.utel = user.getUtel();
        this.umail = user.getUmail();
    }

}

