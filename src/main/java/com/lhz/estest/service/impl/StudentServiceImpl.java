package com.lhz.estest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhz.estest.eneity.Student;
import com.lhz.estest.mapper.StudentMapper;
import com.lhz.estest.service.IStudentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-06-16
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Override
    public void registerStdent(String name,String password,String userNo) {
        Student student = new Student();
        student.setName(name);
        student.setPassword(password);
        student.setUserNo(userNo);
        save(student);
    }

    @Override
    public String verifyStudent(String userNo, String password) {
        Student student = lambdaQuery().ge(Student::getUserNo, userNo).one();
        if (null == student) {
            return "false";
        }
        if (!student.getPassword().equals(password)) {
            return "false";
        }
        return student.getName();
    }

}
