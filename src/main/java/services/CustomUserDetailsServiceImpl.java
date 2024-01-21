package services;

import models.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		List<CustomUser> customUsers= userDAO.load_user_by_username(username);

		if(customUsers.size()==0)
			throw new UsernameNotFoundException(username+" not found");

		ArrayList<GrantedAuthority> authorities=new ArrayList<>();

		SimpleGrantedAuthority authority
				=new SimpleGrantedAuthority("CODER");

		authorities.add(authority);

		UserDetails user=User.withUsername(username)
				.password(customUsers.get(0).getPassword())
				.roles("CODER").build();
		return user;
	}
}
