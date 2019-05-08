package com.tensquare.pojo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author:
 * @create: 2019-04-21 21:30
 **/
@Component
@RabbitListener(queues = "itcast")
public class Consumer1 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("1_itcast接收到的消息："+msg);


    }

}