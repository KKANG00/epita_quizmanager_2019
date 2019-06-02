package com.epita.application.model;
public class MCQuestion extends Question {
	
	private final MCQChoice choices;

	public MCQuestion(String question, String qtopic1, String qtopic2, String qnumber, String qdifficulty, MCQChoice choices) {
		super(question, qtopic1, qtopic2, qnumber, qdifficulty);
		this.choices = choices;
		this.typeofquestion = "multi";
		// TODO Auto-generated constructor stub
	}

	public MCQChoice getchoices() {
    	return choices;
    }
}
