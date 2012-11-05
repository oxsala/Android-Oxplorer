package com.android.TestSegment;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler_Reader_Segment extends DefaultHandler {
	
	final int state_unknown = 0;
	final int state_x = 1;
	final int state_y = 2;
	final int state_h = 3;
	final int state_w = 4;
	final int state_segmentID = 5;
	int currentState = state_unknown;
	
	RSSFeed_Reader_Segment feed;
	RSSItem_Reader item;
	boolean itemFound = false;
	
	public RSSHandler_Reader_Segment(){
	}
	
	public RSSFeed_Reader_Segment getFeed(){
		return feed;
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		feed = new RSSFeed_Reader_Segment();
		item = new RSSItem_Reader();
		
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub		
		if (localName.equalsIgnoreCase("segment")){
			itemFound = true;
			item = new RSSItem_Reader();
			currentState = state_unknown;
		}
		else if (localName.equalsIgnoreCase("x")){
			currentState = state_x;
		}
		else if (localName.equalsIgnoreCase("y")){
			currentState = state_y;
		}
		else if (localName.equalsIgnoreCase("h")){
			currentState = state_h;
		}
		else if (localName.equalsIgnoreCase("w")){
			currentState = state_w;
		}
		else if (localName.equalsIgnoreCase("segmentID")){
			currentState = state_segmentID;
		}
		else{
			currentState = state_unknown;
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		if (localName.equalsIgnoreCase("segment")){
			feed.addItem(item);
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
			case state_x:
				item.setX(strCharacters);
				break;
			case state_y:
				item.setY(strCharacters);
				break;
			case state_h:
				item.setH(strCharacters);
				break;
			case state_w:
				item.setW(strCharacters);
				break;	
			case state_segmentID:
				item.setSegmentID(strCharacters);
				break;	
			default:
				break;
			}
		}
		else{
		// not "item" tag found, it's feed's parameter
			switch(currentState){
			case state_x:
				feed.setX(strCharacters);
				break;
			case state_y:
				feed.setY(strCharacters);
				break;
			case state_h:
				feed.setH(strCharacters);
				break;
			case state_w:
				feed.setW(strCharacters);
				break;	
			default:
				break;
			}
		}
		
		currentState = state_unknown;
	}
	

}
