package com.schoolmanagementsystem.common.producer;

import com.schoolmanagementsystem.common.events.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMqProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public final RabbitTemplate rabbitTemplate;

    public <T extends Event> void sendMessage(T event, String message) {
        log.info(String.format("%s event produced => %s", message, event.toString()));
//        Message<T> message = MessageBuilder
//                .withPayload(event)
//                .build();

        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}
