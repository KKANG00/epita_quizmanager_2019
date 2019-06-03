package com.epita.application;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.epita.application.model.MCQChoice;
import com.epita.application.model.MCQuestion;
import com.epita.application.model.Question;

public class fileXMLDAO {

	public static final String XML_FILE = "questionDB.xml";

	public Document initXmlDocument() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // fetch a factory
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder(); // create a document builder from the factory
			Document document = documentBuilder.parse(new File(XML_FILE)); // parse the given file into the Document
																			// object
			return document;
		} catch (Exception e) { // BAD PRACTICE
			e.printStackTrace();
		}
		return null; // BAD PRACTICE
	}

	public void transformXmlDocument(Document doc) {
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

	public void createNewUser(Question question) {
		Document doc = initXmlDocument(); // contains all data pertaining to xml file
		//Element rootElement = doc.getDocumentElement();

		Element newUserElement = doc.createElement("Question"); // create new user element
		doc.appendChild(newUserElement);
		
//		Attr attr = doc.createAttribute("id");
//		attr.setValue("1");
//		newUserElement.setAttributeNode(attr);

		Element newquestionE = doc.createElement("question");
		newquestionE.setTextContent(question.getquestion());
		
		Element newnumberE = doc.createElement("qnumber");
		newnumberE.setTextContent(question.getqnumber());

		Element newtopic1E = doc.createElement("qtopic1");
		newtopic1E.setTextContent(question.getqtopic1());
		
		Element newtopic2E = doc.createElement("qtopic2");
		newtopic2E.setTextContent(question.getqtopic2());
		
		Element newdifficultyE = doc.createElement("qdifficulty");
		newdifficultyE.setTextContent(question.getqdifficulty());
		
		Element qanswerE = doc.createElement("qanswer");
		qanswerE.setTextContent(question.getqanswer());
		
		newUserElement.appendChild(newnumberE);
		newUserElement.appendChild(newtopic1E);
		newUserElement.appendChild(newtopic2E);
		newUserElement.appendChild(newquestionE);
		newUserElement.appendChild(newdifficultyE);
		newUserElement.appendChild(qanswerE);

		transformXmlDocument(doc); // invoke a method to update xml file with new java document object

	}
	
//	public Question readfromXML(NodeList listOfquestions, int i) {
//		try {
//			Element questioncontentE = (Element) listOfquestions.item(i);
//				
//			Element questionE = (Element) questioncontentE.getParentNode();
//				
//			String questioncontent = String.valueOf(questioncontentE.getTextContent());
//				
//			Element qtopic1E = (Element) questionE.getElementsByTagName("qtopic1").item(i);
//			String qtopic1 = String.valueOf(qtopic1E.getTextContent());
//			Element qtopic2E = (Element) questionE.getElementsByTagName("qtopic2").item(i);
//			String qtopic2 = String.valueOf(qtopic2E.getTextContent());
//			Element qnumberE = (Element) questionE.getElementsByTagName("qnumber").item(i);
//			String qnumber = String.valueOf(qnumberE.getTextContent());
//			Element qdifficultyE = (Element) questionE.getElementsByTagName("qdifficulty").item(i);
//			String qdifficulty = String.valueOf(qdifficultyE.getTextContent());
//			Element qanswerE = (Element) questionE.getElementsByTagName("qanswer").item(i);
//			String qanswer = String.valueOf(qanswerE.getTextContent());
//			Element typeofquestionE = (Element) questionE.getElementsByTagName("typeofquestion").item(i);
//			String typeofquestion = String.valueOf(typeofquestionE.getTextContent());
//				
//			if(typeofquestion.equals("multi")) {
//				List<String> mcqchoices = new ArrayList<>();
//				Element choice1E = (Element) questionE.getElementsByTagName("choice1").item(i);
//				String choice1 = String.valueOf(choice1E.getTextContent());
//				Element choice2E = (Element) questionE.getElementsByTagName("choice2").item(i);
//				String choice2 = String.valueOf(choice2E.getTextContent());
//				Element choice3E = (Element) questionE.getElementsByTagName("choice3").item(i);
//				String choice3 = String.valueOf(choice3E.getTextContent());
//				Element choice4E = (Element) questionE.getElementsByTagName("choice4").item(i);
//				String choice4 = String.valueOf(choice4E.getTextContent());
//					
//				mcqchoices.add(choice1); mcqchoices.add(choice2); mcqchoices.add(choice3); mcqchoices.add(choice4);
//			    MCQChoice choices = new MCQChoice(mcqchoices);
//				Question question = new MCQuestion(questioncontent, qtopic1, qtopic2, qnumber, qdifficulty, qanswer, choices);
//					
//				return question;
//			} else {
//				Question question = new Question(questioncontent, qtopic1, qtopic2, qnumber, qdifficulty, qanswer);
//				return question;
//			}
//		} catch(Exception e) {
//			return null;
//		}
//	}


//	public Question searchForUserByName(String name) {
//		try {
//			Document doc = initXmlDocument();
//			NodeList listOfNames = doc.getElementsByTagName("name");
//			for (int i = 0; i < listOfNames.getLength(); i++) {
//				Element nameElement = (Element) listOfNames.item(i);
//				if (nameElement.getTextContent().equals(name)) {
//					Element userElement = (Element) nameElement.getParentNode(); // get the parent element of the name
//																					// that matched
//
//					// get the height from the user
//					Element heightElement = (Element) userElement.getElementsByTagName("height").item(0);
//					Double height = Double.valueOf(heightElement.getTextContent()); // dont forget to change types
//
//					// get the adress from the user
//					Element adressElement = (Element) userElement.getElementsByTagName("adress").item(0);
//					String adress = adressElement.getTextContent();
//
//					// get the birthdate from the user
//					Element birthDateElement = (Element) userElement.getElementsByTagName("birthdate").item(0);
//					String birthDateString = birthDateElement.getTextContent();
//					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // format it back to a date
//					Date birthDate = sdf.parse(birthDateString);
//
//					User user = new User(name, height, adress, birthDate); // recreate the user using all the attributes
//					return user;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	public void deleteUser(Question question) {
		Document doc = initXmlDocument(); // get all the data of the xml file in the java object
		Element rootElement = doc.getDocumentElement();
		Element elementToRemove;
		NodeList listOfNames = doc.getElementsByTagName("qnumber"); // get all names
		for (int i = 0; i < listOfNames.getLength(); i++) {
			Element nameElement = (Element) listOfNames.item(i);
			if (nameElement.getTextContent().equals(question.getqnumber())) { // see if the name matches the one of the user
				elementToRemove = (Element) nameElement.getParentNode(); // get the parent element of the name that
																			// matched
				rootElement.removeChild(elementToRemove); // remove the user
			}
		}

		transformXmlDocument(doc);
	}

//	public void updateUser(Question question) {
//		Document doc = initXmlDocument();
//		NodeList listOfNames = doc.getElementsByTagName("name");
//		for (int i = 0; i < listOfNames.getLength(); i++) {
//			Element nameElement = (Element) listOfNames.item(i);
//			if (nameElement.getTextContent().equals(user.getName())) {
//				Element userElement = (Element) nameElement.getParentNode();
//
//				// set the new height from the user
//				Element heightElement = (Element) userElement.getElementsByTagName("height").item(0);
//				heightElement.setTextContent(String.valueOf(user.getHeight()));
//
//				// set the new adress from the user
//				Element adressElement = (Element) userElement.getElementsByTagName("adress").item(0);
//				adressElement.setTextContent(user.getAdress());
//
//				// set the new birthdate from the user
//				Element birthDateElement = (Element) userElement.getElementsByTagName("birthdate").item(0);
//				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // format it back to a date
//				birthDateElement.setTextContent(sdf.format(user.getBirthdate()));
//
//			}
//
//		}
//		transformXmlDocument(doc);
//	}

}
