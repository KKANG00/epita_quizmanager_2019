package com.epita.application.view;

import com.epita.application.Main;
import com.epita.application.model.MCQuestion;
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
	
	/**
	 * list of questions after searching 
	 */
	private ObservableList<Question> searchedList =  FXCollections.observableArrayList();
	
	/**
	 * text field to enter search string
	 */
	@FXML
    private TextField searchF;
	/**
	 * table view to showing question list
	 */
	@FXML
    private TableView<Question> questiontable;
	/**
	 * table column to showing numbers of questions
	 */
    @FXML
    private TableColumn<Question, String> qnumbercol;
    /**
     * table column to showing first topics of questions
     */
    @FXML
    private TableColumn<Question, String> qtopic1col;
    /**
     * list view to showing choices of questions
     */
    @FXML
    private ListView<String> choicecol;

    /**
     * label to showing question contents
     */
    @FXML
    private Label questionLB;
    /**
     * label to showing question number
     */
    @FXML
    private Label qnumberLB;
    /**
     * label to showing first topic of question
     */
    @FXML
    private Label qtopicLB;
    /**
     * label to showing difficulty of question
     */
    @FXML
    private Label qdifficultyLB;
    /**
     * label to showing question answer
     */
    @FXML
    private Label qanswerLB;
    /**
     * label to showing question type
     */
    @FXML
    private Label qtypeLB;
	
	public Main main;
	
	/**
	 * constructor
	 */
	public C3_managingQuiz() {}
	
	/**
	 * initialize page
	 */
	@FXML
    private void initialize() {
        qnumbercol.setCellValueFactory(cellData -> cellData.getValue().getqnumberP());
        qtopic1col.setCellValueFactory(cellData -> cellData.getValue().getqtopic1P());
 
        showQuestionDetails(null);
        
        questiontable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showQuestionDetails(newValue));

    }
    
	/**
	 * show detail information of questions
	 * @param question questions to be shown details
	 */
    private void showQuestionDetails(Question question) {
        if (question != null) {
            questionLB.setText(question.getquestion());
            if(!question.getqtopic2().isEmpty()) qtopicLB.setText(question.getqtopic1()+", "+question.getqtopic2());
            else qtopicLB.setText(question.getqtopic1());
            qdifficultyLB.setText(question.getqdifficulty());
            qanswerLB.setText(question.getqanswer());
            choicecol.getItems().clear();
            qtypeLB.setText("["+question.typeofquestion+"]");
            
            if(question.typeofquestion.equals("multi")) {
	            for(int i=0;i<question.getchoices().MCQtoList().size();i++) 
	            	choicecol.getItems().addAll(question.getchoices().MCQtoList().get(i));
            }

        } else {
        	questionLB.setText("");
        	qtopicLB.setText("");
            qdifficultyLB.setText("");
            qanswerLB.setText("");
            qtypeLB.setText("");
        }
    }
    
    /**
     * add mcquestion method
     */
    @FXML
    private void AddMCQuestion() {
        Question newquestion = new MCQuestion();
        boolean okClicked = main.addandedit(newquestion);
        if (okClicked) {
            main.getquesitonList().add(newquestion);
        }
    }

    /**
     * add open question method
     */
    @FXML
    private void AddOpenquestion() {
        Question newquestion = new Question();
        boolean okClicked = main.addandedit(newquestion);
        if (okClicked) {
            main.getquesitonList().add(newquestion);
        }
    }
    
    /**
     * edit question method
     */
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
    
    /**
     * delete question method
     */
    @FXML
    private void Deletequestion() {
        int selected = questiontable.getSelectionModel().getSelectedIndex();
        if (selected >= 0) questiontable.getItems().remove(selected);
    }
    
    /**
     * searching method
     * @param searchtopic string to search
     */
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
    
    /**
     * search and set table view and columns
     */
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

    public void setMainApp(Main mainApp) {
        this.main = mainApp;

        questiontable.setItems(mainApp.getquesitonList());
    }
}
