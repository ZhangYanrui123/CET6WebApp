package com.nksp.backend.mapper;

import com.nksp.backend.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RecordMapper {
    @Select("select * from record where rid = #{rid}")
    Record findById(Integer rid);
}