package ru.yandex.practicum.catsgram.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {

    private String message;
    private LocalDateTime dateTime;
    private HttpStatus httpStatus;

}
