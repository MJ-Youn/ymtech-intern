package com.dev2.intern.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value="/error")
public class ErrorPageController {
	
	@RequestMapping(value = "/404")
	public String error404() {
		log.warn("404 page load");
		
		return "/error/404";
	}
	
	@RequestMapping(value = "/403")
	public String error403() {
		log.warn("403 page load");
		
		return "/error/403";
	}
}
