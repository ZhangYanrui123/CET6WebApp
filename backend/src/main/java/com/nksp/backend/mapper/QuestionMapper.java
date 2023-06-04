package com.nksp.backend.mapper;

import com.nksp.backend.entity.Question;
import com.nksp.backend.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionMapper {
    @Select("select * from question where qid = #{qid}")
    Question findById(Integer qid);

    @Insert("insert into question(qtype, qdifficulty, qstem, qnum, qbelong) " +
            "values(#{qtype},#{qdifficulty},#{qstem},#{qnum},#{qbelong})")
    @Options(useGeneratedKeys=true,keyProperty="qid")
    int insertQues(Question question);
}