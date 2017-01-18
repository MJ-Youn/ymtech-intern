package com.dev2.intern.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class GenericDAO {

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ReloadableResourceBundleMessageSource querySource;
	
	public String getQuery(String name) {
		return querySource.getMessage(name, null, null);
	}
}
