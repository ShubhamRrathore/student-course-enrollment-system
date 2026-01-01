package Hackerrank.codingapi.emailService;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Value("${sendgrid.api.key}")
    private String sendgridKey;


    @Value("${email.from}")
    private String fromEmail;

    @PostConstruct
    public void check() {
        System.out.println("KEY = " + sendgridKey + " " + "fromEmail = " + fromEmail);
    }


    public boolean sendEmail(String to, String subject, String body) {

        System.out.println("Inside the sendEmail api");
        Email from = new Email(fromEmail);
        Email recipient = new Email(to);

        System.out.println("print from "+ from + " " + "recipient" + recipient);

        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, recipient, content);

        SendGrid sg = new SendGrid(sendgridKey);
        System.out.println("sg pring please" + sg);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println( "response.getStatusCode())" + response.getStatusCode());
            return response.getStatusCode() == 202;
        } catch (Exception ex) {
            System.out.println("inside exception");
            return false;
        }
    }
}
