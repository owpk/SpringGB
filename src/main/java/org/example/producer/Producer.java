package org.example.producer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final String exchangeName;

    public Producer(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public void produce(String routingKey, String msg) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true);
            channel.basicPublish(exchangeName, routingKey, MessageProperties.TEXT_PLAIN,
                    msg.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + routingKey + "':'" + msg + "'");
        }
    }
}
