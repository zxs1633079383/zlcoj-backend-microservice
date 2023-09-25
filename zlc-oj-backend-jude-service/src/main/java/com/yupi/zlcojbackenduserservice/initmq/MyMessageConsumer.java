package com.yupi.zlcojbackenduserservice.initmq;

import com.rabbitmq.client.Channel;
import com.yupi.zlcojbackenduserservice.jude.JudgeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MyMessageConsumer {

    @Resource
    private JudgeService judgeService;

    @SneakyThrows
    @RabbitListener(queues = {"code_queue"}, ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long delivery_tag) {
        log.info("receviceMessage message = {}", message);
        long questionSubmitId = Long.parseLong(message);
        try {
            judgeService.duJudge(questionSubmitId);
            channel.basicAck(delivery_tag, false);
        } catch (Exception e) {
            //执行失败重放  (也可放到死信队列)
            channel.basicNack(delivery_tag, false, false);
        }


    }


}
