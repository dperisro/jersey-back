package com.mdp.jersey.back.books.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mdp.jersey.back.books.BaseJUnit;
import com.mdp.jersey.back.books.Book;

public class BookDaoTest extends BaseJUnit {

	@Before
	public void beforeBookDaoTest() {
		bookDao.deleteAllBooks();
	}

	@After
	public void afterBookDaoTest() {
	}

	@Test
	public void testBase() {
		int size = 10;
		insertBooks(size);
		assertThat(bookDao.getAllBooks(5, 0).size(), is(equalTo(5)));
		Book actual = bookDao.getAllBooks().get(0);
		assertThat(actual.getAuthor(), is(nullValue()));
	}

}
