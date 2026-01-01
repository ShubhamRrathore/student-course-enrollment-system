package Hackerrank.codingapi.entities;

import Hackerrank.codingapi.Utils.EmailStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "email_request")
@NoArgsConstructor
@Getter
@Setter
public class EmailRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Long id ;

    private String toAddress;
    private String Subject;

    @Column(columnDefinition = "text")
    private  String body;
    @Enumerated(EnumType.STRING)

    private EmailStatus status = EmailStatus.PENDING;

    private int attempts = 0;

    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();



}
