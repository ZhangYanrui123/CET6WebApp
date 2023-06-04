package com.nksp.backend.mapper;

import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper {
    @Select("select * from teacher where uuid = #{userId}")
    Teacher findById(Integer userId);
}
