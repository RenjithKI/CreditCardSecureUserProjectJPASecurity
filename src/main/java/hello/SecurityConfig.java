package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/").access("hasRole('CUSTOMER')")
		.antMatchers("/**").permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.failureUrl("/login?error=true");
	}

	@Override
	protected void configure( AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsService() {


			@Override
			public Customer loadUserByUsername(String username)
					throws UsernameNotFoundException {
				Customer cardUserDetails = customerRepository.findByUsername(username);
				if (cardUserDetails != null) {
					return cardUserDetails;
				}
				throw new UsernameNotFoundException("User '" + username + "' not found.");

			}
		});
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
