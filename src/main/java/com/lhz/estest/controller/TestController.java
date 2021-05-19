package com.lhz.estest.controller;

import com.lhz.estest.commom.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by: hz.lai
 * Date: 2021/5/10
 * Description:
 */
@RestController
public class TestController {

    @GetMapping(path="/login")
    public Result val(@RequestParam String username,@RequestParam String password) {
        if ("123".equals(password)) {
            return Result.failed("登录失败");
        }
        return Result.success();
    }




}
