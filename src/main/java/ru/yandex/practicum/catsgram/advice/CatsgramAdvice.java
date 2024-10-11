package ru.yandex.practicum.catsgram.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;

import java.time.LocalDateTime;

@ControllerAdvice
public class CatsgramAdvice {

    @ExceptionHandler(ParameterNotValidException.class)
    public ResponseEntity<ErrorMessage> ParameterNotValidExceptionHandler(ParameterNotValidException e) {
        return new ResponseEntity<>(new ErrorMessage(
                "reason - " + e.getReason() + " parameter - " + e.getParameter(),
                LocalDateTime.now(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConditionsNotMetException.class)
    public ResponseEntity<ErrorMessage> ConditionsNotMetExceptionHandler(ConditionsNotMetException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DuplicatedDataException.class)
    public ResponseEntity<ErrorMessage> validationExceptionHandler(DuplicatedDataException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), LocalDateTime.now(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> anyExceptionHandler(RuntimeException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
