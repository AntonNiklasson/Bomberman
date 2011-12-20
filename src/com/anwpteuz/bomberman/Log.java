package com.anwpteuz.bomberman;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows logging in console.
 * @author antonniklasson
 *
 */
public class Log {
	
	private static Log instance;
	private Logger logger;
	
	private Log() {
		logger = Logger.getLogger("BombermanLog");
		logger.setLevel(Level.ALL);
		logger.log(Level.INFO, "Log initialized.");
	}
	
	private static Log getInstance() {
		if(instance == null) instance = new Log();
		return instance;
	}
	
	public static Logger get() {
		return getInstance().logger;
	}
}
