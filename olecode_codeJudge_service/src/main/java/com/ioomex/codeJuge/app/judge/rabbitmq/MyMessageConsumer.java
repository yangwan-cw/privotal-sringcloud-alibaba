package com.ioomex.codeJuge.app.judge.rabbitmq;

import com.ioomex.codeJuge.app.judge.JudgeService;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MyMessageConsumer {

    @Resource
    private JudgeService judgeService;

    // 指定程序监听的消息队列和确认机制
    @SneakyThrows
    @RabbitListener(queues = "code_queue", ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        log.info("receiveMessage message = {}", message);
        log.info("receiveMessage message = {}", message);
        long questionSubmitId;

        try {
            // 将消息转换为 Long 类型
            questionSubmitId = Long.parseLong(message);

            // 调用服务进行处理
            judgeService.doJudge(questionSubmitId);

            // 确认消息
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            // 处理异常，拒绝消息并不重新入队
            log.error("Error processing message", e);
            channel.basicNack(deliveryTag, false, false);
        }
    }
}
