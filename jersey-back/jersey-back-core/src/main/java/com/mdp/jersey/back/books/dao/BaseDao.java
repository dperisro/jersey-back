package com.mdp.jersey.back.books.dao;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

public interface BaseDao {

	public List<?> findAll();

	public void save(Object transientInstance) throws Exception;

	public void saveOrUpdate(Object transientInstance) throws Exception;

	public void delete(Object transientInstance);

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public abstract void saveList(List<?> objects) throws Exception;

}