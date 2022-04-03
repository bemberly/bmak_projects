package ca.sheridancollege.makonnen.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ca.sheridancollege.makonnen.services.UserDetailsServiceImpl;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	//Configuration is used to create an Authentication Manager for user authentication. Allows the use of UserDetailsService.
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	} 
	
	
	/* Allows configuring of web based security for specific http requests that permits users assigned role specifications,
	 * directs to a custom login page, handles unauthorized access attempts, and handles the logout process for users */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http.authorizeRequests()
				.antMatchers("/","/css/**").permitAll()
				.antMatchers("/secure/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/secure/**").hasRole("ADMIN") 
				.antMatchers("/**").hasRole("USER")
				.antMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()
				.and().formLogin()
					.loginPage("/login").permitAll()
					.defaultSuccessUrl("/homePage", true)
				.and().logout()
					.invalidateHttpSession(true)
					.clearAuthentication(true)
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/login?logout").permitAll()
				.and().exceptionHandling()
					.accessDeniedHandler(accessDeniedHandler); 
	}
	
	//A salted hash function for user passwords so they can be stored securely
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	

}
