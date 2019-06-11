package com.epita.application.model;

import java.util.List;

public class MCQChoice {
	
	/**
	 * choices of MCQChoice
	 */
	private List<String> choices;

	/**
	 * constructor 
	 * @param list construct list to be choices
	 */
	public MCQChoice(List<String> list) {
		this.choices = list;
	}
	
	/**
	 * method to convert mcq choice to list
	 * @return choices in list format
	 */
	public List<String> MCQtoList(){
		return choices;
	}
	
	/**
	 * method to convert list to string for xml file
	 * @return choices in string format
	 */
	public String choices_ListtoString() {
		if(!choices.isEmpty()) {
			String choices_=choices.get(0);
			choices_ += ",";
			for(int i=1;i<choices.size();i++) {
				choices_ += choices.get(i);
				if(!(i==choices.size()-1)) choices_ += ",";
			}
			return choices_;
		} else {
			return "";
		}
	}

}
