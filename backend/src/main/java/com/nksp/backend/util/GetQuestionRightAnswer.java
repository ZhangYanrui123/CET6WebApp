package com.nksp.backend.util;

import com.nksp.backend.entity.Option;
import com.nksp.backend.entity.Question;
import com.nksp.backend.serviceimpl.OptionServiceImpl;

import java.util.List;

public class GetQuestionRightAnswer {
    public static String getAnswer(Question question, int qid, OptionServiceImpl optionService){
        // 默认是一个题，没有子问题
        System.out.println(qid);
        System.out.println(question);
        List<Option> optionList = optionService.getOptionList(qid);
        int cnt = 0;
        char c = 'A';
        for(Option op: optionList){
            if(op.isOisright()){
                c += cnt;
                break;
            }
            cnt++;
        }
        return String.valueOf(c);
    }
}
