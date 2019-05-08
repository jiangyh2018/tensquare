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
@RabbitListener(queues = "it")
public class Consumer3 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("3_it接收到的消息："+msg);


    }

}