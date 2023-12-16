package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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

import javax.sql.DataSource;

@EnableWebSecurity
public class security_config extends WebSecurityConfigurerAdapter
{

	public DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
				.authorizeRequests()
				.antMatchers("/helloworld")
				.authenticated()
				.antMatchers("/helloworld").authenticated()
				.antMatchers("/coder").hasAuthority("CODER")
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

		   auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(getPasswordEncoder());

	}
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception
//	{
//		InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager() ;
//
//		UserDetails userDetails1= User.withUsername("mehran").password("1234").roles("CODER" , "TRAINER").build();
//		UserDetails userDetails2= User.withUsername("jafar").password("4321").roles( "CODER").build();
//
//		inMemoryUserDetailsManager.createUser(userDetails1);
//		inMemoryUserDetailsManager.createUser(userDetails2);
//
//
//		auth.userDetailsService(inMemoryUserDetailsManager);
//	}

@Override
public void configure(WebSecurity web) throws Exception
{
	super.configure(web);
}


	@Bean
	PasswordEncoder getPasswordEncoder()
	{
		BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;

//		NoOpPasswordEncoder noOpPasswordEncoder= (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//		return noOpPasswordEncoder;
	}



}
