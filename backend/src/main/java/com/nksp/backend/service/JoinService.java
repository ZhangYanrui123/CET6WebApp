package com.nksp.backend.service;

import com.nksp.backend.entity.Join;
import com.nksp.backend.entity.Teacher;

public interface JoinService {
    Join findById(Integer jid);
}
