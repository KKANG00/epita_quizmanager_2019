package com.epita.application.view;

import com.epita.application.Main;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class C1_startmenulayout {
	
	private Main main;
	
//	@FXML
//	public void questionoverviewpage() {
//		main.initRootLayout();
//		main.showQuestionOverview();
//	}
	
	@FXML
	public void gotologin() {
		//main.initRootLayout();
		main.Login();
	}
	
    public void setMainApp(Main mainApp) {
        this.main = mainApp;

    }
	
}
