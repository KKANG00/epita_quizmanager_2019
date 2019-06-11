package com.epita.application.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class C4_Quizresult {
	
	private ObservableList<Question> quiz = FXCollections.observableArrayList();
	private String quiztopic;
	private int quizsize;
	
	@FXML
	private Label resultLB;
	@FXML
	private Label percentageLB;
    @FXML
    private Label noteLB;
    @FXML
    private Label quiztopicLB;
    
    @FXML
    private TextField usernameF;
	
	public Main main;
	private Stage dialogStage;
	public C4_Quizresult() {}
	
	@FXML
	private void initialize() {
//		resultLB.setText("result");
//		percentageLB.setText("percentage%");
	    noteLB.setText("Enter your name and press button to save your result");;
//	    quiztopicLB.setText("topic");
	    
	}
	
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
	
    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }
    
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

    @FXML
    private void savequiz() {
    	main.savequiz(quiz, quiztopic, quizsize);
    }
}
