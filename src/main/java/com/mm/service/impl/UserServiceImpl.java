package com.mm.service.impl;

import com.mm.dto.User;
import com.mm.mapper.mysql.UserMapper;
import com.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 用户实现类
 * @Author MKC
 * @Date 2021/12/28
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 根据用户ID获取用户信息
     *
     * @param id id
     * @return 用户
     */
    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    /**
     * 保存用户
     *
     * @param user 用户
     * @return 成功失败
     */
    @Override
    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }
}
