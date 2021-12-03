package com.zys.rocketmqTemplate.mq.producer.base;

import com.zys.rciketmqdemo.order.OrderStep;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: Pine
 * @Date: 2021/12/02/4:16 下午
 * @Desc: 普通消息生产者
 */
@Component
public class BaseProducer {
    @Autowired
    RocketMQTemplate rocketMQTemplate;


    public void base(OrderStep orderStep){
        rocketMQTemplate.convertAndSend("pi_base_topic",orderStep);
    }

}
