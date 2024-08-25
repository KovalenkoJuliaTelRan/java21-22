package telran.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class AuthorizationConfiguration
{
	@Autowired
	CustomWebSecurity customWebSecurity;

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception
	{
		http.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
		
		http.addFilterBefore(new ExpiredPasswordFilter(), BasicAuthenticationFilter.class);
		http.authorizeHttpRequests(
				authorize -> authorize.requestMatchers("/account/register", "/account/register/", "/models", "/model")
						.permitAll()
						.requestMatchers(HttpMethod.PUT, "/account/revoke/*", "/account/activate/*").hasRole("ADMIN")
						.requestMatchers("/account/user/*/role/*").hasAnyRole("ADMIN", "SUPERADMIN")
//				.requestMatchers(HttpMethod.PUT, "/account/user/{login}").access("@customWebSecurity.checkOwner(#login)") -> v 5
						.requestMatchers(HttpMethod.PUT, "/account/user/{login}")
//				.access(new WebExpressionAuthorizationManager("@customWebSecurity.checkOwner(#login)")) // -> v 6.1
						.access((auth, context) -> new AuthorizationDecision(
								customWebSecurity.checkOwner(context.getVariables().get("login")))) // -> 6.2
						.requestMatchers(HttpMethod.GET, "/account/*/{login}").access(new WebExpressionAuthorizationManager(
								"#login == authentication.name or hasRole('ADMIN')"))
						.requestMatchers(HttpMethod.DELETE, "/account/user/{login}").access(new WebExpressionAuthorizationManager(
								"#login == authentication.name or hasRole('ADMIN')"))
						
						.requestMatchers("/records/*/*").hasRole("TECHNICIAN")
						.requestMatchers("/driver/add", "/car/return", "/model/cars", "/car/rent").hasRole("CLERK")
						.requestMatchers("/driver/cars", "/drivers/car", "/driver").hasAnyRole("CLERK", "DRIVER")
						.requestMatchers("/drivers/active", "/models/*").hasRole("STATIST")
						.requestMatchers("/*/remove", "/car/add", "/model/add").hasRole("MANAGER")
						.requestMatchers("/account/login", "/account/password", "/car/**").authenticated()
						.anyRequest().denyAll());

		return http.build();
	}
}
//  account/role/{role}/user/{user} -> account/role/*/user/*
//	account/role/user/{role}/{user}, account/role/user/{role}, account/role/user,  account/role/user/{role}/{user}/aaaa
//			-> accoun?/role/user/**