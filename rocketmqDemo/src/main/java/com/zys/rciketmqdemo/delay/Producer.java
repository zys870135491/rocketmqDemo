package com.zys.rciketmqdemo.delay;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 延迟消息
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        DefaultMQProducer producer = new DefaultMQProducer("producerGroup1");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        for(int i =0 ;i<10;i++){
            String body = " delay RocketMQ " + sdf.format(new Date());
            Message message = new Message("harmay","delayTag",body.getBytes());
            // 设置延时等级3,这个消息将在10s之后发送(现在只支持固定的几个时间,详看delayTimeLevel)
            message.setDelayTimeLevel(4);
            SendResult sendResult = producer.send(message);
            System.out.println("结果："+sendResult);
        }
        // 关闭生产者
        producer.shutdown();

    }

}
