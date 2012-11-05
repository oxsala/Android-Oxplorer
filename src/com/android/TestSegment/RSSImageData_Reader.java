package com.android.TestSegment;

public class RSSImageData_Reader {
	
	private String width = null;
	private String height = null;
	
	RSSImageData_Reader(){
	}
	
	
	void setWidth(String value)
	{
		width = value;
	}
	void setHeight(String value)
	{
		height = value;
	}
	
	public String getWidth()
	{
		return width;
	}
	public String getHeight()
	{
		return height;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return width;
	}
	
	

}
