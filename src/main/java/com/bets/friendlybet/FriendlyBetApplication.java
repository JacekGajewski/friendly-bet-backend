package com.bets.friendlybet;

import com.bets.friendlybet.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class FriendlyBetApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendlyBetApplication.class, args);
	}

}
