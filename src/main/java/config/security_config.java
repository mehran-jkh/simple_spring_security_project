package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
				.and()
				.formLogin().loginPage("/mycustomlogin")
				.and()
				.httpBasic()
				.and()
				.logout()
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
	}



}
