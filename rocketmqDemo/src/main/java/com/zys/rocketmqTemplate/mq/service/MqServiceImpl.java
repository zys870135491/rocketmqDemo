package com.zys.rocketmqTemplate.mq.service;


import com.zys.rciketmqdemo.order.OrderStep;
import com.zys.rocketmqTemplate.mq.producer.BaseProducer;
import org.springframework.stereotype.Service;

/**
 * @Author: Pine
 * @Date: 2021/12/02/4:34 下午
 * @Desc:
 */
@Service
public class MqServiceImpl {

    private final BaseProducer baseProducer;

    public MqServiceImpl(BaseProducer baseProducer) {
        this.baseProducer = baseProducer;
    }

    /**
     * 普通消费
     */
    public void BaseMq(){
        OrderStep orderStep = new OrderStep();
        orderStep.setOrderId(123456);
        orderStep.setDesc("订单编号：123456");
        baseProducer.base(orderStep);
    }

}
