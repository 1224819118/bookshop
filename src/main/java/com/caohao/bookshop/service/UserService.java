package com.caohao.bookshop.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caohao.bookshop.entity.User;
import com.caohao.bookshop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("userService")
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    UserMapper userMapper;
    /**
     * 验证用户是否存在
     */
    public String checkUser(String username){
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (user==null){
            return "101";//用户不存在
        }else {
            return "102";//用户已存在
        }
    }

    /**
     * 登录验证
     */
    public String loginCheck(User loginUser,HttpSession session){
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().eq(User::getUsername,loginUser.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            return "101";//用户不存在
        } else {
            //判断密码
            if(loginUser.getPassword().equals(user.getPassword())){
                session.setAttribute("user",user);
                return "100";
            } else {
                return "102";//密码不正确
            }
        }
    }
}
