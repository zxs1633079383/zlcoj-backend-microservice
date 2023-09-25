package com.yupi.zlcojbackendquestionservice.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
public class InitRabbitMq {
    public static void doInit() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.189.101");
            factory.setUsername("zlc");
            factory.setPassword("123456");
            factory.setVirtualHost("/");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String EXCHANGE_NAME = "code_exchange";

            //创建队列
            String queueName = "code_queue";
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, EXCHANGE_NAME, "my_routingkey");
            log.info("消息队列启动成功");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("消息队列启动失败");
        }
    }
}
