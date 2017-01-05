use intern;
show tables;

CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT,			# index
    email VARCHAR(255) NOT NULL,			# login identification
    name VARCHAR(20) NOT NULL,				# user name
    password VARCHAR(41) NOT NULL,			# user password ( SHA-1 length = 41 )
    PRIMARY KEY(id)
);

CREATE TABLE board(
	id INT NOT NULL AUTO_INCREMENT,			# index
    name VARCHAR(20) NOT NULL,				# board name
    PRIMARY KEY(id)
);

CREATE TABLE post(
	id INT NOT NULL AUTO_INCREMENT,																# index
    user_id INT NOT NULL,																		# user id who wrote this post
    post_number INT NOT NULL DEFAULT 1,															# post number on the board
    board_id INT NOT NULL,																		# board id with this post
    title VARCHAR(50) NOT NULL,																	# post title
    contents TEXT NOT NULL,																		# post contents
    hit_count INT DEFAULT 0,																	# post hit count
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,									# post create date
    modify_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,		# post modify date
    PRIMARY KEY(id, post_number, board_id),
    FOREIGN KEY(user_id) REFERENCES user(id),
    FOREIGN KEY(board_id) REFERENCES board(id)
);

CREATE TABLE comment(
	id INT NOT NULL AUTO_INCREMENT,																# index
    parent_comment_id INT DEFAULT 0,															# id of the parent comment
    post_id INT NOT NULL,																		# post id with this comment
    user_id INT NOT NULL,																		# user id who wrote this comment
    contents TEXT NOT NULL,																		# comment contents
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,									# comment create date
    modify_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,		# comment modify date
    PRIMARY KEY(id),
    FOREIGN KEY(post_id) REFERENCES post(id),
    FOREIGN KEY(user_id) REFERENCES user(id)
);

CREATE TABLE file(
	id INT NOT NULL AUTO_INCREMENT,					# index
    post_id INT NOT NULL,							# post id with this file
    location VARCHAR(500) NOT NULL,					# location where the file is stored
    original_file_name VARCHAR(255) NOT NULL,		# original file name ( cuz, do not run the file )
    size INT NOT NULL DEFAULT 0,					# file size
    type VARCHAR(10) NOT NULL DEFAULT 'txt',		# file type ( cuz, different ui for each type )
    PRIMARY KEY(id),
    FOREIGN KEY(post_id) REFERENCES post(id)
);

drop table file;
drop table comment;
drop table post;
drop table board;
drop table user;