package com.epita.application;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.epita.application.model.MCQChoice;
import com.epita.application.model.MCQuestion;
import com.epita.application.model.Question;
import com.epita.application.view.C1_login;
import com.epita.application.view.C3_questionoverview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
    private Stage primaryStage;
    private BorderPane mainlayout;
    
    private ObservableList<Question> questionList = FXCollections.observableArrayList();
//    private ObservableList<String> choiceList = FXCollections.observableArrayList();

    
    public Main() {
//    	question, qtopic1, qtopic2, qnumber, qdifficulty
    	List<String> excl1 = new ArrayList<>();
    	List<String> excl2 = new ArrayList<>();
    	List<String> excl3 = new ArrayList<>();
    	excl1.add("c11"); excl1.add("c12"); excl1.add("c13"); excl1.add("c14");
    	excl2.add("c21"); excl2.add("c22"); excl2.add("c23"); excl2.add("c24");
    	excl3.add("c31"); excl3.add("c32"); excl3.add("c33"); excl3.add("c34");
    	MCQChoice excl1_ = new MCQChoice(excl1);
    	MCQChoice excl2_ = new MCQChoice(excl2);
    	MCQChoice excl3_ = new MCQChoice(excl3);
    	
    	questionList.add(new MCQuestion("Where is capital city of France?", "capital city", "", "01", "1/10", excl1_));
    	questionList.add(new MCQuestion("who is a president of France?", "country", "people", "02", "2/10", excl2_));
    	questionList.add(new MCQuestion("when is Christmas date?", "»ó½Ä", "", "03", "5/10", excl3_));
    	questionList.add(new Question("2+2?", "math", "", "04", "2/10"));
    }
    
	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
			
			this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("Quiz Manager");

	        initRootLayout();
	        
	        //Login();

	        showQuestionOverview();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/V3_rootlayout.fxml"));
            mainlayout = (BorderPane) loader.load();

            Scene scene = new Scene(mainlayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showQuestionOverview() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/V3_questionoverview.fxml"));
            AnchorPane QuestionOverview = (AnchorPane) loader.load();

            mainlayout.setCenter(QuestionOverview);
            
            C3_questionoverview controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void Login() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/V1_login.fxml"));
            AnchorPane loginpage = (AnchorPane) loader.load();

            mainlayout.setCenter(loginpage);
            
            C1_login controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public ObservableList<Question> getquesitonList(){
    	return questionList;
    }	

	public static void main(String[] args) {
		launch(args);
	}
}
