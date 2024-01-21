package services;

import models.signupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class SignupDAOImpl implements SignupDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

	@Override
	public void save_user(signupDTO signupDTO)
	{
		String encoded_password= passwordEncoder.encode(signupDTO.getPassword());
//		String sql=" insert into users values (?,?,?)";
//		String sql2=" insert into authorities values (?,?)";
//
//		jdbcTemplate.update(sql , signupDTO.getUsername() , encoded_password ,true);
//		jdbcTemplate.update(sql2 , signupDTO.getUsername() , "USER");


		UserDetails userDetails= User
				.withUsername(signupDTO.getUsername())
				.password(encoded_password)
				.roles("CODER")
				.build();
		jdbcUserDetailsManager.createUser(userDetails);





	}

	@Override
	public void delete_user(String username)
	{
		jdbcUserDetailsManager.deleteUser(username);

	}


}
