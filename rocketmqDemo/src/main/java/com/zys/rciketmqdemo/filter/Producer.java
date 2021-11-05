package com.zys.rciketmqdemo.filter;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 过滤消息，通过sql语句的形式
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        DefaultMQProducer producer = new DefaultMQProducer("producerGroup1");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for(int i = 0 ;i < 10; i++){
            String body = "Hello RocketMQ " + i + " , "+ sdf.format(new Date());
            Message msg = new Message("harmay",
                    "filter",
                     body.getBytes());
            // 设置一些属性,为了过滤使用
            msg.putUserProperty("a", String.valueOf(i));
            SendResult sendResult = producer.send(msg);
            System.out.println("senResult:"+sendResult);
        }

        producer.shutdown();
    }

}
