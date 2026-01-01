package Hackerrank.codingapi.RabbitMq;

import Hackerrank.codingapi.Utils.EmailStatus;
import Hackerrank.codingapi.emailService.EmailService;
import Hackerrank.codingapi.entities.EmailRequest;
import Hackerrank.codingapi.repositories.EmailRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailQueueConsumer {

    private final EmailRequestRepository emailRequestRepository;
    private final EmailService emailService;
    private final RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = RabbitConfig.EMAIL_QUEUE)
    public void consumeEmail(Long emailId) throws InterruptedException {
        EmailRequest emailRequest = this.emailRequestRepository.findById(emailId).orElseThrow();
        boolean sent = emailService.sendEmail(
                emailRequest.getToAddress(),
                emailRequest.getSubject(),
                emailRequest.getBody()
        );
        System.out.println("print send+++" + sent);
        int attempts = emailRequest.getAttempts();
        sent = false;

        if (sent) {
            emailRequest.setStatus(EmailStatus.SENT);
            emailRequestRepository.save(emailRequest);
            System.out.println("Day3 Worker :: EmailId " + emailId + " SENT ✅");
            return;
        }

        // FAIL case
        attempts++;
        emailRequest.setAttempts(attempts);
        emailRequest.setStatus(EmailStatus.FAILED);
        emailRequestRepository.save(emailRequest);

        if (attempts < 3) {

            long delay = (long) (Math.pow(2, attempts) * 5000);
            System.out.println("Day3 Worker :: EmailId " + emailId + " FAILED, retrying in " + delay / 1000 + "s");

            Thread.sleep(delay);

            rabbitTemplate.convertAndSend(RabbitConfig.EMAIL_QUEUE, emailId);
        } else {
            rabbitTemplate.convertAndSend(RabbitConfig.EMAIL_DLQ, emailId);
            System.out.println("Day3 Worker :: EmailId " + emailId + " FAILED → DLQ ❌");
        }
    }
}