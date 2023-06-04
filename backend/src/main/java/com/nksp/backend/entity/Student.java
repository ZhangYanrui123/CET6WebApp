package com.nksp.backend.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Student {
    int uuid;
    String suniversity;
    String scollege;
    String smajority;
    int sno;
    String  sclass;
    Date sgrade;
}
