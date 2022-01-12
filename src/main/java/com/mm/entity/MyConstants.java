package com.mm.entity;

/**
 * @Description 常量类
 * @Author MKC
 * @Date 2021/12/27
 */
public class MyConstants {
    //====================redis相关start============================//

    public static final String REDIS_MYSQL_LIST = "mysql:list";
    public static final String REDIS_ORACLE_LIST = "oracle:list";
    public static final String REDIS_POSTGRES_LIST = "postgres:list";
    public static final String REDIS_GREENPLUM_LIST = "greenplum:list";
    //====================redis相关end============================//

    //====================用户状态start============================//

    public static final String USER_STATUS_NORMAL = "1";
    public static final String USER_STATUS_LOCK = "0";
    public static final String USER_STATUS_DELETE = "-1";
    //====================用户状态start============================//
}
