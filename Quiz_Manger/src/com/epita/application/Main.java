package com.epita.application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.epita.application.model.MCQChoice;
import com.epita.application.model.MCQuestion;
import com.epita.application.model.Question;
import com.epita.application.model.QuestionDBhandling;
import com.epita.application.view.C1_login;
import com.epita.application.view.C1_rootlayout;
import com.epita.application.view.C3_Add_Edit;
import com.epita.application.view.C3_questionoverview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
    private Stage primaryStage;
    private BorderPane mainlayout;
    
    private ObservableList<Question> questionList = FXCollections.observableArrayList();

    private static final String XML_FILE = "questionDB.xml";   
    
    public Main() {
  
    	fileXMLDAO xmlDAO = new fileXMLDAO();
    	
 //   	question, qtopic1, qtopic2, qnumber, qdifficulty, qanswer
//    	List<String> excl1 = new ArrayList<>();
//    	List<String> excl2 = new ArrayList<>();
//    	List<String> excl3 = new ArrayList<>();
//    	excl1.add("c11"); excl1.add("c12"); excl1.add("c13"); excl1.add("c14");
//    	excl2.add("c21"); excl2.add("c22"); excl2.add("c23"); excl2.add("c24");
//    	excl3.add("c31"); excl3.add("c32"); excl3.add("c33"); excl3.add("c34");
//    	MCQChoice excl1_ = new MCQChoice(excl1);
//    	MCQChoice excl2_ = new MCQChoice(excl2);
//    	MCQChoice excl3_ = new MCQChoice(excl3);
//    	
//    	Question example1 = new MCQuestion("Where is capital city of France?", "capital city", "", "01", "1/10", "3", excl1_);
//    	Question example2 = new MCQuestion("who is a president of France?", "country", "people", "02", "2/10", "1", excl2_);
//    	Question example3 = new MCQuestion("when is Christmas date?", "»ó½Ä", "", "03", "5/10", "1", excl3_);
//    	Question example4 = new Question("2+2?", "math", "", "04", "2/10", "four");
//    	
//    	questionList.add(example1);
//    	questionList.add(example2);
//    	questionList.add(example3);
//    	questionList.add(example4);  	

    	// get all questions in XML to questionList
//    	for(int i=0;i<questionList.size();i++) {
//    		xmlDAO.createNewUser(questionList.get(i));
//    	}
//    	Document doc = xmlDAO.initXmlDocument();
//		NodeList listOfquestions = doc.getElementsByTagName("question");
//		
//		for(int i=0;i<listOfquestions.getLength();i++) {
//			
//			questionList.add(xmlDAO.readfromXML(listOfquestions, i));
//		}
//    	
    	
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
            
            C1_rootlayout controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File file = new File("questionDB.xml");
        if(file != null) loadfromXML(file);
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
    
    public boolean Edit_AddPage(Question question) {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/V3_Add_Editlayout.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("<<Edit>>");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            C3_Add_Edit controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.editQuesiton(question);
            
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
	
	public File getDBfilePath() {
	    Preferences prefs = Preferences.userNodeForPackage(Main.class);
	    String filePath = prefs.get("filePath", null);
	    if (filePath != null) {
	        return new File(filePath);
	    } else {
	        return null;
	    }
	}
	
	public void setDBfilePath(File file) {
	    Preferences prefs = Preferences.userNodeForPackage(Main.class);
	    if (file != null) {
	        prefs.put("filePath", file.getPath());
	        
	        primaryStage.setTitle("QuizManager - " + file.getName());
	    } else {
	        prefs.remove("filePath");

	        primaryStage.setTitle("QuizManager");
	    }
	}
	
	public void loadfromXML(File file) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			NodeList questioncontentsList = doc.getElementsByTagName("question");
			
			for (int i = 0; i < questioncontentsList.getLength(); i++) {
				
				Element questionconentE = (Element) questioncontentsList.item(i);
				Element questionE = (Element) questionconentE.getParentNode();
				
				String questioncontent = String.valueOf(questionconentE.getTextContent());
				
				Element qtopic1E = (Element) questionE.getElementsByTagName("qtopic1").item(0);
				String qtopic1 = String.valueOf(qtopic1E.getTextContent());
				Element qtopic2E = (Element) questionE.getElementsByTagName("qtopic2").item(0);
				String qtopic2 = String.valueOf(qtopic2E.getTextContent());
				Element qnumberE = (Element) questionE.getElementsByTagName("qnumber").item(0);
				String qnumber = String.valueOf(qnumberE.getTextContent());
				Element qdifficultyE = (Element) questionE.getElementsByTagName("qdifficulty").item(0);
				String qdifficulty = String.valueOf(qdifficultyE.getTextContent());
				Element qanswerE = (Element) questionE.getElementsByTagName("qanswer").item(0);
				String qanswer = String.valueOf(qanswerE.getTextContent());
				Element typeofquestionE = (Element) questionE.getElementsByTagName("typeofquestion").item(0);
				String typeofquestion = String.valueOf(typeofquestionE.getTextContent());
				if(typeofquestion.equals("multi")) {
					Element choicesE = (Element) questionE.getElementsByTagName("choices").item(0);
					String choicesphrase = String.valueOf(choicesE.getTextContent());
					String delims = "[,]";
					String[] tokens = choicesphrase.split(delims);
					List<String> mcqchoices = new ArrayList<>();
					for (int j = 0; j < tokens.length; j++)
						mcqchoices.add(tokens[j]);
					MCQChoice choices = new MCQChoice(mcqchoices);
					Question question = new MCQuestion(questioncontent, qtopic1, qtopic2, qnumber, qdifficulty, qanswer, choices);

					questionList.add(question);
					System.out.println("added to list with"+questioncontent+ qtopic1+ qtopic2+ qnumber+ qdifficulty+ qanswer+ choices);
				} else {
					Question question = new Question(questioncontent, qtopic1, qtopic2, qnumber, qdifficulty, qanswer);
					questionList.add(question);

					System.out.println("added to list with"+questioncontent+ qtopic1+ qtopic2+ qnumber+ qdifficulty+ qanswer);
				}
			}
			
			setDBfilePath(file);


		} catch(Exception e) {}
	}
//	
//	public void loadfromXML(File file) {
//	    try {
//	        JAXBContext context = JAXBContext
//	                .newInstance(QuestionDBhandling.class);
//	        Unmarshaller um = context.createUnmarshaller();
//	        
//	        QuestionDBhandling wrapper = (QuestionDBhandling) um.unmarshal(file);
//
//	        questionList.clear();
//	        questionList.addAll(wrapper.getQuestions());
//
//	        setDBfilePath(file);
//
//	    } catch (Exception e) {
//	        Alert alert = new Alert(AlertType.ERROR);
//	        alert.setTitle("Error");
//	        alert.setHeaderText("Could not load data");
//	        alert.setContentText("Could not load data from file:\n" + file.getPath());
//
//	        alert.showAndWait();
//	    }
//	}
//
//	public void savetoXML(File file) {
//	    try {
//	        JAXBContext context = JAXBContext
//	                .newInstance(QuestionDBhandling.class);
//	        Marshaller m = context.createMarshaller();
//	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//	        QuestionDBhandling wrapper = new QuestionDBhandling();
//	        wrapper.setQuestions(questionList);
//
//	        m.marshal(wrapper, file);
//
//	        setDBfilePath(file);
//	    } catch (Exception e) {
//	        Alert alert = new Alert(AlertType.ERROR);
//	        alert.setTitle("Error");
//	        alert.setHeaderText("Could not save data");
//	        alert.setContentText("Could not save data to file:\n" + file.getPath());
//
//	        alert.showAndWait();
//	    }
//	}
//
}
