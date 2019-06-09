package com.epita.application.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Result {
	
	public static final String LOGGING_FORMAT = "yyyy/MM/dd HH:mm:ss";
	
	private String result;
	private String score;
	private String topic;
	
	private String name;
	private String date;
	
	
	
	public Result(String result, String score, String topic, String name) {
		this.result = result;
		this.score = score;
		this.topic = topic;
		this.name = name;
		this.date = setdate();
	}

	public String setdate() {
		Date date = new Date();
		StringBuilder log = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat(LOGGING_FORMAT);
		log.append(sdf.format(date));
		
//		log.append(" \n");
		
		return log.toString();
	}

	@Override
	public String toString() {
		return "Result [result=" + result + ", score=" + score + ", topic=" + topic + ", name=" + name + ", date="
				+ date + "]";
	}	
}
