package com.example.demo.studentdao;

import com.example.demo.Models.StudentBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDao extends JpaRepository<StudentBean,Integer> {

    List<StudentBean> getAllByIdAfter(Integer id);
}
