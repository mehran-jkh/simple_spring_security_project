package services;

import models.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<CustomUser> load_user_by_username(String username)
	{
		String sql="select * from test_user where username= ?";

		Object[] args={username};

		List<CustomUser> customUsers=jdbcTemplate.query(sql , args , new BeanPropertyRowMapper<CustomUser>(CustomUser.class));

		return customUsers;
	}
}
