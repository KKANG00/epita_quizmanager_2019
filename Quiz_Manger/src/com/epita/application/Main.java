package com.epita.application;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

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
import com.epita.application.model.Result;
import com.epita.application.view.C0_rootlayout;
import com.epita.application.view.C3_addandedit;
import com.epita.application.view.C3_managingQuiz;
import com.epita.application.view.C3_playingQuiz;
import com.epita.application.view.C2_makingQuiz;
import com.epita.application.view.C1_startmenu;
import com.epita.application.view.C2_login;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
    private Stage primaryStage;
    private BorderPane mainlayout;
    
    /**
     * to check is it first executing or not
     */
    private boolean init = true;
    
    /**
     * question list used whole scope
     */
    private ObservableList<Question> questionList = FXCollections.observableArrayList();

    /**
     * name of database file
     */
    private static final String XML_FILE = "questionDB.xml";   
    
    /**
     * constructor
     */
    public Main() {}
    
    /**
     * set center of page above root layout
     * @param pane page to set to center
     */
    public void setcentermainlayout(AnchorPane pane) {
    	this.mainlayout.setCenter(pane);
    }
    
	@Override
	public void start(Stage primaryStage) {
		try {			
			this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("Quiz Manager");
	        //this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("appicon.png")));
	        
			initRootLayout();
	        startpage();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * first page of application
	 */
	public void startpage() {
		try {            
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(Main.class.getResource("view/V1_startmenu.fxml"));
            AnchorPane start = (AnchorPane) loader2.load();

            mainlayout.setCenter(start);
            
            C1_startmenu controller2 = loader2.getController();
            controller2.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * back ground of whole pages (menu bar at the top)
	 */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/V0_rootlayout.fxml"));
            mainlayout = (BorderPane) loader.load();

            Scene scene = new Scene(mainlayout);
            primaryStage.setScene(scene);
            
            C0_rootlayout controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
            
            if(init) {
	            File file = new File("questionDB.xml");	
	            if(file != null) loadfromXML(file);
	            init = false;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * login page to move to managing page
     */
    public void login() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/V2_login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();

            mainlayout.setCenter(login);
            
            C2_login controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * managing quiz page
     */
    public void managingQuiz() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/V3_managingQuiz.fxml"));
            AnchorPane managingQuiz = (AnchorPane) loader.load();

            mainlayout.setCenter(managingQuiz);
            
            C3_managingQuiz controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * making quiz page before playing quiz page
     */
    public void makingQuiz() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/V2_makingQuiz.fxml"));
            AnchorPane makingQuiz = (AnchorPane) loader.load();

            mainlayout.setCenter(makingQuiz);
            
            C2_makingQuiz controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * playing quiz page 
     */
    public void playingQuiz() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/V3_playingQuiz.fxml"));
            AnchorPane playingQuiz = (AnchorPane) loader.load();

            mainlayout.setCenter(playingQuiz);
            
            C3_playingQuiz controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * pop up page to perform add and edit question
     * @param question question class to be added or edited
     * @return to check menu button is clicked or not
     */
    public boolean addandedit(Question question) {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/V3_addandedit.fxml"));
            AnchorPane addandedit = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("<<Managing>>");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(addandedit);
            dialogStage.setScene(scene);
            
            C3_addandedit controller = loader.getController();
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
    
    /**
     * for getting question list used whole scope of application
     * @return question list derived from DB
     */
    public ObservableList<Question> getquesitonList(){
    	return questionList;
    }	

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * getting file path from loaded or saved path before
	 * @return file path to save file
	 */
//	public File getDBfilePath() {
//	    Preferences prefs = Preferences.userNodeForPackage(Main.class);
//	    String filePath = prefs.get("filePath", null);
//	    if (filePath != null) {
//	        return new File(filePath);
//	    } else {
//	        return null;
//	    }
//	}
	
	/**
	 * setting file path as used when load from and save to DB
	 * @param file file used when load from and save to DB
	 */
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
	
	/**
	 * fileDAO transform xml file method
	 * @param doc document class to be transformed
	 */
	public void transformXmlDocument(Document doc){
		try {
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.transform(new DOMSource(doc), new StreamResult(XML_FILE));

		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * load questions from xml file to question list when executing application 
	 * @param file file to get database from
	 */
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
				} else {
					Question question = new Question(questioncontent, qtopic1, qtopic2, qnumber, qdifficulty, qanswer);
					question.settype("open");
					questionList.add(question);

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
	
	/**
	 * save questions to xml file when save button executed
	 * @param file file to save database to
	 */
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
	
	/*
	 * reset current question list class to open new file database
	 */
	public void resetquestionList() {
		int from =0;
		int to = questionList.size();
		questionList.remove(from, to);
	}
	
	/**
	 * method to save result of quiz to text file
	 * @param result quiz result class after playing quiz
	 */
	public void savetoDB(Result result) {
		try {
		      BufferedWriter file = new BufferedWriter(new FileWriter("result.txt", true));
		      String resultstring = result.toString();

		      file.write(resultstring);
		      file.newLine();
		      file.close();
		      Desktop.getDesktop().open(new File("result.txt"));
		      
		    } catch (IOException e) {}
	}
	
	/**
	 * method to save quiz to text file
	 * @param quiz quiz played by user (question list)
	 * @param topic topic string to set a file name
	 * @param quizsize size of quiz
	 */
	public void savequiz(ObservableList<Question> quiz, String topic, int quizsize) {
		try {
			  String filename = "Quiz "+topic +".txt";
		      BufferedWriter file = new BufferedWriter(new FileWriter(filename, false));
		      
		      for(int i=0;i<quizsize;i++) {
		    	  String question = quiz.get(i).qtoString();
			      file.write(question);
			      file.newLine();
		      }
		      
		      file.close();
		      Desktop.getDesktop().open(new File(filename));
		      
		    } catch (IOException e) {}
	}
}
