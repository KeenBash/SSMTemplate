package com.ssm.template.controller;

import com.ssm.template.entity.Student;
import com.ssm.template.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index"})
    public String index(){
        return "index";
    }

    @Autowired
    StudentService service;

    @RequestMapping(value = "/json", produces = "application/json")
    @ResponseBody
    public Student json(){
        return service.getStudents().get(0);
    }
}
