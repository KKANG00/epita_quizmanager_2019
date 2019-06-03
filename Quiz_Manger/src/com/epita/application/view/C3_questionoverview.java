package com.epita.application.view;



import com.epita.application.Main;
import com.epita.application.model.Question;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class C3_questionoverview {
	
	@FXML
    private TableView<Question> questiontable;
    @FXML
    private TableColumn<Question, String> qnumbercol;
    @FXML
    private TableColumn<Question, String> qtopic1col;
    @FXML
    private ListView<String> choicecol;
//    List<String> cl = new ArrayList<>();
//    ObservableList<String> choiceList = FXCollections.observableArrayList();

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
    private Label qanswerLB;
	
	public Main main;
	public C3_questionoverview() {}
	
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
            //qnumberLB.setText(question.getqnumber());
            qtopic1LB.setText(question.getqtopic1());
            qtopic2LB.setText(question.getqtopic2());
            qdifficultyLB.setText(question.getqdifficulty());
            qanswerLB.setText(question.getqanswer());
            choicecol.getItems().clear();
            if(question.typeofquestion.equals("multi")) {
	            for(int i=0;i<question.getchoices().MCQtoList().size();i++) 
	            	choicecol.getItems().addAll(question.getchoices().MCQtoList().get(i));
	//            choiceList = question.getchoices().MCQtoList();
	//            choicecol.setItems(choiceList);
            }

        } else {
        	questionLB.setText("");
        	qtopic1LB.setText("");
            qtopic2LB.setText("");
            qdifficultyLB.setText("");
            qanswerLB.setText("");
        }
    }
    
    @FXML
    private void Addquestion() {
        Question newquestion = new Question();
        boolean okClicked = main.Edit_AddPage(newquestion);
        if (okClicked) {
            main.getquesitonList().add(newquestion);
        }
    }
    
    @FXML
    private void Editquestion() {
        Question selected = questiontable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean okClicked = main.Edit_AddPage(selected);
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

}
