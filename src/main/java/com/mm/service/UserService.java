package com.mm.service;

import com.mm.dto.User;

import java.util.List;

/**
 * @Description 用户接口
 * @Author MKC
 * @Date 2021/12/28
 */
public interface UserService {
    /**
     * 根据用户ID获取用户信息
     * @param id id
     * @return 用户
     */
    User getUserById(Long id);

    /**
     * 保存用户
     * @param user 用户
     * @return 成功失败
     */
    int saveUser(User user);

    /**
     * 获取用户列表
     * @return 用户列表
     */
    List<User> getUserList();

    /**
     * 删除用户
     * @param id id
     * @return 成功失败
     */
    int deleteUser(Long id);

    /**
     * 更新用户
     * @param user 用户实体
     * @return 成功失败
     */
    int updateUser(User user);
}
