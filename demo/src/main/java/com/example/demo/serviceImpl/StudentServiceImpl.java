package com.example.demo.serviceImpl;

import com.example.demo.Models.StudentBean;
import com.example.demo.service.StudentService;
import com.example.demo.studentdao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentDao studentDao;

    @Override
    public List<StudentBean> findAllStudent(int id) {
        return studentDao.getAllByIdAfter(id);
    }
}
