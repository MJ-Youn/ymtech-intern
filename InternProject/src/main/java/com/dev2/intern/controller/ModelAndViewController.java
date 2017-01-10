package com.dev2.intern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dev2.intern.service.BoardService;
import com.dev2.intern.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(method = RequestMethod.GET)
public class ModelAndViewController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "/")
	public String index() {
		log.info("index page load");
		int firstBoardId = boardService.calculateBoardId();

		return "redirect:board/" + firstBoardId + "/page/1";
	}

	@RequestMapping(value = "/header")
	public ModelAndView header() {
		log.info("header page load");
		ModelAndView modelAndView = new ModelAndView("header");
		modelAndView.addObject("boardList", boardService.listUpBoard());
		
		return modelAndView;
	}

	@RequestMapping(value = "/footer")
	public String footer() {
		log.info("footer page load");

		return "footer";
	}

	@RequestMapping(value = "/board/{boardNumber}/page/{pageNumber}")
	public ModelAndView board(@PathVariable("boardNumber") String boardNumber,
						@PathVariable("pageNumber") String pageNumber) {
		log.info(boardNumber + " board page load");
		ModelAndView modelAndView = new ModelAndView("board");
		modelAndView.addObject("pageCount", postService.countPageNumber(boardNumber));
		modelAndView.addObject("postList", postService.listUpPost(boardNumber, pageNumber));

		return modelAndView;
	}

	@RequestMapping(value = "/board/{boardNumber}/write")
	public String write(@PathVariable("boardNumber") String boardNumber) {
		log.info(boardNumber + " board writing page load");

		return "write";
	}

	@RequestMapping(value = "/board/{boardNumber}/post/{postId}")
	public ModelAndView post(@PathVariable("boardNumber") String boardNumber,
							@PathVariable("postId") String postId) {
		log.info("view " + postId + "post");
		ModelAndView modelAndView = new ModelAndView("post");
		modelAndView.addObject("post", postService.getPostById(postId));
		
		return modelAndView;
	}
}
