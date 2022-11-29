package com.ssm.template.service.impl;

import com.ssm.template.entity.Student;
import com.ssm.template.mapper.StudentMapper;
import com.ssm.template.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper mapper;

    @Override
    public List<Student> getStudents() {
        return mapper.getStudents();
    }
}
