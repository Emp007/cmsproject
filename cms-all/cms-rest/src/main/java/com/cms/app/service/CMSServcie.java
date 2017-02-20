package com.cms.app.service;

;

public interface CMSServcie<T> {

	public T get(long id);

	public T save(T entity);

	public T update(long id, T entity);

	

}
