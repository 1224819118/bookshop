package com.caohao.bookshop.web;

import com.caohao.bookshop.entity.User;
import com.caohao.bookshop.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    //验证用户名是否存在
    @ResponseBody
    @RequestMapping("/checkUserName")
    public String CheckUserName(String username){
        return userService.checkUser(username);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public String register(User user){
        userService.save(user);
        return "success";
    }

    /**
     * 用户登录
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(User user, HttpSession session){
        return userService.loginCheck(user,session);
    }

    /**
     * 安全退出
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/book/index";
    }
}
