package com.dev2.intern.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PageController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		log.info("main page load");
		
		return "main";
	}
	
	@RequestMapping(value="/header", method=RequestMethod.GET)
	public String header() {
		log.info("header page load");
		
		return "header";
	}
	
	@RequestMapping(value="/footer", method=RequestMethod.GET)
	public String footer() {
		log.info("footer page load");
		
		return "footer";
	}
	
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public String board(String boardNumber) {
		log.info("board page load");
		
		return "board";
	}
	
//	@RequestMapping(value="/board/{boardNumber}", method=RequestMethod.GET)
//	public String board(@PathVariable("boardNumber") String boardNumber) {
//		log.info(boardNumber + " board page load");
//		
//		return "board";
//	}
}
