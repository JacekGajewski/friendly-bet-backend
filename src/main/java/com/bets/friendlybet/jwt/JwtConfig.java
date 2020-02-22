package com.bets.friendlybet.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "application.jwt")
@Data
public class JwtConfig {

    private String secretKey = "secuhukgfhjlkhkghfxghfhkjlghdfhdghkgfkfxjgrxktyvkhgvlutfjyrzjyrxlutltxykzreKey9";
    private String tokenPrefix = "Bearer ";
    private Integer tokenExpirationAfterDays = 14;

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
