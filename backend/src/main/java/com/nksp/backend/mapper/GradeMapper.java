package com.nksp.backend.mapper;

import com.nksp.backend.entity.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GradeMapper {
    @Select("select * from grade where gid = #{gid}")
    Grade findById(Integer gid);
}
