package services;

import models.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyCustomFormAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		System.out.println("inside my custom provider!!!");
		String username= authentication.getName();
		String password = authentication.getCredentials().toString();

		List<CustomUser> customUserLis=userDAO.load_user_by_username(username);
		if(customUserLis.size()==0)
			throw new BadCredentialsException("user doesn't exist ");

		CustomUser customUser= customUserLis.get(0);

		boolean isMatches=passwordEncoder.matches(password , customUser.getPassword() );

		if(!isMatches)
			throw  new BadCredentialsException("invalid username/password");

		List<GrantedAuthority> authorities=new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("CODER"));

		return new UsernamePasswordAuthenticationToken(username , password ,authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
