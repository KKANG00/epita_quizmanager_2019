package com.epita.application.view;

import java.io.IOException;

import com.epita.application.Main;
import com.epita.application.model.Question;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class C2_makingQuiz {
	
	/**
	 * list of questions after searching 
	 */
	private ObservableList<Question> searchedList = FXCollections.observableArrayList();
	
	/**
	 * text field to enter search string
	 */
	@FXML
    private TextField searchF;
	/**
	 * question table view to show question list
	 */
	@FXML
    private TableView<Question> questiontable;
	/**
	 * table column for numbers of questions
	 */
    @FXML
    private TableColumn<Question, String> qnumbercol;
    /**
	 * table column for first topics of questions
	 */
    @FXML
    private TableColumn<Question, String> qtopic1col;
    /**
     * list view to show choices list
     */
    @FXML
    private ListView<String> choicecol;

    /**
     * label for showing question contents
     */
    @FXML
    private Label questionLB;
    /**
     * label for showing numbers of questions
     */
    @FXML
    private Label qnumberLB;
    /**
     * label for showing topics of questions
     */
    @FXML
    private Label qtopicLB;
    /**
     * label for showing difficulties of questions
     */
    @FXML
    private Label qdifficultyLB;
    /**
     * label for showing topic that is selected by user to be made as quiz
     */
    @FXML
    private Label selectedtopicLB;
	
	public Main main;
	/**
	 * constructor
	 */
	public C2_makingQuiz() {}
	
	/**
	 * initialize for page
	 */
	@FXML
    private void initialize() {
        qnumbercol.setCellValueFactory(cellData -> cellData.getValue().getqnumberP());
        qtopic1col.setCellValueFactory(cellData -> cellData.getValue().getqtopic1P());
 
        showQuestionDetails(null);
        selectedtopicLB.setText("");
        
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
            choicecol.getItems().clear();
            if(question.typeofquestion.equals("multi")) {
	            for(int i=0;i<question.getchoices().MCQtoList().size();i++) 
	            	choicecol.getItems().addAll(question.getchoices().MCQtoList().get(i));
            }

        } else {
        	questionLB.setText("");
        	qtopicLB.setText("");
            qdifficultyLB.setText("");
        }
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
    	
	    	selectedtopicLB.setText(search);
	    	
	    	setsearchedList(search);
	    	
	    	questiontable.setItems(searchedList);
	        
	    	qnumbercol.setCellValueFactory(cellData -> cellData.getValue().getqnumberP());
	        qtopic1col.setCellValueFactory(cellData -> cellData.getValue().getqtopic1P());
	        
	        showQuestionDetails(null);
	        
	        questiontable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showQuestionDetails(newValue));
    	} else {
    		questiontable.setItems(main.getquesitonList());
	        
	    	qnumbercol.setCellValueFactory(cellData -> cellData.getValue().getqnumberP());
	        qtopic1col.setCellValueFactory(cellData -> cellData.getValue().getqtopic1P());
	        
	        showQuestionDetails(null);
	        
	        questiontable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showQuestionDetails(newValue));
    	}
    }
    
    /**
     * after quiz made pass arguments and open playing quiz page
     */
    @FXML
    private void playingQuiz() {    	
    	if(!searchedList.isEmpty()) {
	    	try {
		    	FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(Main.class.getResource("view/V3_playingQuiz.fxml"));
		        AnchorPane playingQuiz = (AnchorPane) loader.load();
		        
		        C3_playingQuiz controller = loader.getController();
		        controller.getQuiz(searchedList, selectedtopicLB.getText());
		        
		        controller.setMainApp(main);		        
		        main.setcentermainlayout(playingQuiz);
		        
	    	} catch(IOException e) {}
	    	
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle("Error");
		    alert.setHeaderText("Empty Quiz");
		    alert.setContentText("Empty quiz. search another topic");
	
		    alert.showAndWait();
    	}
    }

    public void setMainApp(Main mainApp) {
        this.main = mainApp;

        questiontable.setItems(mainApp.getquesitonList());
    }

}
