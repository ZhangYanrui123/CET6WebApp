package com.nksp.backend.mapper;

import com.nksp.backend.entity.Option;
import com.nksp.backend.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OptionMapper {
    @Select("select * from options where oid = #{oid}")
    Option findById(Integer oid);

    @Insert("insert into options(qid, ocontent, oisright) values" +
            "(#{qid}, #{ocontent}, #{oisright})")
    @Options(useGeneratedKeys=true,keyProperty="oid")
    int insertOption(Option option);

    @Select("select * from options where qid = #{qid}")
    List<Option> getOptionList(Integer qid);
}