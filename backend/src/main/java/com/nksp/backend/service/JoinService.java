package com.nksp.backend.service;

import com.nksp.backend.entity.Join;
import com.nksp.backend.entity.Teacher;

import java.util.List;

public interface JoinService {
    Join findById(Integer jid);

    List<Join> getAllJoin(Integer uuid);

    int addJoin(Join join);

    int insertJoin(Join join);

    int deleteJoin(int uuid, int eid);
}
