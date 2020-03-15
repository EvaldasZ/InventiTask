package com.inventi.codingchallenge;

import com.inventi.codingchallenge.models.BankOperation;
import com.inventi.codingchallenge.models.CurrencyCode;
import com.inventi.codingchallenge.repositories.StaticCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

@SpringBootApplication
public class CodingChallengeApplication {

	public static void main(String[] args) {
		StaticCache.BankOperations.add(new BankOperation(
				"1",
				"2",
				LocalDateTime.of(2020, 11, 1, 0, 0),
				"null",
				new BigDecimal("1.1212"),
				CurrencyCode.EUR));

		SpringApplication.run(CodingChallengeApplication.class, args);
	}

}
