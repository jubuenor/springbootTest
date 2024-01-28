package com.apitest.springtest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "hello")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Properties {
    private String message;
    private String name;
}
