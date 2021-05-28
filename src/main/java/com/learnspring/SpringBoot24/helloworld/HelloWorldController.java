package com.learnspring.SpringBoot24.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	//GET
	//URI -/hello-world
	//method-"Hello- World"
	
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorld helloWorldBean() {
		
		return new HelloWorld("Rakesh","This is myLife");
	}
	
	
	@GetMapping(path = "/hello-world-bean/path-variable/{name}")
	public HelloWorld helloWorldPathVariable(@PathVariable String name) {
		
		return new HelloWorld(String.format("Hello Word , %s",name));
	}
	/**
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language",required=false) Locale locale) {
		
		return messageSource.getMessage("good.morning.message",null, locale);
	}
	**/
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	}



}