package com.epita.application.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.epita.application.Main;
import com.epita.application.model.Result;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class C4_Quizresult {
	
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
	public C4_Quizresult() {}
	
	@FXML
	private void initialize() {
//		resultLB.setText("result");
//		percentageLB.setText("percentage%");
	    noteLB.setText("Enter your name and press button to save your result");;
//	    quiztopicLB.setText("topic");
	    
	}
	
	public void getresult(int result, int size, String topic) {
		
		double re = result*100;
		double percentage = Math.round(re/size);
		
		resultLB.setText(String.valueOf(result)+"/"+String.valueOf(size));
		percentageLB.setText(String.valueOf(percentage)+"%");
	    noteLB.setText("Enter your name and press button to save your result");
	    quiztopicLB.setText("Quiz "+ topic);
	}
	
    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }
    
    @FXML
    private void saveresult() {    	
    	    	
    	Result user = new Result(resultLB.getText(), percentageLB.getText(), quiztopicLB.getText(), usernameF.getText());
    	
    	main.savetoDB(user);
    }

}
