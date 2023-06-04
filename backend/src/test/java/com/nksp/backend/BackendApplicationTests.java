package com.nksp.backend;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        String s = "{questionText=ewq, options=[{optionTextr=qweerwqe}, {}, {}], correctAnswer=0}";
        s = s.replace("=", ": \"");
        s = s.replace(",", "\",");
        s = s.replace("}", "\"}");
        System.out.println(s);
        JSONObject jsonObject = JSONObject.fromObject(s);
        //再根据json对象中的键得到它的值
        String questionText = jsonObject.getString("questionText");
        String options= jsonObject.getString("options");
        System.out.println(questionText);
        System.out.println(options);

    }

}
