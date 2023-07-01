package com.nksp.backend.mapper;

import com.nksp.backend.entity.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RecordMapper {
    @Select("select * from record where rid = #{rid}")
    Record findById(Integer rid);

    @Select("select * from record where uuid = #{uuid} and eid = #{eid}")
    List<Record> findByUEid(Integer uuid, Integer eid);

    @Insert("insert into record(uuid, eid, qid, ranswer) values (#{uuid}, #{eid}, #{qid}, #{ranswer})")
    int insertRecord(Record record);

    @Update("update record set rscore=#{rscore} where uuid = #{uuid} and eid = #{eid} and qid = #{qid} and rid = #{rid}")
    int updateRecord(Record record);

    @Select("select * from record")
    List<Record> getAllRecords();
}