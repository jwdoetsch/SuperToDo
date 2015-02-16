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
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ListValidator {
	
	private class ErrorHandlerAdapter implements ErrorHandler {

		@Override
		public void error(SAXParseException exception) throws SAXException {
			throw new SAXException(exception);
		}

		@Override
		public void fatalError(SAXParseException exception) throws SAXException {
			throw new SAXException(exception);
		}

		@Override
		public void warning(SAXParseException exception) throws SAXException {
			throw new SAXException(exception);
		}
		
	}
	

	/**
	 * Validates and returns a Document representation of
	 * the target XML list document.
	 * 
	 * 
	 * @param docPath
	 * @return
	 * @throws IOException
	 */
	public Document validate (URL docPath) throws IOException {
	
		Document doc;
		DocumentBuilder docBuilder;
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		
		docBuilderFactory.setNamespaceAware(true);
		docBuilderFactory.setValidating(true);
		docBuilderFactory.setAttribute(
                "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
		docBuilderFactory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaSource",
				ListValidator.class.getResource("resources/listSchema.xsd").openStream());
		
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			docBuilder.setErrorHandler(new ErrorHandlerAdapter());
			doc = docBuilder.parse(docPath.openStream());
			return doc;
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
	
	
	
	public static void main (String[] args) throws IOException {
		
		ListValidator validator = new ListValidator();
		
		System.out.println(validator.validate(ListValidator.class.getResource("resources/test_list.xml")));
		
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		dbf.setNamespaceAware(true);
//		dbf.setValidating(true);
//		dbf.setAttribute(
//                "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
//                "http://www.w3.org/2001/XMLSchema");
//		dbf.setAttribute(
//				"http://java.sun.com/xml/jaxp/properties/schemaSource",
//				ListValidator.class.getResource("resources/listSchema.xsd").openStream());
//		
//		DocumentBuilder db;
//		try {
//			db = dbf.newDocumentBuilder();
//			db.setErrorHandler(new ErrorHandler() {
//
//				@Override
//				public void error(SAXParseException arg0) throws SAXException {
//					throw new SAXException(arg0);
//				}
//
//				@Override
//				public void fatalError(SAXParseException arg0)
//						throws SAXException {
//					throw new SAXException(arg0);
//					
//				}
//
//				@Override
//				public void warning(SAXParseException arg0) throws SAXException {
//					throw new SAXException(arg0);
//				
//				}
//				
//			});
//			
//			Document d = db.parse(ListValidator.class.getResource("resources/test_list.xml").openStream());
//			
//			System.out.println(d);
//			System.out.println("Passed validation.");
//			return;
//		} catch (IllegalArgumentException | ParserConfigurationException | IOException | SAXException e) {
//			//System.out.println("Failed validation: " + e.toString() + e.);
//			if (e instanceof SAXException) {
//				System.out.println("Failed: " + e.getMessage());
//			}
//			return;
//		}

	}

}
