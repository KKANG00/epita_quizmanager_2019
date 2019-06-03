package com.epita.application.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Question {
	
	private final StringProperty question;
	private final StringProperty qtopic1;
	private final StringProperty qtopic2;
	private final StringProperty qnumber;
	private final StringProperty qdifficulty;
	private final StringProperty qanswer;
	public String typeofquestion;
//	private final MCQChoice choices;	
	
	public Question() {
		this(null, null, null, null, null, null);
	}

//    public Question(String question, String qtopic1, String qtopic2, String qnumber, String qdifficulty, MCQChoice choices) {
//        this.question = new SimpleStringProperty(question);
//        this.qtopic1 = new SimpleStringProperty(qtopic1);
//        this.qtopic2 = new SimpleStringProperty(qtopic2);
//        this.qnumber = new SimpleStringProperty(qnumber);
//        this.qdifficulty = new SimpleStringProperty(qdifficulty);
//        this.choices = choices;
//    }
	
    @Override
	public String toString() {
		return "Question [question=" + question + ", qtopic1=" + qtopic1 + ", qtopic2=" + qtopic2 + ", qnumber="
				+ qnumber + ", qdifficulty=" + qdifficulty + ", qanswer=" + qanswer + ", typeofquestion="
				+ typeofquestion + "]";
	}

	public Question(String question, String qtopic1, String qtopic2, String qnumber, String qdifficulty, String qanswer) {
        this.question = new SimpleStringProperty(question);
        this.qtopic1 = new SimpleStringProperty(qtopic1);
        this.qtopic2 = new SimpleStringProperty(qtopic2);
        this.qnumber = new SimpleStringProperty(qnumber);
        this.qdifficulty = new SimpleStringProperty(qdifficulty);
        this.qanswer = new SimpleStringProperty(qanswer);
        this.typeofquestion = "Open";
    }
    
    public StringProperty getqnumberP() {
    	return qnumber;
    }
    
    public StringProperty getqtopic1P() {
    	return qtopic1;
    }
    
    public String getquestion() {
    	return question.get();
    }
    
    public String getqnumber() {
    	return qnumber.get();
    }
    
    public String getqtopic1() {
    	return qtopic1.get();
    }
    
    public String getqtopic2() {
    	return qtopic2.get();
    }
    
    public String getqdifficulty() {
    	return qdifficulty.get();
    }
    
    public String getqanswer() {
    	return qanswer.get();
    }
    
    public MCQChoice getchoices() {
    	MCQChoice empty = new MCQChoice(null);
    	return empty;
    }
    
    public void setquestion(String question) {
    	this.question.set(question);
    }
    
    public void setqnumber(String qnumber) {
    	this.question.set(qnumber);
    }
    
    public void setqtopic1(String qtopic1) {
    	this.question.set(qtopic1);
    }
    
    public void setqtopic2(String qtopic2) {
    	this.question.set(qtopic2);
    }
    
    public void setqdifficulty(String qdifficulty) {
    	this.question.set(qdifficulty);
    }
    
    public void setqanswer(String qanswer) {
    	this.question.set(qanswer);
    }
    
    public void setchoices(MCQChoice choices) {
    	MCQChoice empty = new MCQChoice(null);
    }
}
