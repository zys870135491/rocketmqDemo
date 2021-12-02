package com.zys.rocketmqTemplate.mq.consumer;

import com.zys.rciketmqdemo.order.OrderStep;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Pine
 * @Date: 2021/12/02/4:29 下午
 * @Desc: 普通消息消费者
 * 1.如果两个消费者group和topic都一样，则二者轮循接收消息
 * 2.如果两个消费者topic一样，而group不一样，则消息变成广播机制
 * RocketMQListener<>泛型必须和接收的消息类型相同
 */
@Component
@RocketMQMessageListener(topic = "pi_base_topic" , consumerGroup = "PP_BASE1")
@Slf4j
public class BaseListener1 implements RocketMQListener<OrderStep> {

    @Override
    public void onMessage(OrderStep orderStep) {
        log.info("BaseListener1 orderStep:{}",orderStep);
    }

}
