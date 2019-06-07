package com.epita.application.view;

import java.io.File;

import com.epita.application.Main;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class C1_rootlayout {
		
		private Stage primaryStage;
	    private Main main;
	
	    public void setMainApp(Main main) {
	        this.main = main;
	    }
	    
	    @FXML
	    private void goback() {
	    	main.startpage();
	    }
	    
	    @FXML
	    private void Openmenu() {
	    	main.resetlist();
	        FileChooser fileChooser = new FileChooser();
	
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
	                "XML files (*.xml)", "*.xml");
	        fileChooser.getExtensionFilters().add(extFilter);
	
	        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
	
	        if (file != null) {
	            main.loadfromXML(file);
	        }
	    }
	
	    @FXML
	    private void Savemenu() {
	        File questionDB = main.getDBfilePath();
	        if (questionDB != null) {
	            main.savetoXML(questionDB);
	        } else {
	        	SaveAsmenu();
	        }
	    }
	
	    @FXML
	    private void SaveAsmenu() {
	        FileChooser fileChooser = new FileChooser();
	
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
	                "XML files (*.xml)", "*.xml");
	        fileChooser.getExtensionFilters().add(extFilter);
	
	        File file = fileChooser.showSaveDialog(main.getPrimaryStage());
	
	        if (file != null) {
	            if (!file.getPath().endsWith(".xml")) {
	                file = new File(file.getPath() + ".xml");
	            }
	            main.savetoXML(file);
	        }
	    }
	
	    @FXML
	    private void Exitmenu() {
	        System.exit(0);
	    }
}