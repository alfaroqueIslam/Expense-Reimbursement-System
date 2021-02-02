package dev.islam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.islam.daos.RequestDaos;
import dev.islam.models.Request;


public class ErsServlet extends HttpServlet {
	
	private static Logger log = Logger.getRootLogger();

	private static final long serialVersionUID = 1L;
	private RequestDaos requestService = new RequestDaos();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		log.info("GET request to Ers Servlet");
		ArrayList<Request> requests = requestService.getAllRequests();
		System.out.println(requests);
		ObjectMapper om = new ObjectMapper();
		String itemsJson = om.writeValueAsString(requests);
		System.out.println(itemsJson);
		PrintWriter pw = response.getWriter();
		pw.write(itemsJson);
		pw.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
	}
	
}
