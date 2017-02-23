package com.cms.app.service;

import java.util.List;
import com.cms.model.Role;

public interface RoleService  extends CMSServcie<Role>{
	
	public List<Role> getAll();
	
	public long count();

}
