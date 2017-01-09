package com.dev2.intern.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(method=RequestMethod.GET)
public class PageController {

	@RequestMapping(value="/")
	public String index() {
		log.info("index page load");
		
		return "redirect:board/1";
	}
	
	@RequestMapping(value="/header")
	public String header() {
		log.info("header page load");
		
		return "header";
	}
	
	@RequestMapping(value="/footer")
	public String footer() {
		log.info("footer page load");
		
		return "footer";
	}
	
	@RequestMapping(value="/board/{boardNumber}")
	public String board(@PathVariable("boardNumber") String boardNumber) {
		log.info(boardNumber + " board page load");
		
		return "board";
	}
	
	@RequestMapping(value="/board/{boardNumber}/write")
	public String write(@PathVariable("boardNumber") String boardNumber) {
		log.info(boardNumber + " board writing page load");
		
		return "write";
	}
}
