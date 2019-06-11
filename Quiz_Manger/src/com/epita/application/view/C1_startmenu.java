package com.epita.application.view;

import com.epita.application.Main;

import javafx.fxml.FXML;

public class C1_startmenu {
	
	private Main main;
	
	/**
	 * open login page
	 */
	@FXML
	public void managingQuiz() {
		main.login();
	}
	
	/**
	 * open making page
	 */
	@FXML
	public void makingQuiz() {
		main.makingQuiz();
	}
	
    public void setMainApp(Main mainApp) {
        this.main = mainApp;

    }
	
}
