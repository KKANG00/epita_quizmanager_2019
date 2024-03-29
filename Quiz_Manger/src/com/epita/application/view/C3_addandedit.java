package com.epita.application.view;

import java.util.ArrayList;
import java.util.List;

import com.epita.application.model.MCQChoice;
import com.epita.application.model.Question;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class C3_addandedit {
	
	/**
	 * text field to enter first topic
	 */
	@FXML
    private TextField qtopic1F;
	/**
	 * text field to enter second topic
	 */
	@FXML
    private TextField qtopic2F;
	/**
	 * text field to enter difficulty
	 */
	@FXML
    private TextField qdifficultyF;
	/**
	 * text field to enter question contents
	 */
	@FXML
    private TextField questionF;
	/**
	 * text field to enter number
	 */
	@FXML
    private TextField qnumberF;
	/**
	 * text field to enter choice1
	 */
	@FXML
    private TextField MCQChoice1F;
	/**
	 * text field to enter choice2
	 */
	@FXML
    private TextField MCQChoice2F;
	/**
	 * text field to enter choice3
	 */
	@FXML
    private TextField MCQChoice3F;
	/**
	 * text field to enter choice4
	 */
	@FXML
    private TextField MCQChoice4F;
	/**
	 * text field to enter answer
	 */
	@FXML
    private TextField qanswerF;
	/**
	 * text field to enter note
	 */
	@FXML
	private Label noteF;
	
	private Stage dialogStage;
	
	/**
	 * question class to be added or edited
	 */
    private Question question;
    /**
     * check done button is clicked or not
     */
    private boolean okClicked = false;
    
    /**
     * initialize for page
     */
    @FXML
    private void initialize() {
    	noteF.setText("");
    }
    
    /**
     * set stage of application to pop up page
     * @param dialogStage stage of application
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * edit and add questions by pop up page
     * @param question question to be added and edited
     */
    public void editQuesiton(Question question) {
        this.question = question;

        qtopic1F.setText(question.getqtopic1());
        qtopic2F.setText(question.getqtopic2());
        qdifficultyF.setText(question.getqdifficulty());
        questionF.setText(question.getquestion());
        qnumberF.setText(question.getqnumber());
        qanswerF.setText(question.getqanswer());
        
        if((question.getqtopic1() == null)) {
        	if(question.typeofquestion.equals("multi")) {
        		noteF.setText("MCQuestion");
               	MCQChoice1F.setText("");
            	MCQChoice2F.setText("");
            	MCQChoice3F.setText("");
            	MCQChoice4F.setText("");
        	}
        	else { 
        		noteF.setText("Open Question");
	        	MCQChoice1F.setDisable(true);
	        	MCQChoice2F.setDisable(true);
	        	MCQChoice3F.setDisable(true);
	        	MCQChoice4F.setDisable(true);
        	}
        }
        else if(question.typeofquestion.equals("multi")) {
        	noteF.setText("MCQuestion");
        	switch(question.getchoices().MCQtoList().size()) {
        	case 2: MCQChoice1F.setText(question.getchoices().MCQtoList().get(0));
	        		MCQChoice2F.setText(question.getchoices().MCQtoList().get(1));
	        		MCQChoice3F.setText("");
	            	MCQChoice4F.setText(""); break;
        	case 3: MCQChoice1F.setText(question.getchoices().MCQtoList().get(0));
    				MCQChoice2F.setText(question.getchoices().MCQtoList().get(1));
    				MCQChoice3F.setText(question.getchoices().MCQtoList().get(2));
    				MCQChoice4F.setText(""); break;
        	case 4: MCQChoice1F.setText(question.getchoices().MCQtoList().get(0));
					MCQChoice2F.setText(question.getchoices().MCQtoList().get(1));
					MCQChoice3F.setText(question.getchoices().MCQtoList().get(2));
        			MCQChoice4F.setText(question.getchoices().MCQtoList().get(3)); break;
        	}
        } 
        else if(question.typeofquestion.equals("open")) {
        	noteF.setText("Open Question");
        	MCQChoice1F.setDisable(true);
        	MCQChoice2F.setDisable(true);
        	MCQChoice3F.setDisable(true);
        	MCQChoice4F.setDisable(true);
        }
    }
    
    /**
     * done button clicked or not
     * @return "true" if clicked "false" if not
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * done button
     */
    @FXML
    private void EditDone() {
    	boolean check = false;
    	if(question.typeofquestion.equals("multi"))
    		check = InputCheck_mcq();
    	else check = InputCheck_open();
        
    	if (check) {
            question.setqtopic1(qtopic1F.getText());
            question.setqtopic2(qtopic2F.getText());
            question.setqdifficulty(qdifficultyF.getText());
            question.setquestion(questionF.getText());
            question.setqnumber(qnumberF.getText());
            question.setqanswer(qanswerF.getText());
            

	        //edit
	        if(question.typeofquestion.equals("multi")) {
	        	List<String> choicelist = new ArrayList<>();
	            if(!MCQChoice1F.getText().isEmpty()) choicelist.add(MCQChoice1F.getText()); 
	            if(!MCQChoice2F.getText().isEmpty()) choicelist.add(MCQChoice2F.getText()); 
	            if(!MCQChoice3F.getText().isEmpty()) choicelist.add(MCQChoice3F.getText()); 
	            if(!MCQChoice4F.getText().isEmpty()) choicelist.add(MCQChoice4F.getText());
	            MCQChoice choices = new MCQChoice(choicelist);
	            question.setchoices(choices);
	            	
	        } else question.settype("open");

            okClicked = true;
            dialogStage.close();
            
        }
    }
    
    /**
     * cancel button
     */
    @FXML
    private void EditCancel() {
    	okClicked = true;
        dialogStage.close();
    }
    
    /**
     * check fields are filled properly or not for open question
     * @return "true" if it is okay "false" if there is empty field that should be filled
     */
    private boolean InputCheck_open() {
        String errorMessage = "";

        if (qtopic1F.getText() == null || qtopic1F.getText().length() == 0) {
            errorMessage += "Please fill topic1 field\n";
        }
        if (qdifficultyF.getText() == null || qdifficultyF.getText().length() == 0) {
            errorMessage += "Please fill qdifficultyF field\n";
        }
        if (questionF.getText() == null || questionF.getText().length() == 0) {
            errorMessage += "Please fill questionF field\n";
        }

        if (qanswerF.getText() == null || qanswerF.getText().length() == 0) {
            errorMessage += "Please fill qanswerF field\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }

    }
    
    /**
     * check fields are filled properly or not for open question
     * @return "true" if it is okay "false" if there is empty field that should be filled
     */
    private boolean InputCheck_mcq() {
        String errorMessage = "";

        if (qtopic1F.getText() == null || qtopic1F.getText().length() == 0) {
            errorMessage += "Please fill topic1 field\n";
        }
        if (qdifficultyF.getText() == null || qdifficultyF.getText().length() == 0) {
            errorMessage += "Please fill qdifficultyF field\n";
        }
        if (questionF.getText() == null || questionF.getText().length() == 0) {
            errorMessage += "Please fill questionF field\n";
        }
        if (MCQChoice1F.getText() == null || MCQChoice1F.getText().length() == 0) {
            errorMessage += "MCQuestion needs at least 2 choices\n";
        }
        if (MCQChoice2F.getText() == null || MCQChoice2F.getText().length() == 0) {
            errorMessage += "MCQuestion needs at least 2 choices\n";
        }
        if (qanswerF.getText() == null || qanswerF.getText().length() == 0) {
            errorMessage += "Please fill qanswerF field\n";
        }
        else if(!(qanswerF.getText().equals(MCQChoice1F.getText()) || qanswerF.getText().equals(MCQChoice2F.getText())
        		|| qanswerF.getText().equals(MCQChoice3F.getText()) || qanswerF.getText().equals(MCQChoice4F.getText())))
        	errorMessage += "Answer should be one of the choices\n";
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}