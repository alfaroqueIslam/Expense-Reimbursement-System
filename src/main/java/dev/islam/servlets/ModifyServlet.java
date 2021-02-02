package dev.islam.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.islam.daos.RequestDaos;
import dev.islam.services.ModifyService;

public class ModifyServlet extends HttpServlet {
	
	private static Logger log = Logger.getRootLogger();
	
	private static final long serialVersionUID = 1L;
	private ModifyService mService = new ModifyService();
	private RequestDaos rd = new RequestDaos();
	
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
		log.info("Modifying..");
		
		ObjectMapper om = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, String> map = om.readValue(json, Map.class);
		System.out.println(map.values());
		mService.modify(map.values().toArray()[0].toString(), map.values().toArray()[1].toString());
		
		if(rd.checkRequest(map.values().toArray()[0].toString(), map.values().toArray()[1].toString()) == true) {
			response.setStatus(201);
			log.info("Modified");
		} else {
			response.sendError(400);
		}
	}

}
