package com.nksp.backend.mapper;

import com.nksp.backend.entity.Fee;
import com.nksp.backend.entity.Join;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FeeMapper {
    @Select("select * from fee where fid = #{fid}")
    Fee findById(Integer fid);
}
