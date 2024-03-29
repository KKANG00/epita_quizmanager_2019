package com.epita.application.view;

import com.epita.application.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class C2_login {
	
	/**
	 * valid id to login
	 */
	private static final String VAILD_ID = "admin";
	/**
	 * valid password to login
	 */
	private static final String VAILD_PW = "password";
	
	/**
	 * text field to enter id
	 */
	@FXML
    private TextField loginid;
	/**
	 * text field to enter password
	 */
	@FXML
    private TextField loginpw;
	
	private Main main;
	
	/**
	 * if login information is correct, go to question overview (managing quiz)
	 */
	@FXML
	public void gotooverview() {
		if(loginid.getText().equals(VAILD_ID) && loginpw.getText().equals(VAILD_PW)) {
			
			main.managingQuiz();
		
		} else {
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Login Error");
	        alert.setContentText("Check your id and password");

	        alert.showAndWait();
		}
	}
	
    public void setMainApp(Main main) {
        this.main = main;
    }
    
    
    
    
}
