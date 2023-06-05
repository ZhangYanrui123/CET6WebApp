package com.nksp.backend.mapper;

import com.nksp.backend.entity.Answer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AnswerMapper {
    @Select("select * from answer where aid = #{aid}")
    Answer findById(Integer aid);

    @Insert("insert into answer(pid, uuid, eid, aobjective) values(#{pid}, #{uuid}, #{eid}, #{aobjective})")
    int insertAnswer(Answer answer);
}