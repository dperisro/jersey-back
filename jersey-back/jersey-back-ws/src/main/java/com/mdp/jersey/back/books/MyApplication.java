package com.mdp.jersey.back.books;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

public class MyApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public MyApplication() {
		register(RequestContextFilter.class);
		register(BookApi.class);
		register(JacksonFeature.class);
	}
}
