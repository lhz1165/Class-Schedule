package com.lhz.estest.controller;

import com.lhz.estest.commom.Result;
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
public class IndexController {

    @GetMapping(path="/login")
    public Result val(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        if ("123".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser",username);
            return Result.success();
        }
        return Result.failed("登录失败");
    }

    @GetMapping(path = "/user")
    public Result user(HttpServletRequest request) {
        String loginUser = (String) request.getSession().getAttribute("loginUser");
        if (StringUtils.isEmpty(loginUser)) {
            return Result.success("null");
        }
        return Result.success(loginUser);
    }




}
