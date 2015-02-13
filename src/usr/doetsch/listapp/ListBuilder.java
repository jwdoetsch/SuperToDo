package usr.doetsch.listapp;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ListBuilder {

	public List createList (String title) {
		return createList(title, "", false, false);
	}
	
	public List createList (String title, String description, boolean isUrgent, boolean isMarked) {
		return new List(title, description, isUrgent, isMarked);
	}
	
	public List parseList (URL path) throws SAXException, IOException, ParserConfigurationException {
		List list = createList("");
		Element headElement;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource",
				ListBuilder.class.getResource("resources/newlist.xsd").toString());
		
		Document xmlDoc = factory.newDocumentBuilder().parse(path.toString());
		
		xmlDoc.getDocumentElement().normalize();
		
		headElement = xmlDoc.getDocumentElement();
		
		list = buildList(headElement);
		
		return list;
	}
	
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
	
	public static void main (String[] args) throws SAXException, IOException, ParserConfigurationException {
		ListBuilder lb = new ListBuilder();
		ListReader lr = new ListReader();
		System.out.println(lr.printList(lb.parseList(ListBuilder.class.getResource("resources/newlist.xml"))));
		
	}
	
}
