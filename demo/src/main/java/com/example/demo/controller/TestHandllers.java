package com.example.demo.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class TestHandllers {

    @RequestMapping(value="/hello",method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam String jkl){
        System.out.println("jkl=="+jkl);
        return "Hello-name="+jkl;
    }

    @RequestMapping(value="/sayHello",method = RequestMethod.POST)
    public String sayHello(String jkl){
        System.out.println("kefang"+jkl);
        return "post mehtod return ="+jkl;
    }


}
