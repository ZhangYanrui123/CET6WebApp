package com.nksp.backend.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Fee {
    int fid;
    int fstate;
    int uuid;
    Date fgenerated;
    Date fpaid;
    int fsubject;
    int famount;
}
