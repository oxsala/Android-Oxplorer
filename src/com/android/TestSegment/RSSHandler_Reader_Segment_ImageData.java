package com.android.TestSegment;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler_Reader_Segment_ImageData extends DefaultHandler {
	
	final int state_unknown = 0;
	final int state_width = 1;
	final int state_height = 2;
	int currentState = state_unknown;	
	RSSFeed_Reader_Segment feed;
	RSSImageData_Reader imageData;
	
	boolean itemFound = false;
	
	public RSSHandler_Reader_Segment_ImageData(){
	}
	
	public RSSFeed_Reader_Segment getFeed(){
		return feed;
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		feed = new RSSFeed_Reader_Segment();
		imageData = new RSSImageData_Reader();
		
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		if (localName.equalsIgnoreCase("imageData")){
			itemFound = true;
			imageData = new RSSImageData_Reader();
			currentState = state_unknown;
		}
		else if (localName.equalsIgnoreCase("width")){
			currentState = state_width;
		}
		else if (localName.equalsIgnoreCase("height")){
			currentState = state_height;
		}
		else{
			currentState = state_unknown;
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
	 if (localName.equalsIgnoreCase("imageData")){
			//feed.addImageData(imageData);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub		
		String strCharacters = new String(ch,start,length);		
		if (itemFound==true){
		// "item" tag found, it's item's parameter
			switch(currentState){
			case state_width:
				imageData.setWidth(strCharacters);
				break;	
			case state_height:
				imageData.setHeight(strCharacters);
				break;	
			default:
				break;
			}
		}
		else{
		// not "item" tag found, it's feed's parameter
			switch(currentState){
			case state_height:
				feed.setH(strCharacters);
				break;
			case state_width:
				feed.setW(strCharacters);
				break;	
			default:
				break;
			}
		}
		
		currentState = state_unknown;
	}
	

}
