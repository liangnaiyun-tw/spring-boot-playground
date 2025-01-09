package com.example.demorabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {


    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void listen(String message) {
        System.out.println("Received Message: " + message);
    }


}
