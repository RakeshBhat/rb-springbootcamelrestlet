package com.springbootcamelrestlet;

import java.util.HashMap;

import org.apache.camel.component.restlet.RestletComponent;
import org.restlet.Component;
//import org.apache.camel.component.restlet.RestletComponent;
import org.restlet.ext.servlet.ServerServlet;
import org.restlet.ext.spring.SpringServerServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CamelrestletApplication {  

/*	extends SpringBootServletInitializer {
	private static final String CAMEL_URL_MAPPING = "/*";
	private static final String CAMEL_SERVLET_NAME = "SpringServerServlet";
*/
	public static void main(String[] args) {
		SpringApplication.run(CamelrestletApplication.class, args);
	}

/*	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CamelrestletApplication.class);
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean registration =
				new ServletRegistrationBean(new org.restlet.ext.spring.SpringServerServlet(), CAMEL_URL_MAPPING);
		//registration.addInitParameter("org.restlet.application", CamelrestletApplication.class.getName());
		
		java.util.Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("org.restlet.component", "restletComponent");
		
		registration.setInitParameters(initParameters);
		
		
		//registration.setName(CAMEL_SERVLET_NAME);
		//registration.addInitParameter("org.restlet.component", "org.restlet.Component");
		return registration;
	}
	
	@Bean
	public Component restletComponent(){
		return new Component();
		
	}
	
	@Bean RestletComponent RestletComponentService(){
		return new RestletComponent(restletComponent());
	}
	
*/	

/*	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

				container.addErrorPages(error401Page, error404Page, error500Page);
			}
		};
	}*/
	
}
