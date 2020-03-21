package com.bets.friendlybet.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "application.jwt")
@Data
//@Component
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private String tokenExpirationAfterDays = "14";

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
