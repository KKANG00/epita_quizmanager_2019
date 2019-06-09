package com.epita.application.view;

import java.io.IOException;

import com.epita.application.Main;
import com.epita.application.model.Question;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
        selectedtopicLB.setText("");
        
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
    	selectedtopicLB.setText(search);
    	
    	setsearchedList(search);
    	
    	questiontable.setItems(searchedList);
        
    	qnumbercol.setCellValueFactory(cellData -> cellData.getValue().getqnumberP());
        qtopic1col.setCellValueFactory(cellData -> cellData.getValue().getqtopic1P());
        
        showQuestionDetails(null);
        
        questiontable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showQuestionDetails(newValue));
        
    }
    
    @FXML
    private void playingQuiz() {
//    	setQuiz();
//    	main.Quiztopic = selectedtopicLB.getText();
//    	main.playingQuiz();
    	
    	if(!searchedList.isEmpty()) {
	    	try {
		    	FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(Main.class.getResource("view/V3_playingQuiz.fxml"));
		        AnchorPane playingQuiz = (AnchorPane) loader.load();
		        //Parent root = (Parent) loader.load();
		        
		        C3_playingQuiz controller = loader.getController();
		        controller.getQuiz(searchedList, selectedtopicLB.getText());
		        
		        controller.setMainApp(main);
	//	        Stage stage = new Stage();
	//	        stage.setScene(new Scene(root));
		        
		        main.setcentermainlayout(playingQuiz);
		        
		        //stage.show();
		        
	    	} catch(IOException e) {}
	    	
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle("Error");
		    alert.setHeaderText("Empty Quiz");
		    alert.setContentText("Empty quiz. search another topic");
	
		    alert.showAndWait();
    	}
    }

}
