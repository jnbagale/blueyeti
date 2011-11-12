package uk.ac.uwl.blueyeti.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uk.ac.uwl.blueyeti.Information;
import android.util.Log;

public class Parser extends DefaultHandler{
	
	//Information
	private static final String INFO = "info";
	//Title of information
	private static final String INFO_TITLE = "infoName";
	//Description of information
	private static final String INFO_DESC = "infoDescription";
	//Info sender
	private static final String INFO_SENDER = "infoSender";
	private String currentElement;
	private String currentElementValue;
	private Information info;
	
	@Override
    public void startDocument() throws SAXException{
		Log.v("BlueYetiParser", "Beginning XML Document");
       
    }

    @Override
    public void endDocument() throws SAXException{
    	Log.v("BlueYetiParser", "Finished XML Document");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
    	
    	if(localName.equals(INFO))
    		info = new Information();
    	
    	currentElement = localName;
    }
    
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException{
    	if(localName.equals(INFO_TITLE))
    		info.setTitle(currentElementValue);
    	if(localName.equals(INFO_DESC))
    		info.setDescription(currentElementValue);
    	if(localName.equals(INFO_SENDER))
    		info.setSender(currentElementValue);
    	
    }
    
    @Override
    public void characters(char ch[], int start, int length){
    		currentElementValue = new String(ch, start, length);
    }

}
