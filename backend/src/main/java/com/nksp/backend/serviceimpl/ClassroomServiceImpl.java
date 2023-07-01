package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Classroom;
import com.nksp.backend.mapper.ClassroomMapper;
import com.nksp.backend.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    private ClassroomMapper classroomMapper;
    @Override
    public Classroom findById(Integer cid) {
        return classroomMapper.findById(cid);
    }

    @Override
    public Classroom findByName(String name){return classroomMapper.findByName(name);}
    @Override
    public int addClassromm(Classroom classroom){return classroomMapper.addClassromm(classroom);}

    @Override
    public List<Classroom> getAllClassrooms() {return classroomMapper.getAllClassrooms();}
}
