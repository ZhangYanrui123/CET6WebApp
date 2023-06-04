package com.nksp.backend.entity;

import lombok.Data;

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
}
