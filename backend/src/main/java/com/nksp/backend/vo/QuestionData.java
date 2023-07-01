package com.nksp.backend.vo;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.List;

@Data
public class QuestionData {
    int eid;
    String stem;
    List<Object> options;
}
