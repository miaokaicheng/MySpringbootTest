package com.mm.controller;

import com.mm.entity.KafkaMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description kafka
 * @Author MKC
 * @Date 2021/12/31
 */
@Api(tags = "kafka")
@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, KafkaMessage> kafkaTemplateComplication;

    @ApiOperation(value = "发送消息")
    @GetMapping("/send/{message}")
    public void send(@PathVariable String message) {
        //向之前创建好了一个test的topic中发送消息,并取回响应
        ListenableFuture<SendResult<String, KafkaMessage>> future = kafkaTemplateComplication.send("test", new KafkaMessage("vic", message));;
        future.addCallback(new ListenableFutureCallback<SendResult<String, KafkaMessage>>() {
            @Override
            public void onSuccess(SendResult<String, KafkaMessage> result) {
                log.info("成功发送消息：{}，offset=[{}]", message, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("消息：{} 发送失败，原因：{}", message, ex.getMessage());
            }
        });
    }
}
