package com.myspace.rest.webservices.restfulwebservices.helloWorld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
//This will allow angular application with this url to use this service.
@CrossOrigin(origins="http://localhost:4200")
//Rest Controller is the combination of @Controller & @ResponseBody
@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path="/ping")
	public String ping() {
		return "Hello World :)";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		//throw new RuntimeException("Sorry Server maintainence contact the support team");
		return new HelloWorldBean("Hello World :)");
	}
	
	@GetMapping(path="/hello-world/{name}")
	public HelloWorldBean helloWorldName(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		//return "Good morning";
		 return messageSource.getMessage("good.morning.message", null, 
		LocaleContextHolder.getLocale());
	}
}
