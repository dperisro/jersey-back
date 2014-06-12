package com.mdp.jersey.back.books.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.mdp.jersey.back.books.dao.BaseDao;

@Transactional
public class BaseDaoImpl implements Serializable, BaseDao {

	private static final long serialVersionUID = 1L;
	protected static Logger log = Logger.getLogger(BaseDaoImpl.class);

	protected Class<?> clazz;

	private SessionFactory sessionFactory;

	protected BaseDaoImpl(SessionFactory sessionFactory, Class<?> clazz) {
		setSessionFactory(sessionFactory);
		this.clazz = clazz;
	}

	protected void deleteAll() throws Exception {
		String sql = "delete from " + clazz.getName();
		Query query = getSessionFactory().getCurrentSession().createQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<?> findAll() {
		return getSessionFactory().getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	@Override
	public void save(Object transientInstance) throws Exception {
		try {
			getSessionFactory().getCurrentSession().save(transientInstance);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception("Error al guardar el elemento.", e);
		}
	}

	@Override
	public void saveOrUpdate(Object transientInstance) throws Exception {
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(transientInstance);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception("Error al guardar el elemento.", e);
		}
	}

	@Override
	public void delete(Object transientInstance) {
		try {
			getSessionFactory().getCurrentSession().delete(transientInstance);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveList(List<?> objects) throws Exception {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Object object : objects) {
				session.save(object);
			}
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
