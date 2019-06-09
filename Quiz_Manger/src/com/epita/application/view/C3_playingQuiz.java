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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class C3_playingQuiz {
	
	public Main main;
	private ObservableList<Question> Quiz =  FXCollections.observableArrayList();
	private ObservableList<String> UseranswerList = FXCollections.observableArrayList();
	
	private int currentnumber;
	private int quizsize = 0;
	private String Quiztopic;
	
    @FXML
    private ListView<String> choicecol;

    @FXML
    private Label questionLB;
    @FXML
    private Label qtopic1LB;
    @FXML
    private Label qtopic2LB;
    @FXML
    private Label qdifficultyLB;
    @FXML
    private Label QnumberLB;
    @FXML
    private Label QsizeLB;
    @FXML
    private Label buttonLB;
    @FXML
    private Label noteLB;
    @FXML
    private Label quiztopicLB;
    
    private int level = 0;
    
    @FXML
    private TextField answerF;
    
    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }
    
	@FXML
    private void initialize() {	
		currentnumber = 0;
	    questionLB.setText("Press start button");
	    qtopic1LB.setText("");
	    qtopic2LB.setText("");
	    qdifficultyLB.setText("");
	    buttonLB.setText("Start");
	    noteLB.setText("* Press start button");
	    this.level = 0;
    }
	
	public void getQuiz(ObservableList<Question> Quiz, String Quiztopic) {
		this.Quiz = Quiz;
		this.Quiztopic = Quiztopic;
		
		this.quizsize = Quiz.size();
		
		quiztopicLB.setText(("Quiz of "+Quiztopic));
		QsizeLB.setText(String.valueOf(quizsize));
		QnumberLB.setText("0");
	}
    
    @FXML
    public void gotonext() {
    	String answer = answerF.getText();
    	answerF.setText("");
    	noteLB.setText("");
    	if(currentnumber < quizsize) currentnumber++;
    	
    	QnumberLB.setText(String.valueOf(currentnumber));
    	//pressed start button
    	if(level==0) {
    		quizstart();
    	}
    	else if(level==1) {
    		getquestionofQuiz(currentnumber-1);
    		
    		if(currentnumber == quizsize) {
    				level++;
    				buttonLB.setText("Done");
    		}
    		
    	    if(! answer.equals(null)) {
    	       	UseranswerList.add(answer);
    	    } else {
    		Alert alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Error");
    		    alert.setHeaderText("Empty Answer");
    		    alert.setContentText("Enter your answer!");
    	
    		    alert.showAndWait();
    		}
    	    
    	}
    	else if(level==2) {
    		
    		if(! answer.equals(null)) {
    	       	UseranswerList.add(answer);
    	    } else {
    	    	Alert alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Error");
    		    alert.setHeaderText("Empty Answer");
    		    alert.setContentText("Enter your answer!");
    	
    		    alert.showAndWait();
    		}
    		
    		//for(int i=0;i<UseranswerList.size();i++) System.out.println(">>"+UseranswerList.get(i));
    		Quizresult();
    		
    	}
    } 
    
    public void quizstart() {
    	level++;
    	
    	getquestionofQuiz(0);
    	
    	if(quizsize==1) { 
    		buttonLB.setText("Done");
    		level++;
    	}
    	else if(quizsize>1) buttonLB.setText("Next");
    }
    
    public void quizzing(int number) {
    	getquestionofQuiz(number);
    }
    
    public void getquestionofQuiz(int number) {    	
    	Question question = Quiz.get(number);
    	
        questionLB.setText(question.getquestion());
        qtopic1LB.setText(question.getqtopic1());
        qtopic2LB.setText(question.getqtopic2());
        qdifficultyLB.setText(question.getqdifficulty());
        
        choicecol.getItems().clear();
        if(question.typeofquestion.equals("multi")) 
            for(int i=0;i<question.getchoices().MCQtoList().size();i++) 
            	choicecol.getItems().addAll(question.getchoices().MCQtoList().get(i));
        
        choicecol.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> choosing(newValue));

    }
    
    private void choosing(String choice) {
    	answerF.setText(choice);
    }
    
    private int checkquiz() {
    	int score=0;
    	for(int i=0;i<quizsize;i++) {
    		if(Quiz.get(i).getqanswer().equals(UseranswerList.get(i)))
    			score++;
    	}
    	
    	return score;
    }
    
    private void Quizresult() {
    	
    	int result = checkquiz();
    	int size = quizsize;
    	
    	try {
	    	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/V4_Quizresult.fxml"));
	        
	        AnchorPane Quizresult = (AnchorPane) loader.load();
	        
	        C4_Quizresult controller = loader.getController();
	        
	        controller.setMainApp(main);
	        controller.getresult(result, size, Quiztopic);
	        
	        main.setcentermainlayout(Quizresult);
    	} catch(IOException e) {}
    	
    }

}
