package com.nksp.backend.mapper;

import com.nksp.backend.entity.Paper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PaperMapper {
    @Select("select * from paper where pid = #{pid}")
    Paper findById(Integer pid);

    @Insert("insert into paper(pquestions, pscores) values (#{pquestions}, #{pscores})")
    int insertPaper(Paper paper);

    @Select("select * from paper order by pid desc limit 1")
    Paper findMaxId();

    @Update("update paper set pquestions = #{pquestions} where pid = #{pid}")
    int updateQuestions(Paper paper);
}