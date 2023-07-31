package com.schoolmanagementsystem.common.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMqConsumer {
    // we may inject here some service to handle the event

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consume(String message) {
        log.info(String.format("Message consumed => %s", message));

    }
}
