package com.example.demo.service;

import com.example.demo.Models.StudentBean;

import java.util.List;

public interface StudentService {

    List<StudentBean> findAllStudent(int id);
}
