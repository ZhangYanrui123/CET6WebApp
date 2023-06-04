package com.nksp.backend.mapper;

import com.nksp.backend.entity.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PaperMapper {
    @Select("select * from paper where pid = #{pid}")
    Paper findById(Integer pid);
}