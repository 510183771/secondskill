package com.nick.secondskill.controller;

import com.nick.secondskill.domain.User;
import com.nick.secondskill.result.Result;
import com.nick.secondskill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright (C), 2019-2020, AO YUAN
 * Author: Nick
 * Date: 2020/1/21 14:08
 * FileName: TestController
 * Description:
 */
@Controller
public class TestController {
    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello(){
        return Result.success("hello");
    }

    @RequestMapping("/thymeleaf")
    public String testThymeleaf(Model modle){
        modle.addAttribute("name","thymeleaf");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {//0代表成功
        User user=userService.getById(1);
        System.out.println("res:"+user.getName());
        return Result.success(user);
    }

}
