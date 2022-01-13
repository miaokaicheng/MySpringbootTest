package com.mm.controller;

import com.mm.dto.ResultInfo;
import com.mm.dto.User;
import com.mm.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 登录
 * @Author MKC
 * @Date 2022/1/12
 */
@Slf4j
@Api(tags = "登录")
@Controller
public class LoginController {

    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ApiOperation(value = "根据用户名密码登录", notes = "根据用户名密码登录")
    @ResponseBody
    @PostMapping("/login")
    public ResultInfo login(String username, String password) {
        // 密码MD5加密
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResultInfo.ok();
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return ResultInfo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResultInfo.error("认证失败！");
        }
    }

    @GetMapping("/index")
    public String index(Model model) {
        // 登录成后，即可通过Subject获取登录的用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "index";
    }
}
