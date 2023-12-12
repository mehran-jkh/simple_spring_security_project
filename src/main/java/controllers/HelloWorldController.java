package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

	@GetMapping("/helloworld")
	public String helloworld()
	{
		return "helloworld";
	}

	@RequestMapping("/mycustomlogin")
	public String show_login_page()
	{
		return "login";
	}




}
