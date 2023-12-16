package controllers;

import models.changepasswordDTO;
import models.signupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.SignupDAO;

import java.security.Principal;
import java.util.Collection;

@Controller
public class HelloWorldController {

	private SignupDAO signupDAO;

	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setSignupDAO(SignupDAO signupDAO) {
		this.signupDAO = signupDAO;
	}

	@GetMapping("/helloworld")
	public String helloworld(Principal principal , Authentication authentication , Model model)
	{
		String username=principal.getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		model.addAttribute("username_key" , username);
		model.addAttribute("authorities_key" , authorities);


		return "helloworld";
	}

	@RequestMapping("/mycustomlogin")
	public String show_login_page()
	{
		return "login";
	}


	@RequestMapping("/signup")
	public String show_signup_page(@ModelAttribute("sinupdto_key") signupDTO signupDTO  )
	{
		return "signup";
	}

	@RequestMapping("/signupprocess")
	public String signup_process(signupDTO signupdto)
	{
		signupDAO.save_user(signupdto);
		return "redirect:/mycustomlogin";
	}

	@RequestMapping("/coder")
	public String coder_page()
	{

		return "coder";
	}
	@RequestMapping("/trainer")
	public String trianer_page()
	{

		return "trainer";
	}

	@RequestMapping("/accessdenied")
	public String access_denied()
	{
		return "accessdenied";
	}

	@RequestMapping("/deleteuser")
	public String delete_user(@RequestParam("username") String username)
	{
		signupDAO.delete_user(username);
		return "redirect:/mycustomlogin";
	}



	@RequestMapping("/chagepassword")
	public String chage_password(@ModelAttribute("changepasswordDTO_key") changepasswordDTO changepassword)
	{
		return "changepassword" ;
	}

	@PostMapping("/savechange")
	public String sava_password(changepasswordDTO changepassword , Principal principal)
	{
		UserDetails userDetails= jdbcUserDetailsManager.loadUserByUsername(principal.getName());
		boolean matches= passwordEncoder.matches(changepassword.getOldpassword() , userDetails.getPassword());

		if(!changepassword.getNewpassword().equals(changepassword.getConfirmtpassword()))
		{
			return "redirect:/chagepassword?notMatched";
		}

		if(matches)
		{
			String encodedpassword=passwordEncoder.encode(changepassword.getConfirmtpassword());
			jdbcUserDetailsManager.changePassword(changepassword.getOldpassword() , encodedpassword);
			System.out.println("password changed");
			return "redirect:/helloworld";
		}


		return "redirect:/chagepassword?invalidpassword";
	}









}
