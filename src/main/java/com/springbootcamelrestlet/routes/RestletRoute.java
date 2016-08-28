package com.springbootcamelrestlet.routes;

import java.io.InputStream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.restlet.RestletConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.commons.io.IOUtils;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.springframework.stereotype.Component;

@Component
public class RestletRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		System.out.println("Starting Camel Restlet Route....");
			restConfiguration().component("restlet")
			//.port(8082)
			.bindingMode(RestBindingMode.auto)
			.skipBindingOnErrorCode(false);
			
			//getContext().setStreamCaching(true);
			getContext().setTracing(true);
			//getContext().setTypeConverterStatisticsEnabled(true);
			
			rest("/hello")
			.produces("application/json")
			.get()
			.to("direct:hello3");
			
			rest("/say/new")
			.produces("application/text")
			.get("/hello")
			.to("direct:hello");
			
			from("direct:hello")
			.process(new Processor(){

				@Override
				public void process(Exchange exchange) throws Exception {
					Request request = exchange.getIn().getHeader(RestletConstants.RESTLET_REQUEST, Request.class);
					
					Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
//					response.setEntity("<response>HelloWorld</response>", MediaType.TEXT_XML);
//					exchange.getOut().setBody(response);
					
					UserJAXB user = new UserJAXB();
							user.setName("user1");
					user.setNumber("1234");		
					exchange.getOut().setBody(user);
										
				}
				
			});
			
			from("direct:hello2")
			.process(exchange ->{
				exchange.getIn().setBody("hello-World");
			});
			
			from("direct:hello3")
			.process(new Processor(){

				@Override
				public void process(Exchange exchange) throws Exception {
					
					Employee emp = new Employee();
					emp.setEmpNo("1000");
					emp.setEmpName("TEST");
					emp.setEmpDept("TESTING");
					
					exchange.getOut().setBody(emp);
					
				}
				
			});
			
			rest("user")
			//.bindingMode(RestBindingMode.xml)
			.post("/xml/data")
			.type(UserJAXB.class)
			.to("direct:userxml");
			
			from("direct:userxml")
			.process(new Processor(){

				@Override
				public void process(Exchange exchange) throws Exception {
					Request request = exchange.getIn().getHeader(RestletConstants.RESTLET_REQUEST, Request.class);
					String s = request.getEntityAsText();
					System.out.println(" Request : "+ s);
					
					UserJAXB user = exchange.getIn().getBody(UserJAXB.class);
					System.out.println(">>>> User: "+ user.getName());
					System.out.println(">>>> User: "+ user.getNumber());
					
					user.setNumber("99999");
					
					Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
					
					//response.setEntity(entity);setEntity(user, MediaType.TEXT_XML);
					exchange.getOut().setBody(user);
					
				}
				
			});
			
			rest("user")
			.consumes("application/octet-stream")
			.post("doc")
			.type(InputStream.class)
			.to("direct:file");
			
			from("direct:file")
			.process(new Processor(){

				@Override
				public void process(Exchange exchange) throws Exception {
					//Map<String, DataHandler> attach = exchange.getIn().getAttachments();
					//attach.forEach( (k,v) -> System.out.println("Key: " + k + ": Value: " + v));
					
					 InputStream is = (InputStream) exchange.getIn().getBody();
					 
					 if(null == is){
						 System.out.println("NULL INstream");
						 
						 Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
							response.setEntity("<response>unable to get file.</response>", MediaType.TEXT_XML);
							exchange.getOut().setBody(response);
							
						 
					 }else{
						 byte[] b = IOUtils.toByteArray(is);
						 System.out.println("Total bytes received: "+ b.length);
						 
						 Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
							response.setEntity("<response>successfully received file.</response>", MediaType.TEXT_XML);
							exchange.getOut().setBody(response);
							
					 }
				}
				
			});

	}

}
