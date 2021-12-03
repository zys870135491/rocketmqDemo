package com.zys.rocketmqTemplate.mq.producer.base;

import com.zys.rciketmqdemo.order.OrderStep;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
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
public class BaseAsyncSendProducer {
    @Autowired
    RocketMQTemplate rocketMQTemplate;


    public void baseAsync(OrderStep orderStep){
       rocketMQTemplate.asyncSend("pi_base_async_topic", orderStep, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("sendResult:{}",sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("发送异常:{}",throwable);
            }
        },10*1000);

    }
}
