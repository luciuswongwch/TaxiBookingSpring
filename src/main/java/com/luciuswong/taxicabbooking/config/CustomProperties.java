package com.luciuswong.taxicabbooking.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Component("customProperties")
@Data
@PropertySource("classpath:custom.configuration.properties")
@ConfigurationProperties(prefix="taxicabbooking")
@Validated
public class CustomProperties {
    @Min(value=5, message="Page size configuration must be between 5 and 25")
    @Max(value=25, message="Page size configuration must be between 5 and 25")
    private int pageSize;
    private List<String> weakPasswords;
}
