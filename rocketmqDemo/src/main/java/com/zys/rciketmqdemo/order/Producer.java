package com.zys.rciketmqdemo.order;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 顺序消费
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        String[] tags = new String[]{"TagA", "TagC", "TagD"};

        // 订单列表
        //构建消息集合
        List<OrderStep> orderList = OrderStep.buildOrders();

        for (int i = 0; i < 10; i++) {
            // 加个时间前缀
            String body = sdf.format(new Date()) + " Hello RocketMQ " + orderList.get(i);
            Message msg = new Message("OrderTopic", tags[i % tags.length], "KEY" + i, body.getBytes());
            /**
             * 参数一：消息对象
             * 参数二：消息队列的选择器
             * 参数三：选择队列的业务标识（订单ID）
             */
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                /**
                 * @param mqs：队列集合
                 * @param msg：消息对象
                 * @param arg：业务标识的参数
                 *  根据自定义的规则选择队列，把消息传入（例如根据消息id取模）
                 * @return
                 */
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Long id = (Long) arg;  //根据订单id选择发送queue
                    long index = id % mqs.size();
                    return mqs.get((int) index);
                }

            }, orderList.get(i).getOrderId());//订单id

            System.out.println("结果："+sendResult);
        }

        producer.shutdown();
    }

}
