package com.mm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * @Description: SpringBoot测试类
 * @author: MKC
 * @date: 2021-11-25 17:41
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 使用前需要先初始化
     */
    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        session = new MockHttpSession();
        User user =new User();
        user.setUserName("MKC");
        session.setAttribute("user", user);
    }

    /**
     * 模拟get请求 由于使用了多数据源，需要指定数据源才能使用回滚
     * @throws Exception 异常
     */
    @Transactional("mysqlTransactionManager")
    @Test
    public void test() throws Exception {
        //模拟session和cookies
        //mockMvc.perform(MockMvcRequestBuilders.get("/index").sessionAttr(name, value));
        //mockMvc.perform(MockMvcRequestBuilders.get("/index").cookie(new Cookie(name, value)));

        //设置请求的Content-Type
        //mockMvc.perform(MockMvcRequestBuilders.get("/index").contentType(MediaType.APPLICATION_JSON));
        //设置返回格式为JSON
        //mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).accept(MediaType.APPLICATION_JSON));
        //模拟HTTP请求头
        //mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1).header(name, values));

        //get测试
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
        //post测试
        User user = new User();
        user.setUserName("Vic");
        user.setBirthday(new Date());
        user.setSex(0);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }
}
