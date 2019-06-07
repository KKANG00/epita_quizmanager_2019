package com.epita.application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class C3_playingQuiz {
	
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
    private TextField answerF;
    
    @FXML
    public void gotonext() {
    	//다음문제로 가거나
    	//결과로 가거나
    } 

}
