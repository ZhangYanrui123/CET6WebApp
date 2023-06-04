package com.nksp.backend.mapper;

import com.nksp.backend.vo.LoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select * from user where uname = #{uname}")
    LoginInfo findById(Integer uame);
}
