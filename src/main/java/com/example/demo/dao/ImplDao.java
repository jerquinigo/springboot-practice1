package com.example.demo.dao;

import com.example.demo.services.MethodClass;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ImplDao extends MethodClass {


    @Override

    public String displayGreeting() {
        return "hello world :)";
    }
}
