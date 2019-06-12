package com.epita.application.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Question {
	
	/**
	 * question contents property
	 */
	private final StringProperty question;
	/**
	 * first topic property of question 
	 */
	private final StringProperty qtopic1;
	/**
	 * second topic property of question
	 */
	private final StringProperty qtopic2;
	/**
	 * number property of question
	 */
	private final StringProperty qnumber;
	/**
	 * difficulty property of question
	 */
	private final StringProperty qdifficulty;
	/**
	 * answer property of question
	 */
	private final StringProperty qanswer;
	/**
	 * type of question (multi or open)
	 */
	public String typeofquestion;
	
	/**
	 * constructor
	 */
	public Question() {
		this(null, null, null, null, null, null);
	}
	
	/**
	 * question class to string to save quiz
	 */
	public String qtoString() {
		return "Quesiton(" +qnumber.get()+") ["+question.get()+", first topic: "+qtopic1.get()+", second topic: "+qtopic2.get()+", difficulty: "+
				qdifficulty.get()+", answer: "+qanswer.get()+", type of question: "+typeofquestion;
	}

    /**
     * constructor
     * @param question question contents
     * @param qtopic1 first topic of question
     * @param qtopic2 second topic of question
     * @param qnumber number of question
     * @param qdifficulty difficulty of question
     * @param qanswer answer of question
     */
	public Question(String question, String qtopic1, String qtopic2, String qnumber, String qdifficulty, String qanswer) {
        this.question = new SimpleStringProperty(question);
        this.qtopic1 = new SimpleStringProperty(qtopic1);
        this.qtopic2 = new SimpleStringProperty(qtopic2);
        this.qnumber = new SimpleStringProperty(qnumber);
        this.qdifficulty = new SimpleStringProperty(qdifficulty);
        this.qanswer = new SimpleStringProperty(qanswer);
        this.typeofquestion = "";
    }
    
	/**
	 * get number property of question
	 * @return number property of question
	 */
    public StringProperty getqnumberP() {
    	return qnumber;
    }
    
    /**
     * get first topic property of question
     * @return first topic property of question
     */
    public StringProperty getqtopic1P() {
    	return qtopic1;
    }
    
    /**
     * get question contents
     * @return question contents
     */
    public String getquestion() {
    	return question.get();
    }
    
    /**
     * get number of question
     * @return number of question
     */
    public String getqnumber() {
    	return qnumber.get();
    }
    
    /**
     * get first topic of question
     * @return first topic of question
     */
    public String getqtopic1() {
    	return qtopic1.get();
    }
    
    /**
     * get second topic of question
     * @return second topic of question
     */
    public String getqtopic2() {
    	return qtopic2.get();
    }
    
    /**
     * get difficulty of question
     * @return difficulty of question
     */
    public String getqdifficulty() {
    	return qdifficulty.get();
    }
    
    /**
     * get answer of question
     * @return answer of question
     */
    public String getqanswer() {
    	return qanswer.get();
    }
    
    /**
     * get choices of question
     * @return choices of question
     */
    public MCQChoice getchoices() {
    	MCQChoice empty = new MCQChoice(null);
    	return empty;
    }
    
    /**
     * set question contents
     * @param question question contents
     */
    public void setquestion(String question) {
    	this.question.set(question);
    }
    
    /**
     * set number of question
     * @param qnumber number of question
     */
    public void setqnumber(String qnumber) {
    	this.qnumber.set(qnumber);
    }
    
    /**
     * set first topic of question
     * @param qtopic1 first topic of question
     */
    public void setqtopic1(String qtopic1) {
    	this.qtopic1.set(qtopic1);
    }
    
    /**
     * set second topic of question
     * @param qtopic2 second topic of question
     */
    public void setqtopic2(String qtopic2) {
    	this.qtopic2.set(qtopic2);
    }
    
    /**
     * set difficulty of question
     * @param qdifficulty difficulty of question
     */
    public void setqdifficulty(String qdifficulty) {
    	this.qdifficulty.set(qdifficulty);
    }
    
    /**
     * set answer of question
     * @param qanswer answer of question
     */
    public void setqanswer(String qanswer) {
    	this.qanswer.set(qanswer);
    }
    
    /**
     * set type of question
     * @param type type of question
     */
    public void settype(String type) {
    	this.typeofquestion = type;
    }
    
    /**
     * set choices of question
     * @param choices choices of question
     */
    public void setchoices(MCQChoice choices) {
    	MCQChoice empty = new MCQChoice(null);
    }
}
