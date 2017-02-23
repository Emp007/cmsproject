package com.cms.app.service;

import java.util.List;

import com.cms.model.Action;

public interface ActionService extends CMSServcie<Action>{
	
	public List<Action> getAll();
	
	public long count();
}
