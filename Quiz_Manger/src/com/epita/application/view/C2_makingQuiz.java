package com.epita.application.view;

import com.epita.application.Main;
import com.epita.application.model.Question;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class C2_makingQuiz {
	private ObservableList<Question> searchedList = FXCollections.observableArrayList();
	
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
    private Label qtopic1LB;
    @FXML
    private Label qtopic2LB;
    @FXML
    private Label qdifficultyLB;
    @FXML
    private Label selectedtopicLB;
	
	public Main main;
	public C2_makingQuiz() {}
	
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
            qtopic1LB.setText(question.getqtopic1());
            qtopic2LB.setText(question.getqtopic2());
            qdifficultyLB.setText(question.getqdifficulty());
            choicecol.getItems().clear();
            if(question.typeofquestion.equals("multi")) {
	            for(int i=0;i<question.getchoices().MCQtoList().size();i++) 
	            	choicecol.getItems().addAll(question.getchoices().MCQtoList().get(i));
            }

        } else {
        	questionLB.setText("");
        	qtopic1LB.setText("");
            qtopic2LB.setText("");
            qdifficultyLB.setText("");
        }
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
        	//System.out.println("if "+q.getqtopic1()+" equals "+searchtopic+"||"+q.getqtopic2()+"is empty"+"&&"+ q.getqtopic2()+"equals"+searchtopic);
        	if(q.getqtopic1().equals(searchtopic)
        			||(!(q.getqtopic2().isEmpty()) && q.getqtopic2().equals(searchtopic)))
        		searchedList.add(q);
        }
    	
    }
    
    @FXML
    private void Searchquestion() {
    	String search = searchF.getText();
    	
    	setsearchedList(search);
    	
    	questiontable.setItems(searchedList);
        
    	qnumbercol.setCellValueFactory(cellData -> cellData.getValue().getqnumberP());
        qtopic1col.setCellValueFactory(cellData -> cellData.getValue().getqtopic1P());
        
        showQuestionDetails(null);
        
        questiontable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showQuestionDetails(newValue));

    	
    }
}
