package com.lhz.estest.controller;

import com.lhz.estest.commom.Result;
import com.lhz.estest.eneity.User;
import joptsimple.internal.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by: hz.lai
 * Date: 2021/5/10
 * Description:
 */
@RestController
public class AdminController {

    @GetMapping(path="/login2")
    public Result val(@RequestParam String username,@RequestParam String password) {
        if ("123".equals(password)) {
            return Result.failed("登录失败");
        }
        return Result.success();
    }

    @GetMapping(path="/login")
    public Result login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        if ("123".equals(password)) {
            return Result.failed("登录失败");
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",username);
        return Result.success();
    }
    @GetMapping(path="/getUser")
    public Result getUser( HttpServletRequest request) {
        String username = (String)request.getSession().getAttribute("user");
        if (!StringUtils.hasText(username)) {
            return Result.failed("未登录");
        }
        return Result.success(username);
    }




    @GetMapping(path="/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return Result.success();
    }




}
