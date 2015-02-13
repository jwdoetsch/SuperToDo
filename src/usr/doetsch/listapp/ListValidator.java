package usr.doetsch.listapp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ListValidator {
	
	private ListValidator () {
		
	}
	
	public static ListValidator newInstance () {
		return new ListValidator();
	}
	
	public boolean validate (URL schemaPath, URI uri) {
		
		try {
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema s = sf.newSchema(schemaPath);
			
			Validator v = s.newValidator();
			v.validate(new StreamSource(new File(uri)));
		} catch (SAXException| IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	public static void main (String[] args) throws IOException {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setValidating(true);
		dbf.setAttribute(
                "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
		dbf.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaSource",
				ListValidator.class.getResource("resources/test_list.xsd").openStream());
		
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document d = db.parse(ListValidator.class.getResource("resources/test_list.xml").openStream());
			System.out.println(d);
			System.out.println("Passed validation.");
			return;
		} catch (IllegalArgumentException | ParserConfigurationException | IOException | SAXException e) {
			System.out.println("Failed validation: " + e.toString());
			return;
		}

	}

}
