package Hackerrank.codingapi.RabbitMq.MockRabbitMQ.EmailEventImpl;

import Hackerrank.codingapi.RabbitMq.MockRabbitMQ.EmailEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!rabbit")
public class MockEmailEventPublisher implements EmailEventPublisher {
    @Override
    public void publishEmail(Long emailRequestId) {
        System.out.println("RabbitMQ disabled, skipping email queue");
    }
}
