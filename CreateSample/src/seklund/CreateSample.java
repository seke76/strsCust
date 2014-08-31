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

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class CreateSample {

	public static String outputPath;
	public static int outputCounter;
	
	public static void main(String[] args) throws ParserConfigurationException {
		
		// Read config file -> outputPath, docNr
		readConfig();
		System.out.println(">>>> path: " + outputPath);
		System.out.println(">>>> counter: " + outputCounter);
		// Do we have any argument?
		// #1 = no of files to created, if missing create only one file
		// while loop call method to create file
		// write the updated docNr to config file

		int sumOutputFiles = 2;	// how many sample files will be created
		for(int x = 1; x <= sumOutputFiles; x++) {
			System.out.println("loop x: " + x + ", sumFiles: " + sumOutputFiles);

			try {
				makeFile(x+outputCounter);
			} catch (FileNotFoundException e) {
				System.out.println("oj oj");
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				System.out.println("oj oj oj");
				e.printStackTrace();
			}
		}
		//writeConfig(x+outputCounter);
	}


	public static boolean makeFile(int x) throws FileNotFoundException, UnsupportedEncodingException {

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
			Document doc;
			doc = dBuilder.parse(fXmlFile);
			outputPath = doc.getElementsByTagName("outputPath").item(0).getTextContent();
			outputCounter = Integer.parseInt(doc.getElementsByTagName("outputCounter").item(0).getTextContent());
			
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void writeConfig(int x) {
		
	}
	

}
