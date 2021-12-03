package com.zys.rocketmqTemplate.mq.producer.order;

import com.zys.rciketmqdemo.order.OrderStep;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Pine
 * @Date: 2021/12/03/3:11 下午
 * @Desc: 顺序生产者
 * 消息队列RocketMQ 版针对生产者采起的是轮询制，即 Producer 的消息以轮询的方式发送至 消息队列(Queue);
 * 1.若是消费者consumer机器数量和消息队列相等，则消息队列平均分配到每个consumer上。
 * 2.若是consumer数量大于消息队列数量，则超出消息队列数量的机器没有能够处理的消息队列。
 * 3.若消息队列数量不是consumer的整数倍，则部分consumer会承担跟多的消息队列的消费任务。
 * 这种形式就会导致消息在消费的时候不是顺序进行消费，如果想顺序消费就需要把消息放到同一个队列下就只会有一个消费者来进行消费
 * 但是消费者是多线程消费，此时也要保证消费端是顺序消费，此时开启了顺序消费之后消费端就变成了单线程进行消费，保证了消息先进先出
 */
@Component
@Slf4j
public class OrderProducer {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    public void Order(OrderStep orderStep){

        // orderStep.getOrderId()是同一个，那么就会把此消息放入相同的队列中
        SendResult sendResult =
                rocketMQTemplate.syncSendOrderly("pi_order_topic", orderStep, "orderId");
        log.info("sendResult:{}"+sendResult);
    }

}
