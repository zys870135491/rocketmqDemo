package com.zys.rocketmqTemplate.mq.consumer.order;

import com.zys.rciketmqdemo.order.OrderStep;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Pine
 * @Date: 2021/12/02/4:29 下午
 * @Desc: 普通消息消费者(同步的方式)
 * consumeMode = ConsumeMode.ORDERLY 则表示顺序消费（多线程消费变成了单线程消费）
 */
@Component
@RocketMQMessageListener(topic = "pi_order_topic" , consumerGroup = "PP_ORDER",consumeMode = ConsumeMode.ORDERLY)
@Slf4j
public class OrderListener implements RocketMQListener<OrderStep> {

    @Override
    public void onMessage(OrderStep orderStep) {
        log.info("OrderListener orderStep:{}",orderStep);
    }

}
