package com.nksp.backend.mapper;

import com.nksp.backend.entity.Exam;
import com.nksp.backend.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExamMapper {
    @Select("select * from examination where eid = #{eid}")
    Exam findById(Integer eid);

    @Insert("insert into examination(eid, cid, ebegin, eend, esubject, estate, pid) values (4, #{cid},#{ebegin},#{eend},#{esubject},#{estate},#{pid})")
    int addExam(Exam exam);

    @Select("select * from examination where uuid = #{uuid}")
    List<Exam> findByUid(Integer uuid);

    @Select("select * from examination")
    List<Exam> getAllExams();

    @Delete("delete from examination where eid = #{eid}")
    Integer deleteById(Integer eid);
}
