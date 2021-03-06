package com.zys.rocketmqTemplate.mq.producer.base;

import com.zys.rciketmqdemo.order.OrderStep;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Pine
 * @Date: 2021/12/02/5:46 下午
 * @Desc: 普通消息的同步方法
 * 	Producer 向 broker 发送消息，阻塞当前线程等待 broker 响应 发送结果。
 */
@Component
@Slf4j
public class BaseSyncSendProducer {
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    public void baseSync(OrderStep orderStep){
        SendResult sendResult = rocketMQTemplate.syncSend("pi_base_sync_topic", orderStep);
        log.info("sendResult: {}",sendResult);
        if(sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
            log.error("消息发送成功");
        }

    }
}
