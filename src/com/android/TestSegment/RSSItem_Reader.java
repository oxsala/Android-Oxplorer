package com.android.TestSegment;

public class RSSItem_Reader {
	
	private String x = null;
	private String y = null;
	private String h = null;
	private String w = null;
	private String segmentID = null;

	
	RSSItem_Reader(){
	}
	
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
	void setSegmentID(String value)
	{
		segmentID = value;
	}
	
	public String getX()
	{
		return x;
	}
	public String getY()
	{
		return y;
	}
	public String getH()
	{
		return h;
	}
	public String getW()
	{
		return w;
	}
	String getSegmentID()
	{
		return segmentID;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return x;
	}
	
	

}
