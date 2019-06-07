package com.epita.application.view;

import com.epita.application.Main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class C1_startmenu {
	
	private Main main;
	
	@FXML
	public void managingQuiz() {
		main.login();
	}
	
	@FXML
	public void makingQuiz() {
		main.makingQuiz();
	}
	
    public void setMainApp(Main mainApp) {
        this.main = mainApp;

    }
	
}
