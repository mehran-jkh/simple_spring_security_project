package controllers;

import models.signupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import services.SignupDAO;

import java.security.Principal;
import java.util.Collection;

@Controller
public class HelloWorldController {

	private SignupDAO signupDAO;



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



}
