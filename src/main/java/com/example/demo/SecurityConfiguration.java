package com.example.demo;



import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	
	@Bean
	public JdbcUserDetailsManager jdbcManager(DataSource dataSource) {
		
		return new JdbcUserDetailsManager(dataSource);
	}
	
	@Bean
	public PasswordEncoder encoder() {
		
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		return http
				.authorizeHttpRequests(a -> a.anyRequest()
						.authenticated())
				.httpBasic(Customizer.withDefaults())
				.build();
	}

}