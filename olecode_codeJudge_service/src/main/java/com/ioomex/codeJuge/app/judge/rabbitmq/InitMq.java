package com.ioomex.codeJuge.app.judge.rabbitmq;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitMq {

    @Value("${spring.rabbitmq.host:localhost}")
    private String localAdr;


    @PostConstruct
    public  void doInit() {
        try {
            // 创建连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(localAdr);

            // 创建连接
            Connection connection = factory.newConnection();

            // 创建频道
            Channel channel = connection.createChannel();

            // 声明交换机
            String exchangeName = "code_exchange";
            channel.exchangeDeclare(exchangeName, "direct");

            // 创建队列，随机分配一个队列名称
            String queueName = "code_queue";
            channel.queueDeclare(queueName, true, false, false, null);

            // 绑定队列到交换机
            channel.queueBind(queueName, exchangeName, "my_routingKey");

            // 关闭频道和连接
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
