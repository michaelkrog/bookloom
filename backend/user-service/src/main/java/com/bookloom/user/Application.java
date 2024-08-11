package com.bookloom.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * The main entry point for the Spring Boot application.
 *
 * This class is annotated with {@link SpringBootApplication} which denotes that it is the primary Spring Boot configuration class.
 * It also enables MongoDB repositories through the {@link EnableMongoRepositories} annotation.
 */
@SpringBootApplication
@EnableMongoRepositories
public class Application {

	/**
	 * The main method serves as the entry point of the Spring Boot application.
	 *
	 * It invokes {@link SpringApplication#run(Class, String...)} with the current class and command-line arguments.
	 *
	 * @param args command-line arguments passed during the application startup.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Configures Cross-Origin Resource Sharing (CORS) settings for the application.
	 *
	 * This bean defines a {@link WebMvcConfigurer} that sets up CORS mappings. Specifically, it allows requests from
	 * "http://localhost:3000" to access resources from this application.
	 *
	 * @return a {@link WebMvcConfigurer} instance with custom CORS configuration.
	 */
	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// Allow CORS requests from "http://localhost:3000"
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}

	@Bean
	public SecurityFilterChain web(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						//.requestMatchers("/**").hasAuthority("USER")
						.anyRequest().authenticated()
				);

		// Add Basic Auth
		http.httpBasic(Customizer.withDefaults());

		// Configure CORS
		http.cors(cors -> configurationSource());

		// Disable CSRF (For demo purposes only)
		http.csrf(AbstractHttpConfigurer::disable);

		return http.build();
	}

	@Bean()
	public UrlBasedCorsConfigurationSource configurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);

		config.setAllowedOrigins(List.of("http://localhost:3000"));

		config.addAllowedHeader("accept");
		config.addAllowedHeader("authorization");
		config.addAllowedHeader("content-type");

		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("DELETE");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}*/
}
