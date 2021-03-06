package com.mm.service.impl;

import com.mm.dto.User;
import com.mm.mapper.mysql.UserMapper;
import com.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public User getUserById(Long id) {
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

    /**
     * 获取用户列表
     *
     * @return 用户列表
     */
    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    /**
     * 删除用户
     *
     * @param id id
     * @return 成功失败
     */
    @Override
    public int deleteUser(Long id) {
        return userMapper.deleteUser(id);
    }

    /**
     * 更新用户
     *
     * @param user 用户实体
     * @return 成功失败
     */
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * 根据用户名和邮箱获取用户
     *
     * @param name  用户名
     * @param email 邮箱
     * @return 用户
     */
    @Override
    public User getUserByNameAndEmail(String name, String email) {
        return userMapper.getUserByNameAndEmail(name,email);
    }
}
