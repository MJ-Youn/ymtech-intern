package com.dev2.intern.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dev2.intern.service.BoardService;
import com.dev2.intern.service.CommentService;
import com.dev2.intern.service.PostService;
import com.dev2.intern.util.ResponseHeaderUtil;
import com.dev2.intern.vo.ModifyCommentVO;
import com.dev2.intern.vo.ModifyPostVO;
import com.dev2.intern.vo.ResponseVO;
import com.dev2.intern.vo.WriteCommentVO;
import com.dev2.intern.vo.WritePostVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ModelAndViewController {

	private static ResponseVO SUCCESS_RESPONSE = new ResponseVO().setHeader(ResponseHeaderUtil.RESPONSE_SUCCESS_HEADER); 
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		log.info("index page load");
		int firstBoardId = boardService.calculateBoardId();

		return "redirect:board/" + firstBoardId + "/page/1";
	}

	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public ModelAndView header() {
		log.info("header page load");
		ModelAndView modelAndView = new ModelAndView("header");
		modelAndView.addObject("boardList", boardService.listUpBoard());
		
		return modelAndView;
	}

	@RequestMapping(value = "/footer", method = RequestMethod.GET)
	public String footer() {
		log.info("footer page load");

		return "footer";
	}

	@RequestMapping(value = "/board/{boardNumber}/page/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView board(@PathVariable("boardNumber") String boardNumber,
						@PathVariable("pageNumber") String pageNumber) {
		log.info(boardNumber + " board page load");
		ModelAndView modelAndView = new ModelAndView("board");
		modelAndView.addObject("pageCount", postService.countPageNumber(boardNumber));
		modelAndView.addObject("postList", postService.listUpPost(boardNumber, pageNumber));

		return modelAndView;
	}

	@RequestMapping(value = "/board/{boardNumber}/write", method = RequestMethod.GET)
	public String write(@PathVariable("boardNumber") String boardNumber) {
		log.info(boardNumber + " board writing page load");

		return "write";
	}

	@RequestMapping(value = "/board/{boardNumber}/post/{postId}", method = RequestMethod.GET)
	public ModelAndView post(@PathVariable("boardNumber") String boardNumber,
							@PathVariable("postId") String postId) {
		log.info("view " + postId + " post");
		ModelAndView modelAndView = new ModelAndView("post");
		modelAndView.addObject("post", postService.getPostById(postId));
		modelAndView.addObject("comments", commentService.listUpComment(postId));
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/board/{boardNumber}/post/{postId}/modify", method = RequestMethod.GET)
	public ModelAndView modify(@PathVariable("boardNumber") String boardNumber,
						@PathVariable("postId") String postId) {
		log.info("modify " + postId + " post");
		ModelAndView modelAndView = new ModelAndView("write");
		modelAndView.addObject("post", postService.getPostById(postId));
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVO writePost(@RequestBody WritePostVO writePostVO) {
		log.info(writePostVO.getBoardId() + " board's new posting");
		postService.postPost(writePostVO);
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseVO deletePost(@RequestBody Map<Object, Object> body) {
		int postNumber = (Integer)body.get("postNumber");
		log.info(postNumber + " Post is deleted");
		postService.deletePost(postNumber);

		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseVO modifyPost(@RequestBody ModifyPostVO modifyPostVO) {
		log.info(modifyPostVO.getId() + " Post is modify");
		postService.modifyPost(modifyPostVO);
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVO writeComment(@RequestBody WriteCommentVO writeCommentVO) {
		log.info(writeCommentVO.getPostId() + " Post's new comment");
		commentService.writeComment(writeCommentVO);
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseVO deleteComment(@RequestBody Map<Object, Object> body) {
		int commentId = (Integer)body.get("commentId");
		log.info(commentId + " Comment is deleted");
		commentService.deleteComment(commentId);
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseVO modifyComment(@RequestBody ModifyCommentVO modifyCommentVO) {
		log.info(modifyCommentVO.getId() + " Comment is modify");
		commentService.modifyComment(modifyCommentVO);
		
		return SUCCESS_RESPONSE;
	}
}
