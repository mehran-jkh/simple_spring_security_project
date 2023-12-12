package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controllers" , "services"})
public class mainconfig {


	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setSuffix(".jsp");
		viewResolver.setPrefix("/WEB-INF/views/");
		return viewResolver;
	}
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/postgres");
		dataSource.setUsername("postgres");
		dataSource.setPassword("123");
		return dataSource;
	}


	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}



}
