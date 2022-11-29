package com.ssm.template.mapper;

import com.ssm.template.entity.Student;

import java.util.List;

public interface StudentMapper {

    //@Select("select * from student")
    List<Student> getStudents();

}
