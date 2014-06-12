package com.mdp.jersey.back.books;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.glassfish.jersey.server.JSONP;
import org.springframework.beans.factory.annotation.Autowired;

import com.mdp.jersey.back.books.dao.BookDao;

@Path("v1/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookApi {

	@Autowired
	private BookDao bookDao;

	private static final String ITEMS_URL = "/api/v1/items";

	@GET
	@JSONP(queryParam = "callback")
	public String getAllBooks(@QueryParam("offset") int offset, @QueryParam("count") int count, @QueryParam("callback") String callback)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);
		List<Book> books = bookDao.getAllBooks(offset, count);
		for (Book book : books) {
			book.setLink(ITEMS_URL + "/" + book.getId());
		}
		return mapper.writeValueAsString(books);
	}

	@DELETE
	@JSONP(queryParam = "callback")
	public void deleteAllBooks() throws Exception {
		bookDao.deleteAllBooks();
	}

	@GET
	@Path("/{id}")
	@JSONP(queryParam = "callback")
	public String getBook(@PathParam("id") int id) throws Exception {
		Book book = bookDao.getBook(id);
		return new ObjectMapper().writeValueAsString(book);
	}

	@PUT
	@JSONP(queryParam = "callback")
	public void putUser(String bookJson) throws Exception {
		Book book = new ObjectMapper().readValue(bookJson, Book.class);
		bookDao.saveOrUpdateBook(book);
	}

	@DELETE
	@Path("/{id}")
	@JSONP(queryParam = "callback")
	public void deleteBook(@PathParam("id") int id) throws Exception {
		bookDao.deleteBook(id);
	}

}
