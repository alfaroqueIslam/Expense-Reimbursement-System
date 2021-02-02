package dev.islam.services;

import org.apache.log4j.Logger;

import dev.islam.daos.RequestDaos;
import dev.islam.models.Request;

public class ModifyService {
	
	private static Logger log = Logger.getRootLogger();
	
	public void modify(String status, String rId) {
		RequestDaos r = new RequestDaos();
		r.modify(status, rId);
		log.info("Modifying..");
		
	}

}
