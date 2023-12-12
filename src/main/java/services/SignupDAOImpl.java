package services;

import models.signupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupDAOImpl implements SignupDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public void save_user(signupDTO signupDTO)
	{
		String encoded_password= passwordEncoder.encode(signupDTO.getPassword());
		String sql=" insert into users values (?,?,?)";
		String sql2=" insert into authorities values (?,?)";

		jdbcTemplate.update(sql , signupDTO.getUsername() , encoded_password ,true);
		jdbcTemplate.update(sql2 , signupDTO.getUsername() , "USER");





	}
}
