package change.me.infrastructure.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * More details about this class at: https://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
            http.csrf()
				// FIXME: should disable CSRF only for the /api/** context.
				.disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
        // Fakes  2 users, one with role "USER" and another with "USER" and "MANAGER".
		// FIXME: It's not recommended to use any in memory hardcoded passwords! This is just a simple example on how
		// to do authentication. The "{noop}" indicates to Spring that it should use NoOpPasswordEncoder.
		auth.inMemoryAuthentication()
				.withUser("user").password("{noop}myveryinsecurepassword123").roles("USER")
				.and()
				.withUser("anotheruser").password("{noop}anotherveryinsecurepassword123").roles("MANAGER").roles("USER");
	}

}
