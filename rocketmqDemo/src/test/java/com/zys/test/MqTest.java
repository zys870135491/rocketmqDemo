package com.zys.test;

import com.zys.RocketmqApplication;
import com.zys.rocketmqTemplate.mq.service.MqServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Pine
 * @Date: 2021/12/02/4:37 下午
 * @Desc:
 */
@SpringBootTest(classes = RocketmqApplication.class)
@RunWith(SpringRunner.class)
public class MqTest {
    @Autowired
    MqServiceImpl mqService;

    @Test
    public void baseMq(){

        mqService.BaseMq();
        try {
            Thread.sleep(100*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
