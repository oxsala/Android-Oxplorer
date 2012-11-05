package com.android.TestSegment;

import java.util.List;
import java.util.Vector;

public class RSSFeed_Reader_Segment {
	private String x = null;
	private String y = null;
	private String h = null;
	private String w = null;
	private String height = null;
	private String width = null;
	private List<RSSItem_Reader> itemList;
	private List<RSSImageData_Reader> ImageDataList;
	RSSFeed_Reader_Segment(){
		itemList = new Vector<RSSItem_Reader>(0);
		//ImageDataList = new Vector<RSSImageData_Reader>(0);
	}
	
	void addItem(RSSItem_Reader item){
		itemList.add(item);
	}
	/*void addImageData(RSSImageData_Reader imagedata){
		ImageDataList.add(imagedata);
	}*/
	RSSItem_Reader getItem(int location){
		return itemList.get(location);
	}
/*	RSSImageData_Reader getImageData(int location){
		return ImageDataList.get(location);
	}*/
	public List<RSSItem_Reader> getList(){
		return itemList;
	}
/*	List<RSSImageData_Reader> getListImageData(){
		return ImageDataList;
	}*/
	void setX(String value)
	{
		x = value;
	}
	void setY(String value)
	{
		y = value;
	}
	void setH(String value)
	{
		h = value;
	}
	void setW(String value)
	{
		w = value;
	}
	/*void setHeight(String value)
	{
		height = value;
	}
	void setWidth(String value)
	{
		width = value;
	}*/
	String getX()
	{
		return x;
	}
	String getY()
	{
		return y;
	}
	String getH()
	{
		return h;
	}
	String getW()
	{
		return w;
	}
	/*String getHeight()
	{
		return height;
	}
	String getWidth()
	{
		return width;
	}*/
}
