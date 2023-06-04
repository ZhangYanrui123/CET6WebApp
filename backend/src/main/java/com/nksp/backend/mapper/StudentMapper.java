package com.nksp.backend.mapper;

import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {
    @Select("select * from student where uuid = #{userId}")
    Student findById(Integer userId);
}
