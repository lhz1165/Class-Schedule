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
        System.out.println("  __                   __                                \n" +
                "_/  |_  ____   _______/  |_  ___________    ______ ______\n" +
                "\\   __\\/ __ \\ /  ___/\\   __\\ \\____ \\__  \\  /  ___//  ___/\n" +
                " |  | \\  ___/ \\___ \\  |  |   |  |_> > __ \\_\\___ \\ \\___ \\ \n" +
                " |__|  \\___  >____  > |__|   |   __(____  /____  >____  >\n" +
                "           \\/     \\/         |__|       \\/     \\/     \\/ ");
    }


}
