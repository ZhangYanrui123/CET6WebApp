package com.nksp.backend.mapper;

import com.nksp.backend.entity.Student;
import com.nksp.backend.entity.User;
import com.nksp.backend.vo.RegisterInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select * from student where uuid = #{userId}")
    Student findById(Integer userId);

    @Insert("insert into student(uuid, suniversity, scollege, smajority, sno, sclass, sgrade) values (#{uuid}, #{suniversity}, #{scollege}, #{smajority}, #{sno},#{sclass},#{sgrade})")
    int insertStudent(Student student);

    @Select("select* from student")
    List<Student> getAllStudent();

    @Update("update student set suniversity = #{suniversity}, scollege = #{scollege}, smajority = #{smajority}, sclass = #{sclass}, sgrade = #{sgrade} where sno = #{sno}")
    int updateStudent(Student student);

    @Select("select * from student where sno = #{sno}")
    Student findByNo(int sno);

    @Delete("delete from student where sno = #{sno}")
    int deleteStudentByNo(int sno);
}
