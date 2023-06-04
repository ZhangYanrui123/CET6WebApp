package com.nksp.backend.mapper;

import com.nksp.backend.entity.Classroom;
import com.nksp.backend.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClassroomMapper {
    @Select("select * from classroom where cid = #{cid}")
    Classroom findById(Integer cid);
}
