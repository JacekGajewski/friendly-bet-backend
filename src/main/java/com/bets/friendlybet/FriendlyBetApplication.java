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

//	TODO: 1) Block creating users with id 0. When creatorId/RivalId is null, id 0 is assigned.
//		  2) When one user deletes a bet check if there is no other user attached to this bet.
//		  3) Required, unique etc. for Entities
//	      4) Validation
//		  5) Tests
}
