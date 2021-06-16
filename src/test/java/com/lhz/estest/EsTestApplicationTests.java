package com.lhz.estest;

import com.lhz.estest.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class EsTestApplicationTests {
    @Autowired
    StudentMapper studentMapper;

    @Test
    public void insert() {
        com.lhz.estest.eneity.Student student = new com.lhz.estest.eneity.Student();
        student.setPassword("123");
        student.setName("admin");
        student.setUserNo("15200143");
        this.studentMapper.insert(student);
    }


}
