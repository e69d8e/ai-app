package com.li.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JWTProperties {
    private String secretKey;
    private long ttlMillis;
    private String tokenHead;
}