package com.epita.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.epita.application.model.MCQChoice;
import com.epita.application.model.MCQuestion;
import com.epita.application.model.Question;
import com.epita.application.model.QuestionDBhandling;
import com.epita.application.view.C1_rootlayout;
import com.epita.application.view.C3_Add_Edit;
import com.epita.application.view.C3_questionoverview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
    private Stage primaryStage;
    private BorderPane mainlayout;
    private Parent root;
    
    @FXML
    private Button managing;
    private Button playing;
    
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
	        
//	        initRootLayout(); showQuestionOverview();
	        
	        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/V3_rootlayout.fxml"));
            mainlayout = (BorderPane) loader.load();

            Scene scene = new Scene(mainlayout);
            primaryStage.setScene(scene);
            
            C1_rootlayout controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
            
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(Main.class.getResource("view/V1_startmenu.fxml"));
            AnchorPane QuestionOverview = (AnchorPane) loader2.load();

            mainlayout.setCenter(QuestionOverview);
            
//            C3_questionoverview controller2 = loader2.getController();
//            controller2.setMainApp(this);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startpage() {
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
	}
	
	@FXML
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

	@FXML
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
    
//    public void Login() {
//    	try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(Main.class.getResource("view/V1_login.fxml"));
//            AnchorPane loginpage = (AnchorPane) loader.load();
//
//            mainlayout.setCenter(loginpage);
//            
//            C1_login controller = loader.getController();
//            controller.setMainApp(this);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
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
	
	public void transformXmlDocument(Document doc){
		try {
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.transform(new DOMSource(doc), new StreamResult(XML_FILE)); // updates the xml file
			// according to the document java object

		} catch (TransformerException e) {
			e.printStackTrace();
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
					//System.out.println("added to list with"+questioncontent+ qtopic1+ qtopic2+ qnumber+ qdifficulty+ qanswer+ choices);
				} else {
					Question question = new Question(questioncontent, qtopic1, qtopic2, qnumber, qdifficulty, qanswer);
					questionList.add(question);

					//System.out.println("added to list with"+questioncontent+ qtopic1+ qtopic2+ qnumber+ qdifficulty+ qanswer);
				}
			}
			
			setDBfilePath(file);


		} catch(Exception e) {
			
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not load data");
	        alert.setContentText("Could not load data from file:\n" + file.getPath());

	        alert.showAndWait();
		}
	}
	
	public void savetoXML(File file) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			Element rootElement = doc.getDocumentElement();
			doc.removeChild(rootElement);

			Element newrootE = doc.createElement("Questions");
			doc.appendChild(newrootE);
			
			for(int i=0;i<questionList.size();i++) {
				Question q = questionList.get(i);		
				Element newquestionE = doc.createElement("Question");
		
				Element newquestioncontentE = doc.createElement("question");
				newquestioncontentE.setTextContent(q.getquestion());
				Element newqtopic1E = doc.createElement("qtopic1");
				newqtopic1E.setTextContent(q.getqtopic1());
				Element newqtopic2E = doc.createElement("qtopic2");
				newqtopic2E.setTextContent(q.getqtopic2());
				Element newqnumberE = doc.createElement("qnumber");
				newqnumberE.setTextContent(q.getqnumber());
				Element newqdifficultyE = doc.createElement("qdifficulty");
				newqdifficultyE.setTextContent(q.getqdifficulty());
				Element newqanswerE = doc.createElement("qanswer");
				newqanswerE.setTextContent(q.getqanswer());
				Element newtqE = doc.createElement("typeofquestion");
				newtqE.setTextContent(q.typeofquestion);
				
				Element newchoicesE = doc.createElement("choices");
				
				if(q.typeofquestion.equals("multi")) newchoicesE.setTextContent(q.getchoices().choices_ListtoString());
				else newchoicesE.setTextContent("");
				
				//System.out.println("this is "+i+"th question: "+q.getquestion());
				
				newquestionE.appendChild(newquestioncontentE);
				newquestionE.appendChild(newqtopic1E);
				newquestionE.appendChild(newqtopic2E);
				newquestionE.appendChild(newqnumberE);
				newquestionE.appendChild(newqdifficultyE);
				newquestionE.appendChild(newqanswerE);
				newquestionE.appendChild(newtqE);
				newquestionE.appendChild(newchoicesE);
				
				newrootE.appendChild(newquestionE);
				
			}

			transformXmlDocument(doc);
			setDBfilePath(file);
			
		} catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());

	        alert.showAndWait();
		}
		
	}
}
