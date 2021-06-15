package com.lhz.estest;

import com.lhz.estest.eneity.Student;
import com.lhz.estest.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class EsTestApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void insert() {
        Student student = new Student();
        student.setPassword("123");
        student.setName("admin");
        student.setUserNo("15200143");
        userMapper.insert(student);
    }


}
