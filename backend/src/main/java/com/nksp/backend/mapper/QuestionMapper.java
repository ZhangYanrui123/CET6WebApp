package com.nksp.backend.mapper;

import com.nksp.backend.entity.Question;
import com.nksp.backend.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionMapper {
    @Select("select * from question where qid = #{qid}")
    Question findById(Integer qid);
}