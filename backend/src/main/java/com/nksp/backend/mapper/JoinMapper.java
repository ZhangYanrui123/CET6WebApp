package com.nksp.backend.mapper;

import com.nksp.backend.entity.Exam;
import com.nksp.backend.entity.Join;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JoinMapper {
    @Select("select * from joins where jid = #{jid}")
    Join findById(Integer jid);
}
