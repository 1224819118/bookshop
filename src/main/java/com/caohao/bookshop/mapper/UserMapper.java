package com.caohao.bookshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caohao.bookshop.entity.User;
import org.springframework.stereotype.Repository;

@Repository("userMapper")
public interface UserMapper extends BaseMapper<User> {
}
