package dev.islam.services;

import org.apache.log4j.Logger;

import dev.islam.daos.RequestDaos;
import dev.islam.models.Request;

public class RequestService {
	
	private static Logger log = Logger.getRootLogger();
	
	public Request addNewRequest(Request request) {
		RequestDaos r = new RequestDaos();
		log.info("Adding new request..");
		return r.newRequest(request.getEmail(), request.getAmount(), request.getReason(), request.getUserId());
		
	}

}
