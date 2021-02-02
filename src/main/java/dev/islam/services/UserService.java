package dev.islam.services;

import org.apache.log4j.Logger;

import dev.islam.daos.UserDaos;
import dev.islam.models.User;

public class UserService {
	
	private static Logger log = Logger.getRootLogger();
	
	UserDaos u = new UserDaos();
	
	public User findUserByUsernameAndPassword(String username, String password) {
		for(User user: u.getAllUsers()) {
			if(user!=null) {
				if(user.getUserName()!=null && user.getUserName().equals(username)) {
					if(user.getPassword()!=null && user.getPassword().equals(password)) {
						log.info("User authenticated");
						return user;
					}
				}
			}
		}
		return null;
	}
	
	public User findUserById(int id) {
		for(User user: u.getAllUsers()) {
			if(user!=null && user.getUserId()==id) {
				return user;
			}
		}
		return null;
	}

}
