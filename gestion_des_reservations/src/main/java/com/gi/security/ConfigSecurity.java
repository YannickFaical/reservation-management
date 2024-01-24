package com.gi.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class ConfigSecurity {
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
	   httpSecurity.formLogin().defaultSuccessUrl("/");
	   httpSecurity.authorizeHttpRequests().requestMatchers("/").permitAll();
	   httpSecurity.authorizeHttpRequests().requestMatchers("/clts").hasRole("USER");
	   httpSecurity.authorizeHttpRequests().requestMatchers("/**ajout**").hasRole("ADMIN");
	   httpSecurity.authorizeHttpRequests().requestMatchers("/**save**").hasRole("ADMIN");
	   httpSecurity.authorizeHttpRequests().requestMatchers("/**edit**").hasRole("ADMIN");
	   httpSecurity.authorizeHttpRequests().requestMatchers("/**update**").hasRole("ADMIN");
	   httpSecurity.authorizeHttpRequests().requestMatchers("/**delete**").hasRole("ADMIN");
	   httpSecurity.authorizeHttpRequests().requestMatchers("/**supprimer**").hasRole("ADMIN");
	   httpSecurity.authorizeHttpRequests().requestMatchers("/**formFacture**").hasRole("ADMIN");
		httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
		return httpSecurity.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(); 
		String motDePasse=encoder.encode("1234");
		UserDetails user=User.withUsername("ali")
			.password("{bcrypt}" + motDePasse)
			.roles("USER")
			.build();
		UserDetails admin=User.withUsername("mohamed")
			.password("{bcrypt}" + motDePasse)
			.roles("USER","ADMIN")
			.build();
		return new InMemoryUserDetailsManager(user,admin);
		
		
		
		
	}
	
	


	//@Bean
	public UserDetailsManager user(DataSource dataSource) {
//		
//	BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
//	String motDePasse1=encoder.encode("1234");
//	String motDePasse2=encoder.encode("5678");
//	UserDetails user1=User.builder().username("elabid").password("{bcrypt}" + motDePasse1).roles("USER").build();
//	UserDetails user2=User.builder().username("alami").password("{bcrypt}" + motDePasse2).roles("USER","ADMIN").build();
//	JdbcUserDetailsManager user=new JdbcUserDetailsManager(dataSource);
//	user.createUser(user1);
//	user.createUser(user2);
//		
		JdbcUserDetailsManager user=new JdbcUserDetailsManager(dataSource);
		user.loadUserByUsername("elabid");
		user.loadUserByUsername("alami");

	return user;
	}

}
