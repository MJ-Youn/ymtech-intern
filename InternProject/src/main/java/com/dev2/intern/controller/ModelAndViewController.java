package com.dev2.intern.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dev2.intern.exception.ExistEmailException;
import com.dev2.intern.service.impl.AdminService;
import com.dev2.intern.service.impl.BoardService;
import com.dev2.intern.service.impl.CommentService;
import com.dev2.intern.service.impl.FileService;
import com.dev2.intern.service.impl.PostService;
import com.dev2.intern.service.impl.UserService;
import com.dev2.intern.util.HashMapUtil;
import com.dev2.intern.util.ResponseHeaderUtil;
import com.dev2.intern.util.UserGradeUtil;
import com.dev2.intern.vo.CreateUserVO;
import com.dev2.intern.vo.FileVO;
import com.dev2.intern.vo.ModifyCommentVO;
import com.dev2.intern.vo.ModifyPostVO;
import com.dev2.intern.vo.ModifyUserVO;
import com.dev2.intern.vo.ResponseVO;
import com.dev2.intern.vo.UserVO;
import com.dev2.intern.vo.WriteCommentVO;
import com.dev2.intern.vo.WritePostVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ModelAndViewController {

	private static ResponseVO SUCCESS_RESPONSE = new ResponseVO().setHeader(ResponseHeaderUtil.RESPONSE_SUCCESS_HEADER); 
	private static ResponseVO EXIST_MAIL_RESPONSE = new ResponseVO().setHeader(ResponseHeaderUtil.RESPONSE_EXIST_EMAIL_MESSAGE);
	
	@Autowired
	@Qualifier(BoardService.BEAN_QUALIFIER)
	private BoardService boardService;
	
	@Autowired
	@Qualifier(PostService.BEAN_QUALIFIER)
	private PostService postService;
	
	@Autowired
	@Qualifier(CommentService.BEAN_QUALIFIER)
	private CommentService commentService;
	
	@Autowired
	@Qualifier(FileService.BEAN_QUALIFIER)
	private FileService fileService;
	
	@Autowired
	@Qualifier(UserService.BEAN_QUALIFIER)
	private UserService userService;
	
	@Autowired
	@Qualifier(AdminService.BEAN_QUALIFIER)
	private AdminService adminService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		log.info("index page load");
		int firstBoardId = boardService.calculateBoardId();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String grade = auth.getAuthorities().toArray()[0].toString();
		
		if (UserGradeUtil.getGradeNameByLevel(-1).equals(grade) == true) {
			return "redirect:login?false";
		} else {
			return "redirect:board/" + firstBoardId + "/page/1";
		}
	}

	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public ModelAndView header() {
		ModelAndView modelAndView = new ModelAndView("header");
		modelAndView.addObject("boardList", boardService.listUpBoard());
		
		return modelAndView;
	}

	@RequestMapping(value = "/footer", method = RequestMethod.GET)
	public String footer() {
		return "footer";
	}

	@RequestMapping(value = "/board/{boardNumber}/page/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView board(@PathVariable("boardNumber") String boardNumber,
						@PathVariable("pageNumber") String pageNumber) {
		log.info("{} board page load", boardNumber);
		ModelAndView modelAndView = new ModelAndView("board");

		modelAndView.addObject("pageCount", postService.countPageNumber(boardNumber));
		modelAndView.addObject("postList", postService.listUpPost(boardNumber, pageNumber));

		return modelAndView;
	}

	@RequestMapping(value = "/board/{boardNumber}/write", method = RequestMethod.GET)
	public String write(@PathVariable("boardNumber") String boardNumber) {
		log.info("{} board writing page load", boardNumber);

		return "write";
	}

	@RequestMapping(value = "/board/{boardNumber}/post/{postId}", method = RequestMethod.GET)
	public ModelAndView post(@PathVariable("boardNumber") String boardNumber,
							@PathVariable("postId") String postId) {
		log.info("view {} post ", postId);
		ModelAndView modelAndView = new ModelAndView("post");
		modelAndView.addObject("post", postService.getPostById(postId));
		modelAndView.addObject("file", fileService.getFileByPostId(postId));
		modelAndView.addObject("comments", commentService.listUpComment(postId));
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/board/{boardNumber}/post/{postId}/modify", method = RequestMethod.GET)
	public ModelAndView modify(@PathVariable("boardNumber") String boardNumber,
						@PathVariable("postId") String postId) {
		log.info("modify {} post", postId);
		ModelAndView modelAndView = new ModelAndView("write");
		modelAndView.addObject("post", postService.getPostById(postId));
		modelAndView.addObject("file", fileService.getFileByPostId(postId));
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		log.info("view login page");
		
		return "login";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		log.info("view signup page");
		
		return "signup";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView userModify() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		ModelAndView modelAndView = new ModelAndView("signup");
		modelAndView.addObject("user", userService.getUserByEmail(email));
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage() {
		log.info("welcome to admin page");
		
		return "admin";
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVO writePost(WritePostVO writePostVO) {
        log.info("{} board's new posting", writePostVO.getBoardId());
        int postId = postService.postPost(writePostVO);
        
        fileService.saveFile(postId, writePostVO.getFile());
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseVO deletePost(@RequestBody Map<Object, Object> body) {
		int postNumber = (Integer)body.get("postNumber");
		log.info("{} Post is deleted", postNumber);
		postService.deletePost(postNumber);

		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/post/{postId}/modify", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVO modifyPost(ModifyPostVO modifyPostVO) throws IllegalStateException, IOException {
		log.info("{} Post is modify", modifyPostVO.getId());
		postService.modifyPost(modifyPostVO);
		fileService.modifyFileByPost(modifyPostVO);
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVO writeComment(@RequestBody WriteCommentVO writeCommentVO) {
		log.info("{} Post's new comment", writeCommentVO.getPostId());
		commentService.writeComment(writeCommentVO);
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseVO deleteComment(@RequestBody Map<Object, Object> body) {
		int commentId = (Integer)body.get("commentId");
		log.info("{} Comment is deleted", commentId);
		commentService.deleteComment(commentId);
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/comment", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseVO modifyComment(@RequestBody ModifyCommentVO modifyCommentVO) {
		log.info("{} Comment is modify", modifyCommentVO.getId());
		commentService.modifyComment(modifyCommentVO);
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/file/{fileId}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable("fileId") String fileId, HttpServletResponse httpServletResponse) throws IOException {
		log.info("{} file is downloaded", fileId);
		FileVO fileVO = fileService.getFileByFileId(fileId);

		byte fileByte[] = FileUtils.readFileToByteArray(new File(fileVO.getLocation()));
		
		httpServletResponse.setContentType("application/octet-stream");
		httpServletResponse.setContentLength(fileByte.length);
		httpServletResponse.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileVO.getOriginalFileName(), "UTF-8")+"\";");
		httpServletResponse.setHeader("Content-Transfer-Encoding", "bynary");
		httpServletResponse.getOutputStream().write(fileByte);
		
		httpServletResponse.getOutputStream().flush();
		httpServletResponse.getOutputStream().close();
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVO createUser(@RequestBody CreateUserVO createUserVO) {
		log.info("new user created");
		
		try {
			userService.createUser(createUserVO);
			return SUCCESS_RESPONSE;
		} catch(ExistEmailException eee) {
			return EXIST_MAIL_RESPONSE;
		}
	}
	
	@RequestMapping(value = "/user/id", method = RequestMethod.GET)
	@ResponseBody
	public ResponseVO getUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		UserVO userVO = userService.getUserByEmail(email);
		int userId = userVO.getId();
	
		Map<Object, Object> body = HashMapUtil.createHashMap("id", userId);
		
		return SUCCESS_RESPONSE.setBody(body);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public ResponseVO modifyUser(@RequestBody ModifyUserVO modifyUserVO) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		userService.modifyUser(email, modifyUserVO);
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value= "/user", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseVO deleteUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		userService.deleteUser(email);
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/admin/table/{tableName}", method = RequestMethod.GET)
	@ResponseBody
	public <T> ResponseVO getTableData(@PathVariable("tableName") String tableName) {
		ArrayList<T> tableData = adminService.getDBData(tableName);
		
		return SUCCESS_RESPONSE.setBody(tableData);
	}
	
	@RequestMapping(value = "/admin/table/{tableName}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseVO deleteTableData(@PathVariable("tableName") String tableName,
										@RequestBody Map<?, ?> map) {
		log.info("ADMIN: data is deleted in {} ", tableName);
		adminService.deleteDBData(tableName, map);
		
		return SUCCESS_RESPONSE;
	}
	
	@RequestMapping(value = "/admin/user/level", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseVO modifyUserLevel(@RequestBody Map<Object, Object> map) {
		log.info("ADMIN: user {} level is modified");
		String userId = (String)map.get("userId");
		String level = (String)map.get("level");
		adminService.modifyUserLevel(userId, level);
		
		return SUCCESS_RESPONSE;
	}
	
}
