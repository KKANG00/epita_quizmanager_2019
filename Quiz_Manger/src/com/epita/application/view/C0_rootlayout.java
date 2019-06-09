package com.epita.application.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.epita.application.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class C0_rootlayout {
	
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
	    	main.resetquestionList();
	    	
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
	    
	    @FXML
	    private void help() throws IOException {
	    	File file = new File("readme.pdf");
	    	
	    	if(file.exists()) Desktop.getDesktop().open(file);
	    	else {
	    		Alert alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Error");
    		    alert.setHeaderText("No file exist");
    		    alert.setContentText("readme.pdf not exist");
    	
    		    alert.showAndWait();
    		    }
	    	}
	    
	    @FXML
	    private void checkresult() throws IOException {
	    	File file = new File("result.txt");
	    	
	    	if(file.exists()) Desktop.getDesktop().open(file);
	    	else {
	    		Alert alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Error");
    		    alert.setHeaderText("No file exist");
    		    alert.setContentText("result.txt not exist");
    	
    		    alert.showAndWait();
	    	}
	    }
}