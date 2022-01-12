package com.mm.controller;

import com.mm.dto.ResultInfo;
import com.mm.dto.Status;
import com.mm.dto.User;
import com.mm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Description 用户类
 * @Author MKC
 * @Date 2021/12/28
 */
@Slf4j
@Api(tags = "用户")
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户ID获取用户信息
     * @param id id
     * @return 用户
     */
    @ApiOperation(value = "获取用户信息", notes = "根据用户id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/{id}")
    public ResultInfo getUserById(@PathVariable(value = "id") Long id) {
        User user = userService.getUserById(id);
        return new ResultInfo(Status.SUCCESS.code, "查询成功", user);
    }

    /**
     * 保存用户
     * @param user 用户
     * @return 成功失败
     */
    @ApiOperation(value = "新增用户", notes = "根据用户实体创建用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    @PostMapping("/save")
    public ResultInfo saveUser(@RequestBody User user) {
        int result = userService.saveUser(user);
        return new ResultInfo(Status.SUCCESS.code, "操作成功");
    }

    /**
     * 获取用户列表
     * @return 用户列表
     */
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @GetMapping("/list")
    public ResultInfo getUserList() {
        List<User> users = userService.getUserList();
        return new ResultInfo(Status.SUCCESS.code, "操作成功", users);
    }

    /**
     * 删除用户
     * @param id id
     * @return 成功失败
     */
    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/{id}")
    public ResultInfo deleteUser(@PathVariable(value = "id") Long id) {
        int result = userService.deleteUser(id);
        return new ResultInfo(Status.SUCCESS.code, "操作成功");
    }

    /**
     * 更新用户（ApiImplicitParamsd的使用，实际的话，不需要传id，包含在user中了）
     * @param id id
     * @param user 用户实体
     * @return 成功失败
     */
    @ApiOperation(value = "更新用户", notes = "根据用户id更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User", paramType = "body") })
    @PutMapping("/{id}")
    public ResultInfo updateUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
        int result = userService.updateUser(user);
        return new ResultInfo(Status.SUCCESS.code, "操作成功");
    }

    /**
     * 普通参数校验
     * @param name 用户名
     * @param email 邮箱
     * @return 用户
     */
    @ApiOperation(value = "普通参数校验", notes = "普通参数校验")
    @GetMapping("/{name}/{email}")
    public ResultInfo getUserByNameAndEmail(
            @NotBlank(message = "不能为空") @PathVariable(value = "name")  String name,
            @Email(message = "格式不正确") @PathVariable(value = "email")  String email) {
        User user = userService.getUserByNameAndEmail(name,email);
        return new ResultInfo(Status.SUCCESS.code, "查询成功", user);
    }

    /**
     * 实体类参数校验
     * @param user 用户实体类
     * @return 用户
     */
    @ApiOperation(value = "实体类参数校验", notes = "实体类参数校验")
    @PostMapping("/valid")
    public ResultInfo getUser(@Valid User user) {
        return new ResultInfo(Status.SUCCESS.code, "查询成功", user);
    }
}
