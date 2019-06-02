package com.epita.application.view;

import java.io.IOException;

import com.epita.application.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class C1_login {
	
	public static final String VAILD_ID = "admin";
	public static final String VAILD_PW = "2019password";
	
	@FXML
	private AnchorPane loginPane;
	@FXML
    private Label loginid;
    @FXML
    private Label loginpw;
    
	public Main main;
	public C1_login() {}
    

	@FXML
	private void loadnext(ActionEvent Event) throws IOException {
		AnchorPane Pane = FXMLLoader.load(getClass().getResource("V3_questionoverview.fxml"));
		loginPane.getChildren().setAll(Pane);
	}
	
	public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

}
