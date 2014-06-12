package com.mdp.jersey.back.books.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mdp.jersey.back.books.Book;
import com.mdp.jersey.back.books.dao.BookDao;

@Repository("bookDao")
public class BookDaoImpl extends BaseDaoImpl implements BookDao {

	private static final long serialVersionUID = 1L;

	@Autowired
	protected BookDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Book.class);
	}

	@Override
	public void deleteAllBooks() {
		Session session = getSessionFactory().openSession();
		session.createQuery("delete from Book").executeUpdate();
		session.close();
	}

	@Override
	public List<Book> getAllBooks() {
		return getAllBooks(0, 0);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Book> getAllBooks(int firstResult, int maxResult) {
		List<Book> books = new ArrayList<Book>();
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery("select id, title from Book");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List allUsers = query.list();
		for (Iterator it = allUsers.iterator(); it.hasNext();) {
			Object[] bookObject = (Object[]) it.next();
			Book book = new Book((Integer) bookObject[0]);
			book.setTitle((String) bookObject[1]);
			books.add(book);
		}
		session.close();
		return books;
	}

	@Override
	public Book getBook(int id) {
		Session session = getSessionFactory().openSession();
		Book book = (Book) session.get(Book.class, id);
		session.close();
		return book;
	}

	@Override
	public void saveOrUpdateBook(Book book) {
		Session session = getSessionFactory().openSession();
		session.saveOrUpdate(book);
		session.flush();
		session.close();
	}

	@Override
	public void saveBook(Book book) {
		Session session = getSessionFactory().openSession();
		session.save(book);
		session.flush();
		session.close();
	}

	@Override
	public Book deleteBook(int id) {
		Session session = getSessionFactory().openSession();
		Book book = getBook(id);
		if (book != null) {
			session.delete(book);
			session.flush();
		}
		session.close();
		return book;
	}

}
