package com.nksp.backend.mapper;

import com.nksp.backend.entity.Exam;
import com.nksp.backend.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ExamMapper {
    @Select("select * from examination where eid = #{eid}")
    Exam findById(Integer eid);
}
