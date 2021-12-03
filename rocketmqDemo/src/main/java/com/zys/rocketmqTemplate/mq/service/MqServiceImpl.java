package com.zys.rocketmqTemplate.mq.service;


import com.zys.rciketmqdemo.order.OrderStep;
import com.zys.rocketmqTemplate.mq.producer.base.BaseAsyncSendProducer;
import com.zys.rocketmqTemplate.mq.producer.base.BaseProducer;
import com.zys.rocketmqTemplate.mq.producer.base.BaseSyncSendProducer;
import com.zys.rocketmqTemplate.mq.producer.delay.DelayProducer;
import com.zys.rocketmqTemplate.mq.producer.order.OrderProducer;
import org.springframework.stereotype.Service;

/**
 * @Author: Pine
 * @Date: 2021/12/02/4:34 下午
 * @Desc:
 */
@Service
public class MqServiceImpl {

    private final BaseProducer baseProducer;
    private final BaseSyncSendProducer baseSyncSendProducer;
    private final BaseAsyncSendProducer baseAsyncSendProducer;
    private final OrderProducer orderProducer;
    private final DelayProducer delayProducer;


    public MqServiceImpl(BaseProducer baseProducer, BaseSyncSendProducer baseSyncSendProducer, BaseAsyncSendProducer baseAsyncSendProducer, OrderProducer orderProducer, DelayProducer delayProducer) {
        this.baseProducer = baseProducer;
        this.baseSyncSendProducer = baseSyncSendProducer;
        this.baseAsyncSendProducer = baseAsyncSendProducer;
        this.orderProducer = orderProducer;
        this.delayProducer = delayProducer;
    }

    /**
     * 普通消费
     */
    public void BaseMq(){
        for(int i = 0 ;i<10; i++){
            OrderStep orderStep = new OrderStep();
            orderStep.setOrderId(i);
            orderStep.setDesc(String.format("订单编号 %s",i));
            baseProducer.base(orderStep);
        }

    }

    /**
     * 普通消费 同步方式
     */
    public void BaseSyncMq(){
        OrderStep orderStep = new OrderStep();
        orderStep.setOrderId(123);
        orderStep.setDesc(String.format("订单编号 %s","123"));
        baseSyncSendProducer.baseSync(orderStep);

    }

    /**
     * 普通消费 异步方式
     */
    public void BaseAsyncMq(){
        OrderStep orderStep = new OrderStep();
        orderStep.setOrderId(123);
        orderStep.setDesc(String.format("订单编号 %s","123"));
        baseAsyncSendProducer.baseAsync(orderStep);

    }

    /**
     * 顺序消费
     */
    public void orderMq(){
        for(int i = 0 ;i<10; i++){
            OrderStep orderStep = new OrderStep();
            orderStep.setOrderId(i);
            orderStep.setDesc(String.format("订单编号 %s",i));
            orderProducer.Order(orderStep);
        }

    }

    /**
     * 延时消费
     */
    public void delayMq(){
        OrderStep orderStep = new OrderStep();
        orderStep.setOrderId(123);
        orderStep.setDesc(String.format("订单编号 %s","123"));
        delayProducer.delay(orderStep);

    }

}
