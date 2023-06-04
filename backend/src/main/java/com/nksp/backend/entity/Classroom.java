package com.nksp.backend.entity;

import lombok.Data;
import java.util.Date;
@Data
public class Classroom {
    int cid;
    String cuniversity;
    String cclassroom;
    Date copen;
    Date cclose;
}
