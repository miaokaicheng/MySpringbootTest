package com.mm.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description kafka复杂消息类
 * @Author MKC
 * @Date 2021/12/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMessage implements Serializable {
    private static final long serialVersionUID = 6678420965611108427L;

    @ApiModelProperty(value = "消息发送端")
    private String from;
    @ApiModelProperty(value = "消息")
    private String message;
}
