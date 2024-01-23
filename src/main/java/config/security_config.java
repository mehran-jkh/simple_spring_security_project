package config;

import filters.MyAuthenticationLoggerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import services.CustomUserDetailsServiceImpl;
import services.MyCustomFormAuthenticationProvider;

import javax.sql.DataSource;

@EnableWebSecurity
public class security_config extends WebSecurityConfigurerAdapter
{

	public DataSource dataSource;
	public CustomUserDetailsServiceImpl customUserDetailsService;


	private DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public CustomUserDetailsServiceImpl getCustomUserDetailsService() {
		return customUserDetailsService;
	}

	@Autowired
	public void setCustomUserDetailsService(CustomUserDetailsServiceImpl customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}


	private MyCustomFormAuthenticationProvider myCustomFormAuthenticationProvider;

	@Autowired
	public void setMyCustomFormAuthenticationProvider(MyCustomFormAuthenticationProvider myCustomFormAuthenticationProvider) {
		this.myCustomFormAuthenticationProvider = myCustomFormAuthenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
				.addFilterAfter(new MyAuthenticationLoggerFilter() , BasicAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/helloworld")
				.authenticated()
				.antMatchers("/helloworld").authenticated()
				.antMatchers("/coder").hasAuthority("ROLE_CODER")
				.antMatchers("/trainer").hasAuthority("TRAINER")

				.and()
				.formLogin().loginPage("/mycustomlogin")
				.and()
				.httpBasic()
				.and()
				.logout()
				.and()
				.exceptionHandling().accessDeniedPage("/accessdenied")
				;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
//		auth.inMemoryAuthentication()
//				.withUser("mehran")
//				.password("1234")
//				.roles("admin");

//		   auth.jdbcAuthentication()
//				   .dataSource(dataSource)
//				   .usersByUsernameQuery("select username,password,is_active from test_user where username=?")
//				   .authoritiesByUsernameQuery("select username,role_name from test_roles where username=?")
//				   .passwordEncoder(getPasswordEncoder());



//		auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncoder());
		auth.authenticationProvider(myCustomFormAuthenticationProvider);



	}


@Override
public void configure(WebSecurity web) throws Exception
{
	super.configure(web);
}


	@Bean
	PasswordEncoder getPasswordEncoder()
	{
//		BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
//		return bCryptPasswordEncoder;

		NoOpPasswordEncoder noOpPasswordEncoder= (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
		return noOpPasswordEncoder;
	}



}
