package com.mdp.jersey.back.books.dao;

import java.util.List;

import com.mdp.jersey.back.books.Book;

public interface BookDao {
	
	public void deleteAllBooks();

	public List<Book> getAllBooks();

	public List<Book> getAllBooks(int firstResult, int maxResult);

	public Book getBook(int id);

	public void saveOrUpdateBook(Book book);

	public void saveBook(Book book);

	public Book deleteBook(int id);

}