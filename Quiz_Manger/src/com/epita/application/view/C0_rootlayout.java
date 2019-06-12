package com.epita.application.view;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.epita.application.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class C0_rootlayout {
	
	    private Main main;
	    private static final String XML_file = "questionDB.xml";
	    
	    /**
	     * restart button in program menu
	     */
	    @FXML
	    private void goback() {
	    	main.startpage();
	    }
	    
	    /**
	     * open button in file menu
	     */
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
	
	    /**
	     * save button in file menu
	     */
	    @FXML
	    private void Savemenu() {
	        File questionDB = new File(XML_file);
	        if (questionDB.exists()) {
	            main.savetoXML(questionDB);
	        } else {
	        	try {
	    	    	DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	                Document document = documentBuilder.newDocument();
	                
	                main.transformXmlDocument(document);
	                
	                Element newrootE = document.createElement("Questions");
	    	    	document.appendChild(newrootE);
	    	    	
	    	    	main.transformXmlDocument(document);
	    	    	
	    	    	} catch(Exception e) {}    	
	    			
	    	    	File file = new File(XML_file);

	    	    	if(file.exists()) main.savetoXML(file);
	        }
	    }
	
	    /**
	     * exit button in program menu
	     */
	    @FXML
	    private void Exitmenu() {
	        System.exit(0);
	    }
	    
	    /**
	     * help button in about menu
	     * @throws IOException file exception 
	     */
	    @FXML
	    private void help() throws IOException {
	    	File file = new File("documents/User guide.pdf");
	    	
	    	if(file.exists()) Desktop.getDesktop().open(file);
	    	else {
	    		Alert alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Error");
    		    alert.setHeaderText("No file exist");
    		    alert.setContentText("User guide.pdf not exist");
    	
    		    alert.showAndWait();
    		    }
	    	}
	    
	    /**
	     * result button in about menu
	     * @throws IOException file exception
	     */
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
	    
	    public void setMainApp(Main main) {
	        this.main = main;
	    }
}