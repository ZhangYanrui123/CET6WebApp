package com.nksp.backend.mapper;

import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.User;
import com.nksp.backend.vo.RegisterInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {
    @Select("select * from student where uuid = #{userId}")
    Student findById(Integer userId);

    @Insert("insert into student(uuid, suniversity, scollege, smajority, sno, sclass, sgrade) values (#{uuid}, #{suniversity}, #{scollege}, #{smajority}, #{sno},#{sclass},#{sgrade})")
    int insertStudent(Student student);
}
