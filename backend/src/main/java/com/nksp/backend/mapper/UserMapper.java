package com.nksp.backend.mapper;

import com.nksp.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where uuid = #{userId}")
    User findById(Integer userId);
}
