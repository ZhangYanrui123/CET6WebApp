package com.nksp.backend.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class QuestionJson {
    String questionText;
    List<Map<String, String>> optionTextList;
    String correctAnswer;
}
