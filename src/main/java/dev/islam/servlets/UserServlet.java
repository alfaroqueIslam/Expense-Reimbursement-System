package dev.islam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.islam.models.User;
import dev.islam.services.UserService;

public class UserServlet extends HttpServlet{
	
	private static Logger log = Logger.getRootLogger();
	
	private UserService uService = new UserService();
	private ObjectMapper oMapper = new ObjectMapper();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uri = request.getRequestURI();
		
		String idStr = uri.split("/")[3];
		log.info("GET request for User with id: " + idStr);
		
		if(idStr!=null && idStr.matches("^\\d+$")) {
			int id = Integer.parseInt(idStr);	
			User user = uService.findUserById(id);
			if(user!=null) {
				try(PrintWriter pw = response.getWriter()){
					pw.write(oMapper.writeValueAsString(user));
				}
			} else {
				response.sendError(404);
			}
		} else {
			response.sendError(400);
		}
	
	}

}
