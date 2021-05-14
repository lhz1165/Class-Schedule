package com.lhz.estest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by: hz.lai
 * Date: 2021/5/10
 * Description:
 */
@RestController
public class TestController {
    @Value("${lhz.val}")
    int val;

    @PostMapping(path="/user")
    public String val(@RequestBody User user) {
        if ("123".equals(user.getPassword())) {
            return "succ";
        }
        return "faild";
    }

    public int getVal() {
        return val;
    }
    static class User{
        String username;
        String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


}
