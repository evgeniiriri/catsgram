package ru.yandex.practicum.catsgram.exception;

public class CatsgramInternalServerException extends RuntimeException {
    public CatsgramInternalServerException(String message) {
        super(message);
    }
}
