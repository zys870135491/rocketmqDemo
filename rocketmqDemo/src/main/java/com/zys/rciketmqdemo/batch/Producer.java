package com.zys.rciketmqdemo.batch;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 批量发送
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("producerGroup1");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 2; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("harmay" ,
                    "batch" ,
                    ("batch " + sdf.format(new Date())+"_"+i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            Message msg1 = new Message("harmay" ,
                    "batch" ,
                    ("batch " + sdf.format(new Date())+"_"+i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            Message msg2 = new Message("harmay" ,
                    "batch" ,
                    ("batch " + sdf.format(new Date())+"_"+i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            List<Message> messages = Arrays.asList(msg, msg1, msg2);
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(messages);
            // 通过sendResult返回消息是否成功送达
            System.out.println("发送结果："+sendResult);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }

}
