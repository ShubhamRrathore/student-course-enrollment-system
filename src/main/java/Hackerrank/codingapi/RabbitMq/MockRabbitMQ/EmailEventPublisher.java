package Hackerrank.codingapi.RabbitMq.MockRabbitMQ;

import org.springframework.stereotype.Component;

public interface EmailEventPublisher {
    void publishEmail(Long emailRequestId);
}

