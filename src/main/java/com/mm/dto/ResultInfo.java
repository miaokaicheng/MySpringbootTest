package com.mm.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果类统一封装
 * @author MKC
 */
@Data
public class ResultInfo implements Serializable {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据对象
     */
    private Object result;

    /**
     * 无参构造器
     */
    public ResultInfo() {
        super();
        this.code = Status.SUCCESS.code;
        this.message = Status.SUCCESS.message;
    }

    public ResultInfo(Status status) {
        super();
        this.code = status.code;
        this.message = status.message;
    }

    public ResultInfo result(Object result) {
        this.result = result;
        return this;
    }

    public ResultInfo message(String message) {
        this.message = message;
        return this;
    }

    /**
     * 只返回状态，状态码，消息
     *
     * @param code
     * @param message
     */
    public ResultInfo(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * 只返回状态，状态码，数据对象
     *
     * @param code
     * @param result
     */
    public ResultInfo(Integer code, Object result) {
        super();
        this.code = code;
        this.result = result;
    }

    /**
     * 只返回数据对象
     *
     * @param code
     * @param message
     * @param result
     */
    public ResultInfo(Object result) {
        super();
        this.result = result;
    }

    /**
     * 返回全部信息即状态，状态码，消息，数据对象
     *
     * @param code
     * @param message
     * @param result
     */
    public ResultInfo(Integer code, String message, Object result) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
    }

    /**
     * 正常返回
     * @return ResultInfo
     */
    public static ResultInfo ok(){
        return new ResultInfo();
    }

    /**
     * 异常返回
     * @return ResultInfo
     */
    public static ResultInfo error(String msg){
        return new ResultInfo(Status.WARN.code,msg);
    }

}
