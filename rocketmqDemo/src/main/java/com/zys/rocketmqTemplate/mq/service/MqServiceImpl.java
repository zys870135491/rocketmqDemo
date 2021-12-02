package com.zys.rocketmqTemplate.mq.service;


import com.zys.rciketmqdemo.order.OrderStep;
import com.zys.rocketmqTemplate.mq.producer.BaseAsyncSendProducer;
import com.zys.rocketmqTemplate.mq.producer.BaseProducer;
import com.zys.rocketmqTemplate.mq.producer.BaseSyncSendProducer;
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

    public MqServiceImpl(BaseProducer baseProducer, BaseSyncSendProducer baseSyncSendProducer, BaseAsyncSendProducer baseAsyncSendProducer) {
        this.baseProducer = baseProducer;
        this.baseSyncSendProducer = baseSyncSendProducer;
        this.baseAsyncSendProducer = baseAsyncSendProducer;
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

}
