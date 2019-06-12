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
	/**
	 * question list that is made by user's choose topic
	 */
	private ObservableList<Question> Quiz =  FXCollections.observableArrayList();
	/**
	 * list of string to save answers from users
	 */
	private ObservableList<String> UseranswerList = FXCollections.observableArrayList();
	
	/**
	 * number of current question (by order)
	 */
	private int currentnumber;
	/**
	 * size of quiz
	 */
	private int quizsize = 0;
	/**
	 * topic of quiz
	 */
	private String Quiztopic;
	/**
	 * list view for showing choices
	 */
    @FXML
    private ListView<String> choicecol;
    /**
     * label for showing question contents
     */
    @FXML
    private Label questionLB;
    /**
     * label for showing first topic of question
     */
    @FXML
    private Label qtopicLB;
    /**
     * label for showing difficulty of question
     */
    @FXML
    private Label qdifficultyLB;
    /**
     * label for showing number of question
     */
    @FXML
    private Label QnumberLB;
    /**
     * label for showing quiz size
     */
    @FXML
    private Label QsizeLB;
    /**
     * label for showing button name
     */
    @FXML
    private Label buttonLB;
    /**
     * label for showing note
     */
    @FXML
    private Label noteLB;
    /**
     * label for showing topic of quiz
     */
    @FXML
    private Label quiztopicLB;
    /**
     * text field to enter answer of question
     */
    @FXML
    private TextField answerF;
    
    /**
     * instance to change button start->next->done as order
     */
    private int level = 0;
    
    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }
    
    /**
     * initialize page
     */
	@FXML
    private void initialize() {	
		currentnumber = 0;
	    questionLB.setText("Press start button");
	    qtopicLB.setText("");
	    qdifficultyLB.setText("");
	    buttonLB.setText("Start");
	    noteLB.setText("* Press start button");
	    this.level = 0;
    }
	
	/**
	 * get quiz data from last page 
	 * @param Quiz question list made by topic user choose
	 * @param Quiztopic quiz topic user choose
	 */
	public void getQuiz(ObservableList<Question> Quiz, String Quiztopic) {
		this.Quiz = Quiz;
		this.Quiztopic = Quiztopic;
		this.quizsize = Quiz.size();
		answerF.setText("press start button ==>");;
		
		quiztopicLB.setText(("Quiz of "+Quiztopic));
		QsizeLB.setText(String.valueOf(quizsize));
		QnumberLB.setText("0");
	}
    
	/**
	 * on playing quiz button (start,next,done)
	 */
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
    		Quizresult();
    		
    	}
    } 
    
    /**
     * quiz start
     */
    public void quizstart() {
    	level++;
    	
    	getquestionofQuiz(0);
    	
    	if(quizsize==1) { 
    		buttonLB.setText("Done");
    		level++;
    	}
    	else if(quizsize>1) buttonLB.setText("Next");
    }
    
    /**
     * get questions detail from list
     * @param number question number in quiz
     */
    public void getquestionofQuiz(int number) {    	
    	Question question = Quiz.get(number);
    	
        questionLB.setText(question.getquestion());
        if(!question.getqtopic2().isEmpty()) qtopicLB.setText(question.getqtopic1()+", "+question.getqtopic2());
        else qtopicLB.setText(question.getqtopic1());
        qdifficultyLB.setText(question.getqdifficulty());
        
        choicecol.getItems().clear();
        if(question.typeofquestion.equals("multi")) 
            for(int i=0;i<question.getchoices().MCQtoList().size();i++) 
            	choicecol.getItems().addAll(question.getchoices().MCQtoList().get(i));
        
        choicecol.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> choosing(newValue));

    }
    
    /**
     * set answer field as user choose (click one of list)
     * @param choice choice string that user choose
     */
    private void choosing(String choice) {
    	answerF.setText(choice);
    }
    
    /**
     * evaluating quiz
     * @return how many questions user got correct
     */
    private int checkquiz() {
    	int score=0;
    	for(int i=0;i<quizsize;i++) {
    		if(Quiz.get(i).getqanswer().equals(UseranswerList.get(i)))
    			score++;
    	}
    	
    	return score;
    }
    
    /**
     * show result of quiz and pass the result to next page
     */
    private void Quizresult() {
    	
    	int result = checkquiz();
    	int size = quizsize;
    	
    	try {
	    	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/V4_Quizresult.fxml"));
	        
	        AnchorPane Quizresult = (AnchorPane) loader.load();
	        
	        C4_Quizresult controller = loader.getController();
	        
	        controller.setMainApp(main);
	        controller.getresult(result, size, Quiztopic, Quiz);
	        
	        main.setcentermainlayout(Quizresult);
    	} catch(IOException e) {}
    	
    }

}
