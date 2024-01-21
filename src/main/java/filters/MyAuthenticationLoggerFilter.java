package filters;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;


public class MyAuthenticationLoggerFilter implements Filter {


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null)
			System.out.println("username is "+authentication.getName()+" and role is "+ authentication.getAuthorities());

		chain.doFilter(request , response);
	}
}
