package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utils.SmsUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description:
 * @author:
 * @create: 2019-04-25 22:24
 **/
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @Autowired
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.template_code}")
    private String template_code;

    @Value("${aliyun.sms.sign_name}")
    private String sign_name;

    @RabbitHandler
    public Result sendMsg(Map<String,String> msg){
        String mobile = msg.get("mobile");
        String checkcode = msg.get("checkcode");
        System.out.println("手机号："+msg.get("mobile"));
        System.out.println("验证码："+msg.get("checkcode"));

        smsUtil.sendSms_new(mobile, template_code, sign_name, "{\"checkcode\":\""+checkcode+"\"}");

        return new Result(true, StatusCode.OK,"发送短信成功");
    }

}