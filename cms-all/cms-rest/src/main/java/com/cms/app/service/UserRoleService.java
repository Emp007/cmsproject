package com.cms.app.service;

import java.util.List;

import com.cms.model.UserRole;

public interface UserRoleService extends CMSServcie<UserRole> {

	public List<UserRole> getAll();
	
	public long count();
	
	public UserRole findByRoleId(long id);
	
	public List<UserRole> findByUserId(long id);
}
