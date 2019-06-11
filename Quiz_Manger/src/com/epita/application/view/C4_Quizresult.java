package com.epita.application.view;

import com.epita.application.Main;
import com.epita.application.model.Question;
import com.epita.application.model.Result;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class C4_Quizresult {
	/**
	 * question list that made by topic user choose
	 */
	private ObservableList<Question> quiz = FXCollections.observableArrayList();
	/**
	 * topic string user choose
	 */
	private String quiztopic;
	/**
	 * size of quiz
	 */
	private int quizsize;
	
	/**
	 * label for showing result
	 */
	@FXML
	private Label resultLB;
	/**
	 * label for showing percentage 
	 */
	@FXML
	private Label percentageLB;
    /**
     * label for showing note
     */
	@FXML
    private Label noteLB;
	/**
	 * label for showing quiz topic
	 */
    @FXML
    private Label quiztopicLB;
    /**
     * text field to enter user name
     */
    @FXML
    private TextField usernameF;
	
	public Main main;
	private Stage dialogStage;
	/**
	 * constructor
	 */
	public C4_Quizresult() {}
	
	/**
	 * initialize page
	 */
	@FXML
	private void initialize() {
	    noteLB.setText("Enter your name and press button to save your result");
	    
	}
	
	/**
	 * pass quiz information from last page
	 * @param result number of questions user got
	 * @param size size of quiz
	 * @param topic topic string
	 * @param Quiz quiz (question list)
	 */
	public void getresult(int result, int size, String topic, ObservableList<Question> Quiz) {
		
		double re = result*100;
		double percentage = Math.round(re/size);
		
		resultLB.setText(String.valueOf(result)+"/"+String.valueOf(size));
		percentageLB.setText(String.valueOf(percentage)+"%");
	    noteLB.setText("Enter your name and press button to save your result");
	    quiztopicLB.setText("Quiz "+ topic);
	    
	    this.quiztopic = topic;
	    this.quizsize = size;
	    this.quiz = Quiz;
	}
    
	/**
	 * method to save quiz result to text
	 */
    @FXML
    private void saveresult() {    	
    	if (usernameF.getText() == null || usernameF.getText().length() == 0) {
    		Alert alert = new Alert(AlertType.ERROR);
    	    alert.initOwner(dialogStage);
    	    alert.setTitle("Error");
    	    alert.setHeaderText("Empty user name");
    	    alert.setContentText("Please enter user name");

    	    alert.showAndWait();
        }
    	    	
    	else{
    		Result user = new Result(resultLB.getText(), percentageLB.getText(), quiztopicLB.getText(), usernameF.getText());
    	
    		main.savetoDB(user);
    	}
    }

    /**
     * method to save quiz that user played
     */
    @FXML
    private void savequiz() {
    	main.savequiz(quiz, quiztopic, quizsize);
    }
    
    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }
}
