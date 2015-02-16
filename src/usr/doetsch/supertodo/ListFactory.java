package usr.doetsch.supertodo;

import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ListFactory {

	/*
	 * ErrorHandlerAdapter forwards the associated exceptions
	 * to within the scope of the parent DocumentBuilder.
	 */
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
	
	public List createList (String title) {
		return createList(title, "", false, false, "");
	}
	
	public List createList (String title, String description,
			boolean isUrgent, boolean isMarked, String deadline) {
		return new List(title, description, isUrgent, isMarked, deadline);
	}
	

	/*
	 * Validates and returns a Document representation of
	 * the target XML list document.
	 * 
	 * @param path the URL specifying the target document's path
	 * @return the Document representation of the target document
	 * @throws IOException if the URL can't be resolved
	 * @throws SAXException if the document isn't well-formed and/or
	 * doesn't follow the listapp schema
	 * @throws ParserConfigurationException if the local listapp schema
	 * resources is inaccessible 
	 */
	public List parse (URL path) throws IOException, SAXException, ParserConfigurationException {
	
		Document doc;
		DocumentBuilder docBuilder;
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		
		//configure the factory for validation against the supertodo schema
		docBuilderFactory.setNamespaceAware(true);
		docBuilderFactory.setValidating(true);
		docBuilderFactory.setAttribute(
                "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
		docBuilderFactory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaSource",
				ListFactory.class.getResource("resources/supertodo.xsd").openStream());
		
		docBuilder = docBuilderFactory.newDocumentBuilder();
		docBuilder.setErrorHandler(new ErrorHandlerAdapter());
		doc = docBuilder.parse(path.openStream());
		doc.normalize();
		
		return buildList(doc.getDocumentElement());

	}
	
		
	/*
	 * Returns a List representation of the given XML document Node
	 * 
	 * @param n the Node to represent
	 * @return the List representation
	 */
	private List buildList (Node n) {
		
		List list = createList("");
		NodeList children = n.getChildNodes();
		NamedNodeMap attributes = n.getAttributes();
		
		list.setTitle(attributes.getNamedItem("title") != null ? attributes.getNamedItem("title").getNodeValue() : "");
		list.setDescription(attributes.getNamedItem("desc") != null ? attributes.getNamedItem("desc").getNodeValue() : "");
		list.setDeadline(attributes.getNamedItem("deadline") != null ? attributes.getNamedItem("deadline").getNodeValue() : "");
		list.setUrgent(attributes.getNamedItem("isUrgent") != null ? Boolean.valueOf(attributes.getNamedItem("isUrgent").getNodeValue()) : false);
		list.setMarked(attributes.getNamedItem("isMarked") != null ? Boolean.valueOf(attributes.getNamedItem("isMarked").getNodeValue()) : false);
		
		for (int i = 0; i < children.getLength(); i++) {
			n = children.item(i);

			if (n.getNodeName().equals("list"))
				list.addSubList(buildList(n));
		}
				
		return list;
	}
	
	
	public static void main (String[] args) {
		
		ListFactory lb = new ListFactory();
		try {
			List l = lb.parse(ListFactory.class.getResource("resources/test_list.xml"));
			System.out.println(l);
			
			ListReader r = new ListReader();
			
			System.out.println(r.getListCount(l));
			System.out.println(r.getItemCount(l));
			
		
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (SAXException e) {
			e.printStackTrace();
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}
}
