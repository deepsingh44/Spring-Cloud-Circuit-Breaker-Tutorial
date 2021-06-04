package com.deepsingh44.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	private GreetingService greetingService;

	@GetMapping("/message")
	public String message() {
		System.out.println("inside ..1");
		return greetingService.getMessage();
	}

}
