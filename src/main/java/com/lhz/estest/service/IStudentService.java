package com.lhz.estest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.estest.eneity.Student;

/**
 * Created by: hz.lai
 * Date: 2021/6/16
 * Description:
 */
public interface IStudentService extends IService<Student> {
    void registerStdent(String name,String password,String userNo);

    String verifyStudent(String userNo, String password);
}
