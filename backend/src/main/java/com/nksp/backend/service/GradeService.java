package com.nksp.backend.service;
import com.nksp.backend.entity.Grade;

import java.util.List;

public interface GradeService {
    Grade findById(Integer gid);

    List<Grade> getAllGrade(Integer uuid);

    int updateSOScore(Grade grade);

    int updateSubScore(Grade grade);

    Grade findByUidEid(int uuid, int eid);
}
