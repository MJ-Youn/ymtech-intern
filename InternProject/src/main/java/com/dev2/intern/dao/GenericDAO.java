package com.dev2.intern.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public abstract class GenericDAO {

	@Autowired
	private ReloadableResourceBundleMessageSource querySource;
	
	public String getQuery(String name) {
		return querySource.getMessage(name, null, null);
	}
}
