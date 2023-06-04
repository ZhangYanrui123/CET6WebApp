package com.nksp.backend.mapper;

import com.nksp.backend.entity.Blank;
import com.nksp.backend.entity.Classroom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BlankMapper {
    @Select("select * from blank where bid = #{bid}")
    Blank findById(Integer bid);
}