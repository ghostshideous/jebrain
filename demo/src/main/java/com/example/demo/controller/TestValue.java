package com.example.demo.controller;

import com.example.demo.Models.StudentBean;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestValue {

    @Value("${blog.address}")
    String address;

    @Autowired
    private StudentService service;

    @Value("${blog.author}")
    String author;

    @RequestMapping(value = "/getvalue",method = RequestMethod.GET)
    public String getValue(){
        return address + author;
    }

    @RequestMapping("/findall")
    public List<StudentBean> findAll(int id){
        return service.findAllStudent(id);
    }

}
