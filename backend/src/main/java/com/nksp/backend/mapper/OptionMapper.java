package com.nksp.backend.mapper;

import com.nksp.backend.entity.Option;
import com.nksp.backend.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OptionMapper {
    @Select("select * from options where oid = #{oid}")
    Option findById(Integer oid);
}