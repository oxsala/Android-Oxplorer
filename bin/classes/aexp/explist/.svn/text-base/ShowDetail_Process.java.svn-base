package aexp.explist;




import java.io.IOException;
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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sskk.example.bookprovidertest.provider.BookProviderMetaData;

public class ShowDetail_Process extends ListActivity {
	
	public class RssLoadingTask extends AsyncTask<Void, Void, Void> {

		 @Override
		 protected void onPostExecute(Void result) {
		  // TODO Auto-generated method stub
		  displayRss();
		 }

		 @Override
		 protected void onPreExecute() {
		  // TODO Auto-generated method stub
		  preReadRss();
		 }

		 @Override
		 protected void onProgressUpdate(Void... values) {
		  // TODO Auto-generated method stub
		  //super.onProgressUpdate(values);
			// readRss();
		 }

		 @Override
		 protected Void doInBackground(Void... arg0) {
		  // TODO Auto-generated method stub
				//while(true)
				//{SystemClock.sleep(1000); 
					// readRss();
				//}
		 
		 return null;
		 }

		}
	
TextView feedTitle;
TextView feedDescribtion;
TextView feedPubdate;
TextView feedLink;
String[] arInfo;
private String imageUrl="";
private ImageView imgView;
private String messageLinkProcess;
private String messageLinkWB;
private int readcount;
private int unreadcount;
private String DocumentClicked;
private String LinkProcessClicked;
List<String> ListProcessClicked = new ArrayList();
private List<String> ListLinkDocument = new ArrayList();
private List<String> ListLinkFirstPages = new ArrayList();
private List<String> ListLinkFirstThumnails = new ArrayList();
String smallImage;

Integer[] mThumbIds;
private List<String> ListDocumentTypeInfo = new ArrayList();
private List<String> ListDocumentCreditorInfo = new ArrayList();
private List<String> ListDocumentAmountInfo = new ArrayList();
private List<String> ListDocumentCountpageInfo = new ArrayList();
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

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Bundle bundle = this.getIntent().getExtras();
      CharSequence textMessage = bundle.getCharSequence("LinkProcess");
      messageLinkProcess=""+textMessage;
      CharSequence textMessage1 = bundle.getCharSequence("messageLinkWB");
      messageLinkWB=""+textMessage1;
      startReadRss();
      
  }
  
  
  private void startReadRss(){
	    new RssLoadingTask().execute();
	   }

  private void readRss(){

/*	   getLinkDocumentFromServer(messageLinkProcess);
	      for(int i=0;i<ListLinkDocument.size();i++){			 
	    	  getDocumentInfoForEachLinkDocument(ListLinkDocument.get(i));
	    	  getLinkPageForEachDocument(ListLinkDocument.get(i));
	    	  getFirstLinkThumnailForEachDocument(ListLinkDocument.get(i));
		   } 
	
	      
	      for(int i=0;i<ListLinkFirstThumnails.size();i++){			 
			   String h;
			   h=ListLinkFirstThumnails.get(i).replace("[","-");	
			   //h=LinkProcess.get(i).replace("]","");
			  	arInfo = h.split("-");
			  	ListSmallImage.add(arInfo[0]);
			  	//ListFullImage.add(arInfo[1].replace("]",""));
			   }
	  
	  
 setListAdapter(null);
  
      try {
  URL rssUrl = new URL(messageLinkProcess);
  SAXParserFactory mySAXParserFactory = SAXParserFactory.newInstance();
  SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
  XMLReader myXMLReader = mySAXParser.getXMLReader();
  
 
  InputSource myInputSource = new InputSource(rssUrl.openStream());
  myXMLReader.parse(myInputSource);
 
 
 
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
 }*/

  }

@Override
protected void onListItemClick(ListView l, View v, int position, long id) {
	Object o = this.getListAdapter().getItem(position);
	String keyword = o.toString();
	DocumentClicked= keyword;	
	  Toast.makeText(this,"Going to list Pages for document: "+DocumentClicked, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
	    Bundle bundle= new Bundle();
	   CharSequence TextLinkDocument;
	   TextLinkDocument =""+ListLinkDocument.get(position).toString();
		 bundle.putCharSequence("LinkDocument",TextLinkDocument);
		 CharSequence TextTitleDoc;
		 //bundle.putCharSequence("TitleDoc",TextTitleDoc);
	      CharSequence textmessageLinkProcess;
	      textmessageLinkProcess=messageLinkProcess;
	      bundle.putCharSequence("LinkProcess",textmessageLinkProcess);
	      CharSequence textmessageLinkWB;
	      textmessageLinkWB=messageLinkWB;
	      bundle.putCharSequence("messageLinkWB",textmessageLinkWB);
	  		 
		intent.putExtras(bundle);
	     intent.setClass(ShowDetail_Process.this,showFullImage.class);
	     startActivity(intent);
	     finish();
	   
	     setTitle(""+DocumentClicked);
}

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
 case (0): readRss();
   break;
 default:
   break;
 }

 return true;
}

@Override

public boolean onKeyDown(int keyCode, KeyEvent event) {
switch (keyCode) {
case KeyEvent.KEYCODE_BACK: {
	Intent intent = new Intent();
    Bundle bundle= new Bundle();
   CharSequence textMessage1;
   textMessage1=""+messageLinkWB;;
	 bundle.putCharSequence("messageLinkWB",textMessage1);
	intent.putExtras(bundle);
    startActivity(intent);
    finish();
}

}
return false;
}
private void preReadRss(){

	Toast.makeText(this, "Reading RSS, Please wait.", Toast.LENGTH_LONG).show();
	}
	public void displayRss(){
		readRss();
	}
}

