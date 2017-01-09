package com.dev2.intern.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev2.intern.service.BoardService;
import com.dev2.intern.util.ResponseHeaderUtil;
import com.dev2.intern.vo.PostVO;
import com.dev2.intern.vo.ResponseVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiController {

	private static ResponseVO SUCCES_RESPONSE = new ResponseVO().setHeader(ResponseHeaderUtil.RESPONSE_SUCCESS_HEADER);

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/board/{boardNumber}/page", method = RequestMethod.GET)
	public ResponseVO getPageCount(@PathVariable("boardNumber") String boardNumber) {
		log.info("Get Board(" + boardNumber + ") page count");
		Map<Object, Object> pageCount = boardService.getPageCount(boardNumber);
		
		return SUCCES_RESPONSE.setBody(pageCount);
	}
	
	@RequestMapping(value = "/board/{boardNumber}/page/{pageNumber}", method = RequestMethod.GET)
	public ResponseVO getPostList(@PathVariable("boardNumber") String boardNumber,
									@PathVariable("pageNumber") String pageNumber) {
		log.info("Get Board: " + boardNumber + ", Pages: " + pageNumber);
		ArrayList<PostVO> postList = boardService.getPostList(boardNumber, pageNumber);
		
		return SUCCES_RESPONSE.setBody(postList);
	}
}
