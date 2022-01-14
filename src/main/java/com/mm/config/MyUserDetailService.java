package com.mm.config;

import com.mm.dto.User;
import com.mm.mapper.mysql.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description 实现Security的UserDetailService
 * @Author MKC
 * @Date 2022/1/14
 */
@Slf4j
@Configuration
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 模拟一个用户，替代数据库获取逻辑
        User user = userMapper.getUserByName(username);
        if(user == null){
           throw new UsernameNotFoundException("用户不存在");
        }
        //user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setPassword(this.passwordEncoder.encode("123456"));
        // 输出加密后的密码
        log.info(user.getPassword());

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true,
                true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}