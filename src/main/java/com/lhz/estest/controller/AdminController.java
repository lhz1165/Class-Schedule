package com.lhz.estest.controller;

import com.lhz.estest.commom.Result;
import com.lhz.estest.eneity.Student;
import com.lhz.estest.service.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by: hz.lai
 * Date: 2021/5/10
 * Description:
 */
@Api(tags = "注册登录")
@RestController
@RequestMapping("/")
public class AdminController {


    @Autowired
    private IStudentService studentService;


    @ApiOperation("登录")
    @GetMapping(path="/login")
    public Result login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        String name = studentService.verifyStudent(username, password);
        if ("false".equals(name)) {
            return Result.failed("账号或密码不正确");
        }
        request.getSession().setAttribute("user",name);
        request.getSession().setAttribute("studentNo",username);
        return Result.success();
    }

    /**
     * 注册账号
     * @param name
     * @param password
     * @param studentNo
     * @return
     */
    @ApiOperation("注册")
    @GetMapping("/register")
    public Result registerStudent(@RequestParam String name, @RequestParam String password, @RequestParam String studentNo) {
        studentService.registerStdent(name,password,studentNo);
        return Result.success();
    }


    /**
     * 获取登录用户
     * @param request
     * @return
     */
    @ApiOperation("获取用户")
    @GetMapping(path="/getUser")
    public Result getUser( HttpServletRequest request) {
        String username = (String)request.getSession().getAttribute("user");
        if (!StringUtils.hasText(username)) {
            return Result.failed("未登录");
        }
        return Result.success(username);
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @GetMapping(path="/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return Result.success();
    }


    @ApiOperation("获取用户信息")
    @GetMapping(path="/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        String studentNo = (String)request.getSession().getAttribute("studentNo");
        return Result.success(studentService.lambdaQuery().eq(Student::getUserNo,studentNo).one());
    }

    @ApiOperation("修改用户信息")
    @GetMapping(path="/edit")
    public Result edit(String name,String password,HttpServletRequest request) {
        String studentNo = (String)request.getSession().getAttribute("studentNo");
        Student student = studentService.lambdaQuery().eq(Student::getUserNo, studentNo).one();
        student.setName(name);
        student.setPassword(password);
        studentService.updateById(student);
        return Result.success();
    }




}
