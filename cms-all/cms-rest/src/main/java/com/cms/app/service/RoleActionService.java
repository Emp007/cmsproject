package com.cms.app.service;

import java.util.List;


import com.cms.model.RoleAction;

public interface RoleActionService extends CMSServcie<RoleAction> {
	
	public List<RoleAction> getAll();
	public long count();

}
