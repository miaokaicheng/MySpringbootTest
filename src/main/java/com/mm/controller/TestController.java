package com.mm.controller;

import com.mm.annotation.Log;
import com.mm.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: 通用测试类
 * @author: MKC
 * @date: 2021-11-25 17:41
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService service;

    /*@RequestMapping("/getList")
    public List<Map<String,Object>> getList(){
        return service.getList();
    }
    @RequestMapping("/getBasicList")
    public List<Map<String,Object>> getBasicList(){
        return service.getBasicList();
    }*/

    @RequestMapping("/getList2")
    public List<Map<String,Object>> getList2(){
        return service.getList2();
    }


    @Log("执行方法：logMethod")
    @GetMapping("/logMethod")
    public void logMethod(String name) { }
}
