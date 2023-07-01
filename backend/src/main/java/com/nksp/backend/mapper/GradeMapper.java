package com.nksp.backend.mapper;

import com.nksp.backend.entity.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GradeMapper {
    @Select("select * from grade where gid = #{gid}")
    Grade findById(Integer gid);

    @Select("select * from grade where uuid = #{uuid}")
    List<Grade> getAllGrade(Integer uuid);

    @Update("update grade set gdictation = #{gdictation}, gwriting = ${gwriting}, gtotal = #{gtotal}" +
            "where uuid = #{uuid} and eid = #{eid}")
    int updateSOScore(Grade grade);

    @Update("update grade set gwriting = ${gwriting}" +
            "where uuid = #{uuid} and eid = #{eid}")
    int updateSubScore(Grade grade);

    @Select("select * from grade where uuid = #{uuid} and eid = #{eid}")
    Grade findByUidEid(int uuid, int eid);
}
