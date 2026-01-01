package Hackerrank.codingapi.RabbitMq.MockRabbitMQ.EmailEventImpl;

import Hackerrank.codingapi.RabbitMq.MockRabbitMQ.EmailEventPublisher;
import Hackerrank.codingapi.RabbitMq.RabbitConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("rabbit")
public class RabbitEmailEventPublisher implements EmailEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    @Override
    public void publishEmail(Long emailRequestId) {

        rabbitTemplate.convertAndSend(RabbitConfig.EMAIL_EXCHANGE,
                RabbitConfig.EMAIL_ROUTING_KEY,
                emailRequestId);

    }
}
