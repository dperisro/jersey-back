package com.mdp.jersey.back.books;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mdp.jersey.back.books.dao.BookDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:module-context.xml")
public class BaseJUnit {

	protected static Logger logger = Logger.getLogger(BaseJUnit.class);

	@Autowired
	BookDao bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMinimal() throws Exception {
		Assert.assertTrue(true);
		bookDao.getAllBooks();
	}

}
