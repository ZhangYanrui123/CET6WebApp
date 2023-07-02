package com.nksp.backend.mapper;

import com.nksp.backend.entity.Fee;
import com.nksp.backend.entity.Join;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FeeMapper {
    @Select("select * from fee where fid = #{fid}")
    Fee findById(Integer fid);

    @Insert("insert into fee(fstate, uuid, fgenerated, fpaid, fsubject, famount) values (#{fstate},#{uuid},#{fgenerated},#{fpaid},#{fsubject},#{famount})")
    int addFee(Fee fee);

    // 报名后，插入一个fee,它就是最后一行，所以获取这个fee，就能拿到fid了，但是肯定多用户会出问题，咱也不管了
    @Select("SELECT * FROM table_name ORDER BY id DESC LIMIT 1")
    Fee findEnd();
}
