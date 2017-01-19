package com.dev2.intern.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dev2.intern.dao.impl.AdminDAO;
import com.dev2.intern.dao.impl.CommentDAO;
import com.dev2.intern.dao.impl.FileDAO;
import com.dev2.intern.dao.impl.PostDAO;
import com.dev2.intern.dao.impl.UserDAO;
import com.dev2.intern.service.IAdminService;
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
	public int deleteDBData(String tableName, Object object) {
		if (TABLE_POST.equals(tableName)) {
			int postId = ((PostVO)object).getId();
			return postDAO.deletePost(postId);
		} else if (TABLE_FILE.equals(tableName)) {
			int postId = ((FileVO)object).getPostId();
			return fileDAO.deleteFile(postId);
		} else if (TABLE_COMMENT.equals(tableName)) {
			int commentId = ((CommentVO)object).getId();
			return commentDAO.deleteComment(commentId);
		} else if (TABLE_USER.equals(tableName)) {
			String email = ((UserVO)object).getEmail();
			return userDAO.deleteUser(email);
		} else if (TABLE_TRASH_POST.equals(tableName)) {
			return adminDAO.deleteTrashPost((PostVO)object);
		} else if (TABLE_TRASH_FILE.equals(tableName)) {
			return adminDAO.deleteTrashFile((FileVO)object);
		} else if (TABLE_TRASH_COMMENT.equals(tableName)) {
			return adminDAO.deleteTrashComment((CommentVO)object);
		} else if (TABLE_TRASH_USER.equals(tableName)) {
			return adminDAO.deleteTrashUser((UserVO)object);
		}
		
		return 0;
	}

	@Override
	public int modifyUserLevel(String userId, String level) {
		return adminDAO.modifyUserLevel(userId, level);
	}
}
