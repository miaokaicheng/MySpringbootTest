package com.mm.mapper.mysql;

import com.mm.dto.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 用户Mapper
 * @Author MKC
 * @Date 2021/12/28
 */
@Repository
public interface UserMapper {
    /**
     * 根据用户ID获取用户信息
     * @param id id
     * @return 用户
     */
    User getUserById(Long id);

    /**
     * 保存用户
     *
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
     *
     * @param id id
     * @return 成功失败
     */
    int deleteUser(Long id);

    /**
     * 更新用户
     *
     * @param user 用户实体
     * @return 成功失败
     */
    int updateUser(User user);

    /**
     * 根据用户名和邮箱获取用户
     *
     * @param name  用户名
     * @param email 邮箱
     * @return 用户
     */
    User getUserByNameAndEmail(@Param("name") String name, @Param("email") String email);

    /**
     * 根据用户名获取用户信息
     * @param userName 用户名
     * @return 用户
     */
    User getUserByName(@Param("userName") String userName);
}
