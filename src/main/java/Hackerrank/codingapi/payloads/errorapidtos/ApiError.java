package Hackerrank.codingapi.payloads.errorapidtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class ApiError {

        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;

        // getters/setters + builder

}
