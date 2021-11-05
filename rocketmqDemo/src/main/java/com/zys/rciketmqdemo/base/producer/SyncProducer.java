package com.zys.rciketmqdemo.base.producer;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发送同步消息
 * 这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("group3");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 7; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("TopicTestA" ,
                    "TagA" ,
                    ("Hellod RocketMQ " + sdf.format(new Date())+"_"+i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg);
            // 通过sendResult返回消息是否成功送达
            System.out.println("发送结果："+sendResult);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }

}
