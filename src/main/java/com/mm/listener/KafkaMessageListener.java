package com.mm.listener;

import com.mm.entity.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Description kafka消息监听，用于消费消息
 * @Author MKC
 * @Date 2021/12/31
 */
@Slf4j
@Component
public class KafkaMessageListener {
    /**
     * ConsumerFactory和KafkaListenerContainerFactory。这两个Bean成功注册到Spring IOC容器中后，就可以使用@KafkaListener注解来监听消息了
     * 还可以监听多个topic，用,隔开
     * 使用topicPartitions来指定只接收来自特定分区的消息，也可以不指定initialOffset，而直接使用@TopicPartition(topic = "test", partitions = { "0", "1" })
     * @param message 消息
     * @param partition 分区
     */
    //@KafkaListener(topics = "test", groupId = "test-consumer")
    @KafkaListener(groupId = "test-consumer",
            topicPartitions = @TopicPartition(topic = "test",
                    partitionOffsets = {
                            @PartitionOffset(partition = "0", initialOffset = "7")
                    }))
    public void listen(@Payload KafkaMessage message,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("接收消息: {}，partition：{}", message, partition);
    }
}
