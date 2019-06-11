package com.epita.application.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Result {
	
	/**
	 * date format
	 */
	public static final String LOGGING_FORMAT = "yyyy/MM/dd HH:mm:ss";
	
	/**
	 * number of questions user got
	 */
	private String result;
	/**
	 * percentage of result
	 */
	private String score;
	/**
	 * topic of quiz
	 */
	private String topic;
	
	/**
	 * name of user
	 */
	private String name;
	/**
	 * date of result
	 */
	private String date;
	
	/**
	 * constructor
	 * @param result number of questions user got
	 * @param score percentage of result
	 * @param topic topic of quiz
	 * @param name name of user
	 */
	public Result(String result, String score, String topic, String name) {
		this.result = result;
		this.score = score;
		this.topic = topic;
		this.name = name;
		this.date = setdate();
	}

	/**
	 * set date and time at result made
	 * @return date and time to string
	 */
	public String setdate() {
		Date date = new Date();
		StringBuilder log = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat(LOGGING_FORMAT);
		log.append(sdf.format(date));
		
		return log.toString();
	}

	/**
	 * string to save result to text file
	 */
	@Override
	public String toString() {
		return "Result [result=" + result + ", score=" + score + ", topic=" + topic + ", name=" + name + ", date="
				+ date + "]";
	}	
}
