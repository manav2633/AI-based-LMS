package com.exam.portal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

	@GetMapping("/")
	public String index() {
		return "index1";
	}
	@GetMapping("/organiserlogin")
	public String login() {
		return "log-in.html";
	}
	@GetMapping("/trainee_signup")
	public String Signup() {
		return "sign-up.html";
	}
	@GetMapping("/courses")
	public String CourseLogin() {
		return "courses.html";
	}

	@GetMapping("/courses-details.html")
	public String CourseDetails() {
		return "courses-details.html";
	}

	
	@GetMapping("/contact.html")
	public String Contact() {
		return "contact.html";
	}
	@GetMapping("/sign-up.html")
	public String SignUpOrganization() {
		return "organization_signup.html";
	}
	@GetMapping("/sign-up-trainee.html")
	public String SignUpTrainee() {
		return "sign-up.html";
	}
	@GetMapping("/abcd")
	public String testing() {
		return "abc.html";
	}

	


	

	
	@GetMapping("/exam")
	public String exam() {
		return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/contact-us")
	public String contactUs() {
		return "contact";
	}
	
	
	@GetMapping("organiser/assign")
	public String Showroles() {
		return "organiser/assign_roles";
	}
}
