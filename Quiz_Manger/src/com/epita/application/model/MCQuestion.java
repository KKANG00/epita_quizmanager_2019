package com.epita.application.model;

public class MCQuestion extends Question {
	
	/**
	 * choices of MCQuestion
	 */
	private MCQChoice choices;
	
	/**
	 * constructor
	 */
	public MCQuestion() {
		this(null, null, null, null, null, null, null);
	}

	/**
	 * constructor
	 * @param question question contents
	 * @param qtopic1 topic of question
	 * @param qtopic2 second topic of question
	 * @param qnumber number of question
	 * @param qdifficulty difficulty of question
	 * @param qanswer answer of question
	 * @param choices choices of MCQuestion
	 */
	public MCQuestion(String question, String qtopic1, String qtopic2, String qnumber, String qdifficulty, String qanswer, MCQChoice choices) {
		super(question, qtopic1, qtopic2, qnumber, qdifficulty, qanswer);
		this.choices = choices;
		this.typeofquestion = "multi";
		// TODO Auto-generated constructor stub
	}

	/**
	 * get choices from MCQuestion class
	 */
	public MCQChoice getchoices() {
    	return choices;
    }
	
	/**
	 * set choices of MCQuestion class
	 */
	public void setchoices(MCQChoice choices) {
    	this.choices=choices;
    }
}
