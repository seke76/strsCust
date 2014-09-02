package seklund;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class CreateSample {

	public static String outputPath;
	public static int outputCounter;

	public static void main(String[] args) throws ParserConfigurationException {

		// Read config file -> outputPath, docNr
		readConfig();
		System.out.println(">>>> counter: " + outputCounter);
		// Do we have any argument?
		// #1 = no of files to created, if missing create only one file
		// while loop call method to create file
		// write the updated docNr to config file

		int sumOutputFiles = 2;	// how many sample files will be created
		int x;
		for(x = 1; x <= sumOutputFiles; x++) {
			System.out.println("loop x: " + x + ", sumFiles: " + sumOutputFiles);

			try {
				makeFile(x+outputCounter);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		System.out.println(">>>> after loop x: "+x);
		writeConfig(String.valueOf(x+outputCounter));
	}


	public static boolean makeFile(int x) throws FileNotFoundException, UnsupportedEncodingException {

		System.out.println("MakeFile x:" +x);
		UUID uuidFileName = UUID.randomUUID();

		PrintWriter writer = new PrintWriter(uuidFileName+".txt", "UTF-8");
		writer.println("HEADER|"+x+"date");
		writer.println("SELLER|CompanyName|Country|Department");
		writer.println("BUYER|CustomerName");
		writer.close();

		return true;
	}

	public static void readConfig() throws ParserConfigurationException {

		try {
			File fXmlFile = new File("Config/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			outputPath = doc.getElementsByTagName("outputPath").item(0).getTextContent();
			outputCounter = Integer.parseInt(doc.getElementsByTagName("outputCounter").item(0).getTextContent());


		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void writeConfig(String x) {

		System.out.println("skriva xmlfilen x:" + x);
		try {
			String filepath = "Config/config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			// Get the outputCounter element by tag name directly
			Node counterElem = (Node)doc.getElementsByTagName("outputCounter").item(0);

			// update outputCounter attribute
			counterElem.setTextContent(x);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}


}
