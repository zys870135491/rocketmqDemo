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
 * @Desc: Oneway发送
 * 	Oneway 方式只负责发送请求，不等待应答，Producer只负责把请求发出去，而不处理响应结果。
 */
@Component
@Slf4j
public class BaseOnewaySendProducer {
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    public void baseSync(OrderStep orderStep){

        rocketMQTemplate.sendOneWay("pi_base_oneway_topic",orderStep);

    }
}
