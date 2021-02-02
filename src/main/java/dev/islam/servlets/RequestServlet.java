package dev.islam.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.islam.models.Request;
import dev.islam.services.RequestService;

public class RequestServlet extends HttpServlet {
	
	private static Logger log = Logger.getRootLogger();
	
	private static final long serialVersionUID = 1L;
	private RequestService rService = new RequestService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		log.info("POST to /create");
		// order + order items will be in request body, so we need to obtain the request body from the HttpServletRequest
		BufferedReader bw = request.getReader();
		String json = "";
		String line = bw.readLine();
		while(line!=null) {
			json = json + line;
			line = bw.readLine();
		}
		System.out.println(json);

		// use the ObjectMapper to convert string -> java object 
		ObjectMapper om = new ObjectMapper();
		Request r = om.readValue(json, Request.class);
//		System.out.println(order);
		
		// send java object to service which will pass that object to the DAO and persist to our DB 
		Request createdRequest = rService.addNewRequest(r);
		
		// configure HttpServletResponse with appropriate status (201 - created), possibly 400/500 depending on what kind of issue
		if(createdRequest!=null && createdRequest.getRequestId()!=0) {
			response.setStatus(201);
		} else {
			response.sendError(400);
		}
	}

}
