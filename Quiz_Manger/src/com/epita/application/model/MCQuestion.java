package com.epita.application.model;


public class MCQuestion extends Question {
	
	private MCQChoice choices;
	
	public MCQuestion() {
		this(null, null, null, null, null, null, null);
	}

	public MCQuestion(String question, String qtopic1, String qtopic2, String qnumber, String qdifficulty, String qanswer, MCQChoice choices) {
		super(question, qtopic1, qtopic2, qnumber, qdifficulty, qanswer);
		this.choices = choices;
		this.typeofquestion = "multi";
		// TODO Auto-generated constructor stub
	}

	public MCQChoice getchoices() {
    	return choices;
    }
	
	public void setchoices(MCQChoice choices) {
		System.out.println("setchoice from mcq");
    	this.choices=choices;
    }
}
