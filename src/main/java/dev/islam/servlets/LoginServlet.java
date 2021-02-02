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

public class LoginServlet extends HttpServlet {
	
	private static Logger log = Logger.getRootLogger();
	
	private UserService uService = new UserService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ObjectMapper om = new ObjectMapper();
		User credentials = om.readValue(request.getReader().readLine(), User.class);
		
		User user = uService.findUserByUsernameAndPassword(credentials.getUserName(), credentials.getPassword());
		
		log.info("Logging in..");
		if(user!=null) {
			//send back token
			String token = user.getUserId()+":"+user.getUserType()+":"+user.getUserName();
			try(PrintWriter pw = response.getWriter()){
				pw.write(token);
				log.info("Logged in");
			}
		} else {
			response.sendError(401);
		}
	
	}
	
}
