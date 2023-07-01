package com.nksp.backend.util;

import com.nksp.backend.entity.Paper;

public class AddQuestionIntoPaper {
    public static boolean addQuestionIntoPaper(Paper paper, Integer qid){
        String curQuestions = paper.getPquestions();
        if(curQuestions == null)
            curQuestions = ""+qid;
        else
            curQuestions = curQuestions + "&"+qid;
        paper.setPquestions(curQuestions);
        return true;
    }
}
