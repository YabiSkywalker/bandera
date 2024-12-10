package com.example.bandera;

import com.example.bandera.secrets.JwtTokenUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@OpenAPIDefinition(
		info = @Info(
				title = "Bandera Service",
				version = "1.0",
				description = "/// Central API service for customer data management. "
		)
)

@ComponentScan
@SpringBootApplication
public class BanderaApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(BanderaApplication.class, args);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
		LocalDateTime d = LocalDateTime.now();
		String formattedDate = d.format(formatter);

		System.out.println(formattedDate);
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		String token = jwtTokenUtil.generateToken("skywalker12!@#!");
		System.out.println("Generated Token: " + token);


	}

}
