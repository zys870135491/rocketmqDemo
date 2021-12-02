package com.zys.rocketmqTemplate.mq.producer;

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
 * 这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
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
