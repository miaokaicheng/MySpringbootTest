package com.mm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description: RedisClusterTest
 * @author: MKC
 * @date: 2021-12-03 17:31
 */

@RestController
@RequestMapping("/redis")
public class RedisTestController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/redis")
    public void getredis() {
        //string
        stringRedisTemplate.opsForValue().set("a:b:1", "stringRedisTemplate test");
        System.out.println(stringRedisTemplate.opsForValue().get("a:b:1"));
        redisTemplate.opsForValue().set("a:b:2", "redisTemplate test String");
        System.out.println(redisTemplate.opsForValue().get("a:b:2"));

        //hash
        Map<String,Object> map = new HashMap<>(2);
        map.put("key1", "k1");
        map.put("key2", "k2");
        Map<String,Object> map1 = new HashMap<>(2);
        map.put("key3", map1);
        //存入数组会有问题 Could not resolve type id 'k2' as a subtype of `java.lang.Object`: no such class found
        //redisTemplate.opsForHash().put("a:b:33","ky1","ky1");
        redisTemplate.opsForHash().putAll("a:b:3",map);
        System.out.println(redisTemplate.opsForHash().get("a:b:3","key1"));
        Map<String, Object> entries = redisTemplate.opsForHash().entries("a:b:3");
        System.out.println(entries);

        //list
        redisTemplate.opsForList().leftPush("a:b:4", map);
        redisTemplate.opsForList().leftPush("a:b:4", map1);
        //set方法是往已有的key中添加,不能超过list原有大小
        redisTemplate.opsForList().set("a:b:4", 0, "list1");
        redisTemplate.opsForList().set("a:b:4", 1, "list2");
        System.out.println(redisTemplate.opsForList().index("a:b:4", 1));

        //set
        redisTemplate.opsForSet().add("a:b:5","A","B","C","B","D","E","F");
        Set<String> set = redisTemplate.opsForSet().members("a:b:5");
        System.out.println(set);
    }
}
