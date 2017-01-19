package com.dev2.intern.service.impl;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.impl.AdminDAO;
import com.dev2.intern.dao.impl.CommentDAO;
import com.dev2.intern.dao.impl.FileDAO;
import com.dev2.intern.dao.impl.PostDAO;
import com.dev2.intern.dao.impl.UserDAO;
import com.dev2.intern.service.IAdminService;
import com.dev2.intern.util.HashMapUtil;
import com.dev2.intern.vo.CommentVO;
import com.dev2.intern.vo.FileVO;
import com.dev2.intern.vo.PostVO;
import com.dev2.intern.vo.UserVO;

@Service(AdminService.BEAN_QUALIFIER)
public class AdminService implements IAdminService {

	public static final String BEAN_QUALIFIER = "com.dev2.intern.service.impl.AdminService";
	
	private static final String TABLE_POST = "post";
	private static final String TABLE_FILE = "file";
	private static final String TABLE_COMMENT = "comment";
	private static final String TABLE_USER = "user";
	private static final String TABLE_TRASH_POST = "trash_post";
	private static final String TABLE_TRASH_FILE = "trash_file";
	private static final String TABLE_TRASH_COMMENT = "trash_comment";
	private static final String TABLE_TRASH_USER = "trash_user";
	
	@Autowired
	@Qualifier(AdminDAO.BEAN_QUALIFIER)
	private AdminDAO adminDAO;
	
	@Autowired
	@Qualifier(PostDAO.BEAN_QUALIFIER)
	private PostDAO postDAO;
	
	@Autowired
	@Qualifier(CommentDAO.BEAN_QUALIFIER)
	private CommentDAO commentDAO;
	
	@Autowired
	@Qualifier(FileDAO.BEAN_QUALIFIER)
	private FileDAO fileDAO;
	
	@Autowired
	@Qualifier(UserDAO.BEAN_QUALIFIER)
	private UserDAO userDAO;
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> getDBData(String tableName) {
		if (TABLE_POST.equals(tableName)) {
			return (ArrayList<T>) adminDAO.getAllPost();
		} else if (TABLE_FILE.equals(tableName)) {
			return (ArrayList<T>) adminDAO.getAllFile();
		} else if (TABLE_COMMENT.equals(tableName)) {
			return (ArrayList<T>) adminDAO.getAllComment();
		} else if (TABLE_USER.equals(tableName)) {
			return (ArrayList<T>) adminDAO.getAllUser();
		} else if (TABLE_TRASH_POST.equals(tableName)) {
			return (ArrayList<T>) adminDAO.getAllTrashPost();
		} else if (TABLE_TRASH_FILE.equals(tableName)) {
			return (ArrayList<T>) adminDAO.getAllTrashFile();
		} else if (TABLE_TRASH_COMMENT.equals(tableName)) {
			return (ArrayList<T>) adminDAO.getAllTrashComment();
		} else if (TABLE_TRASH_USER.equals(tableName)) {
			return (ArrayList<T>) adminDAO.getAllTrashUser();
		}
		
		return null;
	}

	@Override
	public int deleteDBData(String tableName, Map<?, ?> map) {
		
		if (TABLE_POST.equals(tableName)) {
			PostVO postVO = (PostVO)HashMapUtil.mapConvertToVO(map, PostVO.class);
			return postDAO.deletePost(postVO.getId());
		} else if (TABLE_FILE.equals(tableName)) {
			FileVO fileVO = (FileVO)HashMapUtil.mapConvertToVO(map, FileVO.class);
			return fileDAO.deleteFile(fileVO.getPostId());
		} else if (TABLE_COMMENT.equals(tableName)) {
			CommentVO commentVO = (CommentVO)HashMapUtil.mapConvertToVO(map, CommentVO.class);
			return commentDAO.deleteComment(commentVO.getId());
		} else if (TABLE_USER.equals(tableName)) {
			UserVO userVO = (UserVO)HashMapUtil.mapConvertToVO(map, UserVO.class);
			return userDAO.deleteUser(userVO.getEmail());
		} else if (TABLE_TRASH_POST.equals(tableName)) {
			PostVO postVO = (PostVO)HashMapUtil.mapConvertToVO(map, PostVO.class);
			return adminDAO.deleteTrashPost(postVO);
		} else if (TABLE_TRASH_FILE.equals(tableName)) {
			FileVO fileVO = (FileVO)HashMapUtil.mapConvertToVO(map, FileVO.class);
			return adminDAO.deleteTrashFile(fileVO);
		} else if (TABLE_TRASH_COMMENT.equals(tableName)) {
			CommentVO commentVO = (CommentVO)HashMapUtil.mapConvertToVO(map, CommentVO.class);
			return adminDAO.deleteTrashComment(commentVO);
		} else if (TABLE_TRASH_USER.equals(tableName)) {
			UserVO userVO = (UserVO)HashMapUtil.mapConvertToVO(map, UserVO.class);
			return adminDAO.deleteTrashUser(userVO);
		}
		
		return 0;
	}

	@Override
	public int modifyUserLevel(String userId, String level) {
		return adminDAO.modifyUserLevel(userId, level);
	}
}
