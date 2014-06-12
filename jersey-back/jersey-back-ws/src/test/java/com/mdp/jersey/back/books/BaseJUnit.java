package com.mdp.jersey.back.books;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mdp.jersey.back.books.dao.BookDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:module-context.xml")
@Ignore
public class BaseJUnit {

	protected static Logger logger = Logger.getLogger(BaseJUnit.class);

	protected final int id = 0;
	protected final String image = "https://assets-cdn.github.com/images/spinners/octocat-spinner-32.gif";
	protected final String title = "BookTitle";
	protected final String author = "everis";
	protected final double price = 5.99;
	protected final String link = "/api/v1/books/42";
	protected Book book;

	@Autowired
	protected BookDao bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		book = new Book(id);
		book.setImage(image);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPrice(price);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMinimal() throws Exception {
		Assert.assertTrue(true);
	}

	protected List<Book> insertBooks(int count) {
		List<Book> books = new ArrayList<Book>();
		for (int index = 1; index <= count; index++) {
			Book book = new Book(index);
			book.setTitle(title + "" + index);
			book.setAuthor(author);
			book.setImage(image);
			book.setPrice(price + index);
			book.setLink("/api/v1/books/" + index);
			bookDao.saveBook(book);
			books.add(book);
		}
		return books;
	}

}
