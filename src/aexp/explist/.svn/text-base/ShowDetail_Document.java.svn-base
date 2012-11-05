package aexp.explist;




import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.TestSegment.RSSItem_Reader;
import com.sskk.example.bookprovidertest.provider.BookProviderMetaData;

public class ShowDetail_Document extends ListActivity {
TextView feedTitle;
TextView feedDescribtion;
TextView feedPubdate;
TextView feedLink;
String[] arInfo;
private String imageUrl="";
private ImageView imgView;
private String message;
private int readcount;
private int unreadcount;
private String ProcessClicked;
List<String> ListProcessClicked = new ArrayList();
private List<String> LinkImage = new ArrayList();
String smallImage;

Integer[] mThumbIds;
private List<String> ListSmallImage = new ArrayList();
private List<String> ListFullImage = new ArrayList();
private String[] WBenableProj =
	new String[] { BookProviderMetaData.BookTableMetaData._ID
		,BookProviderMetaData.BookTableMetaData.WB_NAME
		,BookProviderMetaData.BookTableMetaData.WB_URI
		,BookProviderMetaData.BookTableMetaData.WB_EANABLE
		,BookProviderMetaData.BookTableMetaData.TOTAL_COUNT
		,BookProviderMetaData.BookTableMetaData.UNREAD_COUNT
		,BookProviderMetaData.BookTableMetaData.INGROUP_ID  };

private String[] ProcessClickedProj =
	new String[] { BookProviderMetaData.BookTableMetaData._ID,
		BookProviderMetaData.BookTableMetaData.PROCESS_OPENNED};
public class MyCustomAdapter extends ArrayAdapter<RSSItem_Reader> {

 public MyCustomAdapter(Context context, int textViewResourceId,
   List<RSSItem_Reader> list) {
  super(context, textViewResourceId, list);
 }

 /*@Override
 public View getView(int position, View convertView, ViewGroup parent) {
  // TODO Auto-generated method stub
  //return super.getView(position, convertView, parent);
	
  View row = convertView;
 
  if(row==null){
   LayoutInflater inflater=getLayoutInflater();
   row=inflater.inflate(R.layout.rowreader, parent, false);
  }
 
  TextView listTitle=(TextView)row.findViewById(R.id.listtitle);
  listTitle.setText(myRssFeed.getList().get(position).getTitle());
  TextView listPubdate=(TextView)row.findViewById(R.id.listpubdate);
  listPubdate.setText(myRssFeed.getList().get(position).getDescription());
  ImageView imageView = (ImageView) row.findViewById(R.id.icon);
  URL myFileUrl =null;         
  for(int i=0;i<ListSmallImage.size();i++){			
	  Bitmap[] bmImg=new Bitmap[ListSmallImage.size()];  
  try {
	  //if (position == i){
		  myFileUrl= new URL(ListSmallImage.get(i));
	  //}
	  
       HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
       conn.setDoInput(true);
       conn.connect();
       int length = conn.getContentLength();
       InputStream is = conn.getInputStream();
       
       bmImg[i] = BitmapFactory.decodeStream(is);
   if (position == i){
    		  imageView.setImageBitmap(bmImg[i]);
    	  }
 
       //imView.setImageBitmap(bmImg);
  } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
  }
  }
  
   
      
 
  return row;
 }*/
}
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      //setContentView(R.layout.mainreader);
      UpdateProcessClickedInDb();
      Bitmap[] bmImg=new Bitmap[2];
      Bundle bundle = this.getIntent().getExtras();
      CharSequence textMessage = bundle.getCharSequence("message");
      message=""+textMessage;
    //  getMandantListFromServer(message);
      
	   for(int i=0;i<LinkImage.size();i++){			 
		   String h;
		   h=LinkImage.get(i).replace("[","-");	
		   //h=LinkImage.get(i).replace("]","");
		  	arInfo = h.split("-");
		  	ListSmallImage.add(arInfo[0]);
		  	ListFullImage.add(arInfo[1].replace("]",""));
		   } 
	  	Toast.makeText(this,""+ListFullImage.get(0), Toast.LENGTH_SHORT).show();
      //readRss();
  }

/*  private void readRss(){

 setListAdapter(null);
  
      try {
  URL rssUrl = new URL(message);
  SAXParserFactory mySAXParserFactory = SAXParserFactory.newInstance();
  SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
  XMLReader myXMLReader = mySAXParser.getXMLReader();
  RSSHandler_Reader myRSSHandler = new RSSHandler_Reader();
  myXMLReader.setContentHandler(myRSSHandler);
  InputSource myInputSource = new InputSource(rssUrl.openStream());
  myXMLReader.parse(myInputSource);
 
  myRssFeed = myRSSHandler.getFeed();
 
 } catch (MalformedURLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 } catch (ParserConfigurationException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 } catch (SAXException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 } catch (IOException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 }

 if (myRssFeed!=null)
 {
  Calendar c = Calendar.getInstance();
     String strCurrentTiime =  "\n(Time of Reading - "
           + c.get(Calendar.HOUR_OF_DAY)
           + " : "
           + c.get(Calendar.MINUTE) + ")\n";

  feedTitle.setText(myRssFeed.getTitle() + strCurrentTiime);
  feedDescribtion.setText(myRssFeed.getDescription());
  feedPubdate.setText(myRssFeed.getPubdate());
  feedLink.setText(myRssFeed.getLink());
 
  MyCustomAdapter adapter =
   new MyCustomAdapter(this, R.layout.rowreader, myRssFeed.getList());
  setListAdapter(adapter);
 
 }
  }*/

@Override
protected void onListItemClick(ListView l, View v, int position, long id) {
	Object o = this.getListAdapter().getItem(position);
	String keyword = o.toString();
	ProcessClicked= keyword;	
	if(ListProcessClicked.contains(ProcessClicked)){
		Toast.makeText(this, ProcessClicked, Toast.LENGTH_LONG).show();
		Intent intent = new Intent();
	    Bundle bundle= new Bundle();
	   CharSequence textMessage;
	   CharSequence textMessage1;
	   textMessage1=message;
	    textMessage =""+ListFullImage.get(position).toString();
		 bundle.putCharSequence("fullimage",textMessage);
		 bundle.putCharSequence("message",textMessage1);
		intent.putExtras(bundle);
	     intent.setClass(ShowDetail_Document.this,showFullImage.class);
	     startActivity(intent);
	     finish();
	     setTitle("Showing Full Image");
	}
	else{
		addProcessClickedtoDB(ProcessClicked);
		UpdateProcessClickedInDb();
		UpdateCountInDb();
		Toast.makeText(this, ProcessClicked, Toast.LENGTH_LONG).show();
		Intent intent = new Intent();
	    Bundle bundle= new Bundle();
	   CharSequence textMessage;
	   CharSequence textMessage1;
	   textMessage1=message;
	    textMessage =""+ListFullImage.get(position).toString();
		 bundle.putCharSequence("fullimage",textMessage);
		 bundle.putCharSequence("message",textMessage1);
		intent.putExtras(bundle);
	     intent.setClass(ShowDetail_Document.this,showFullImage.class);
	     startActivity(intent);
	     finish();
	     setTitle("Showing Full Image");
	}


}

/*
public void getMandantListFromServer(String uri){
	try{
		URL rssUrl = new URL(uri);
		  SAXParserFactory mySAXParserFactory = SAXParserFactory.newInstance();
		  SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
		  XMLReader myXMLReader = mySAXParser.getXMLReader();
		  RSSHandler_Reader myRSSHandler = new RSSHandler_Reader();
		  myXMLReader.setContentHandler(myRSSHandler);
		  InputSource myInputSource = new InputSource(rssUrl.openStream());
		  myXMLReader.parse(myInputSource);    		 
		  myRssFeed2 = myRSSHandler.getFeed();
		  int x = myRssFeed2.getList().size();		   
		   for(int i=0;i<x;i++){			 
			   LinkImage.add(myRssFeed2.getList().get(i).getLink());
		   } 
		   
	} catch (MalformedURLException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		 } catch (ParserConfigurationException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		 } catch (SAXException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		 } catch (IOException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		 }
		   
}*/


@Override
public boolean onCreateOptionsMenu(Menu menu) {
 // TODO Auto-generated method stub
 menu.add(0, 0, 0, "Reload");
 return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
 // TODO Auto-generated method stub
 switch(item.getItemId()){
 case (0): 
	 //readRss();
   break;
 default:
   break;
 }

 return true;
}

@Override

public boolean onKeyDown(int keyCode, KeyEvent event) {
switch (keyCode) {
case KeyEvent.KEYCODE_DPAD_LEFT: {

break;
}                           
case KeyEvent.KEYCODE_DPAD_RIGHT: {

     
	
break;
}
case KeyEvent.KEYCODE_DPAD_UP: {

	//openDialog2();
break;
}
case KeyEvent.KEYCODE_DPAD_DOWN: {

break;
}
/*	case KeyEvent.KEYCODE_DPAD_CENTER: {

break;
}*/
case KeyEvent.KEYCODE_BACK: {
	//finish();
	Intent intent = new Intent();
	intent.setClass(ShowDetail_Document.this,aexp.explist.ANDROID_RSS_READER.class);
    startActivity(intent);
    finish();
}

}
return false;
}


public void CountReadinDB() {
	
    		ContentResolver contentResolver = getContentResolver(); 
    		Cursor cursor = contentResolver.query(BookProviderMetaData.BookTableMetaData.CONTENT_URI,
    				WBenableProj,"wbenable = ? AND wburi = ?",
    				new String[] { "1",message}, null);	    		
    		int numberOfWBenable= cursor.getCount();
   		 for ( int i = 0; i <numberOfWBenable ; i++ ){
				cursor.moveToPosition(i );
				int ColumnReadCount = cursor.getColumnIndex(BookProviderMetaData.BookTableMetaData.TOTAL_COUNT);
				String nameReadCount = cursor.getString(ColumnReadCount);							
				readcount=Integer.parseInt(nameReadCount);
				
				int ColumnUnReadCount = cursor.getColumnIndex(BookProviderMetaData.BookTableMetaData.UNREAD_COUNT);
				String nameUnReadCount= cursor.getString(ColumnUnReadCount);							
				unreadcount=Integer.parseInt(nameUnReadCount);

		 }	        
}

public void UpdateCountInDb(){
	CountReadinDB();
	int read=readcount+1;
	int unread=unreadcount-1;
	ContentResolver contentResolver = getContentResolver(); 
	ContentValues values1 = new ContentValues();
	ContentValues values2 = new ContentValues();
	values1.put(BookProviderMetaData.BookTableMetaData.TOTAL_COUNT,""+read);	
	values2.put(BookProviderMetaData.BookTableMetaData.UNREAD_COUNT,""+unread);	
	int count1 = contentResolver.update(BookProviderMetaData.BookTableMetaData.CONTENT_URI, 
			values1,"readcount = ? AND wburi = ?"
			,new String[] {""+readcount,message});  
	int count2 = contentResolver.update(BookProviderMetaData.BookTableMetaData.CONTENT_URI, 
			values2,"unreadcount = ? AND wburi = ?"
			,new String[] {""+unreadcount,message});  
	//Toast.makeText(this,"Rename Successfully !", Toast.LENGTH_SHORT).show();

}

public void addProcessClickedtoDB(String ProcessClicked) {
	ContentResolver contentResolver = getContentResolver(); 
	ContentValues values = new ContentValues();
	values.put(BookProviderMetaData.BookTableMetaData.PROCESS_OPENNED,ProcessClicked);
	Uri uri = contentResolver.insert(BookProviderMetaData.BookTableMetaData.CONTENT_URI, values);				        		      	     

}

public void UpdateProcessClickedInDb() {
	ListProcessClicked.clear();
  
	ContentResolver contentResolver = getContentResolver(); 
	Cursor cursor1 = contentResolver.query(BookProviderMetaData.BookTableMetaData.CONTENT_URI,
			ProcessClickedProj,null,null, null);    		
	int x1= cursor1.getCount();
	
	 for ( int i = 0; i <x1 ; i++ ){
		
		cursor1.moveToPosition(i );
		int ColumnProcessClicked = cursor1.getColumnIndex(BookProviderMetaData.BookTableMetaData.PROCESS_OPENNED);
		String ProcessClicked = cursor1.getString(ColumnProcessClicked);							
		ListProcessClicked.add(ProcessClicked);
		
 }	
}


}

