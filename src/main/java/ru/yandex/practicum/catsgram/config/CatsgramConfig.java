package ru.yandex.practicum.catsgram.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties("application")
public class CatsgramConfig {

    private String imageDirectory;
}
