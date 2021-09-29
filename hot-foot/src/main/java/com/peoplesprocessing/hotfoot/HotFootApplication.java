package com.peoplesprocessing.hotfoot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class HotFootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotFootApplication.class, args);
	}
	
	@GetMapping(path = "/logout")
	public String logout(HttpServletRequest req) throws ServletException {
		req.logout();
		return "/";
	}

}
