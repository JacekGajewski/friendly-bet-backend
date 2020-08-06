package com.bets.friendlybet.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtConfig {

    private String secretKey = System.getenv("SECRET_KEY");
    private String tokenPrefix = System.getenv("TOKEN_PREFIX");
    private String tokenExpirationAfterDays = System.getenv("TOKEN_EXP_DAYS");

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
