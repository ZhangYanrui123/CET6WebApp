package com.nksp.backend.mapper;

import com.nksp.backend.entity.Record;
import com.nksp.backend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.nksp.backend.vo.RegisterInfo;

@Mapper
public interface UserMapper {
    @Select("select * from user where uuid = #{userId}")
    User findById(Integer userId);

    @Select("select * from user where uname = #{uname}")
    User findByName(String name);

    // 默认注册的是学生
    @Insert("insert into user(uname, usex, ubirth, utel, umail, udoctype, udocno, upwd, ustate, utype) values (#{uname}, #{usex}, #{ubirth}, #{utel},#{umail},#{udoctype},#{udocno},#{password},1,2)")
    int insertUser(RegisterInfo info);


}
