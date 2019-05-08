package com.test;

import com.tensquare.RabbitmqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author:
 * @create: 2019-04-21 21:26
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqApplication.class)
public class RabbitmqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMsg1(){
        rabbitTemplate.convertAndSend("itcast","直接模式发送的消息");
    }

    @Test
    public void sendMsg2(){
        rabbitTemplate.convertAndSend("chuanzhi","","分裂模式发送的消息");
    }

    @Test
    public void sendMsg3(){
        rabbitTemplate.convertAndSend("mytopic","good.log","主题模式发送的消息");
    }

}