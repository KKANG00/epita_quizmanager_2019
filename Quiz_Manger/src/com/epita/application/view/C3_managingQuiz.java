package com.epita.application.view;

import com.epita.application.Main;
import com.epita.application.model.Question;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class C3_managingQuiz {
	
	private ObservableList<Question> searchedList =  FXCollections.observableArrayList();
	
	@FXML
    private TextField searchF;
	@FXML
    private TableView<Question> questiontable;
    @FXML
    private TableColumn<Question, String> qnumbercol;
    @FXML
    private TableColumn<Question, String> qtopic1col;
    @FXML
    private ListView<String> choicecol;

    @FXML
    private Label questionLB;
    @FXML
    private Label qnumberLB;
    @FXML
    private Label qtopicLB;
    @FXML
    private Label qdifficultyLB;
    @FXML
    private Label qanswerLB;
	
	public Main main;
	public C3_managingQuiz() {}
	
	@FXML
    private void initialize() {
        qnumbercol.setCellValueFactory(cellData -> cellData.getValue().getqnumberP());
        qtopic1col.setCellValueFactory(cellData -> cellData.getValue().getqtopic1P());
 
        showQuestionDetails(null);
        
        questiontable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showQuestionDetails(newValue));

    }
    
    private void showQuestionDetails(Question question) {
        if (question != null) {
            questionLB.setText(question.getquestion());
            if(!question.getqtopic2().isEmpty()) qtopicLB.setText(question.getqtopic1()+", "+question.getqtopic2());
            else qtopicLB.setText(question.getqtopic1());
            qdifficultyLB.setText(question.getqdifficulty());
            qanswerLB.setText(question.getqanswer());
            choicecol.getItems().clear();
            if(question.typeofquestion.equals("multi")) {
	            for(int i=0;i<question.getchoices().MCQtoList().size();i++) 
	            	choicecol.getItems().addAll(question.getchoices().MCQtoList().get(i));
            }

        } else {
        	questionLB.setText("");
        	qtopicLB.setText("");
            qdifficultyLB.setText("");
            qanswerLB.setText("");
        }
    }
    
    @FXML
    private void Addquestion() {
        Question newquestion = new Question();
        boolean okClicked = main.addandedit(newquestion);
        if (okClicked) {
            main.getquesitonList().add(newquestion);
        }
    }
    
    @FXML
    private void Editquestion() {
        Question selected = questiontable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean okClicked = main.addandedit(selected);
            if (okClicked) {
                showQuestionDetails(selected);
            }

        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void Deletequestion() {
        int selected = questiontable.getSelectionModel().getSelectedIndex();
        if (selected >= 0) questiontable.getItems().remove(selected);
    }

    public void setMainApp(Main mainApp) {
        this.main = mainApp;

        questiontable.setItems(mainApp.getquesitonList());
    }
    
    public void setsearchedList(String searchtopic) {
    	searchedList.clear();
    	
    	ObservableList<Question> getquestionList = main.getquesitonList();
    	int questionListlength = getquestionList.size();
    	
        for(int i=0;i<questionListlength;i++) {
        	Question q = getquestionList.get(i);
        	if(q.getqtopic1().equals(searchtopic)
        			||(!(q.getqtopic2().isEmpty()) && q.getqtopic2().equals(searchtopic)))
        		searchedList.add(q);
        }
    	
    }
    
    @FXML
    private void Searchquestion() {
    	String search = searchF.getText();
    	if(!search.isEmpty()) {	    	
	    	setsearchedList(search);
	    	
	    	questiontable.setItems(searchedList);
	        
	    	qnumbercol.setCellValueFactory(cellData -> cellData.getValue().getqnumberP());
	        qtopic1col.setCellValueFactory(cellData -> cellData.getValue().getqtopic1P());
	        
	        showQuestionDetails(null);
	        
	        questiontable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showQuestionDetails(newValue));
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle("Error");
		    alert.setHeaderText("Empty search");
		    alert.setContentText("enter search keyword");
	
		    alert.showAndWait();
    	}
    }

}
