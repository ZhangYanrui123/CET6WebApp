package com.nksp.backend.serviceimpl;

import com.nksp.backend.entity.Student;
import com.nksp.backend.mapper.StudentMapper;
import com.nksp.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student findById(Integer userId) {
        return studentMapper.findById(userId);
    }

    @Override
    public int insertStudent(Student info){return studentMapper.insertStudent(info);}

    @Override
    public List<Student> getAllStudent(){return studentMapper.getAllStudent();}

    @Override
    public int updateStudent(Student student) {return studentMapper.updateStudent(student);}

    @Override
    public Student findByNo(int sno) {return studentMapper.findByNo(sno);}

    @Override
    public int deleteStudentByNo(int sno) {return studentMapper.deleteStudentByNo(sno);}
}
