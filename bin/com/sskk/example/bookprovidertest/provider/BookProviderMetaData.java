package com.sskk.example.bookprovidertest.provider;

import android.net.Uri;



import android.provider.BaseColumns;

public class BookProviderMetaData
{
	public static final String AUTHORITY = "com.sskk.example.bookprovidertest.provider.BookProvider";
	public static final String DATABASE_NAME = "book.db";
	public static final int DATABASE_VERSION =2;
//	public static final String BOOKS_TABLE_NAME = "books";
	private BookProviderMetaData() {}

	//inner class describing BookTable
	public static final class BookTableMetaData implements BaseColumns
	{
		private BookTableMetaData() {}
		public static final String TABLE_NAME = "books";
		//uri and MIME type definitions
		public static final Uri CONTENT_URI =
			Uri.parse("content://" + AUTHORITY + "/books");

		public static final String CONTENT_TYPE =
			"vnd.android.cursor.dir/vnd.androidbook.book";

		public static final String CONTENT_ITEM_TYPE =
			"vnd.android.cursor.item/vnd.androidbook.book";

		public static final String DEFAULT_SORT_ORDER = "modified DESC";

		//Additional Columns start here.
		//string type
		public static final String _ID = "id";
		
		public static final String MANDANT_NAME = "mandantname";
		public static final String MANDANT_URI = "mandanturi";
		public static final String MANDANT_ENABLE= "mandantenable";
	
		
		public static final String GROUP_NAME = "name";
		public static final String GROUP_ID = "groupid";		
		public static final String INGROUP_ID= "idgroup";
		public static final String GROUP_ENABLE = "groupenable";
		
		public static final String WB_NAME= "wbname";
		public static final String WB_URI= "wburi";
		public static final String WB_EANABLE = "wbenable";
		
		public static final String TOTAL_COUNT= "totalcount";
		public static final String UNREAD_COUNT= "unreadcount";
		public static final String PROCESS_OPENNED= "processopenned";
		public static final String STATUS_PROCESS= "statusprocess";
		public static final String EXPAND= "expand";
		public static final String EXPAND_PROCESS= "expandprocess";
		public static final String TIME_DATE= "timedate";
		
		public static final String MODIFIED_DATE = "modified";		
		public static final String CONTENT_DIRECTORY = "syncstate";
	}
}
