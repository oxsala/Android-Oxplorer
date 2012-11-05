package com.sskk.example.bookprovidertest.provider;

import java.util.HashMap;




import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.sskk.example.bookprovidertest.provider.BookProviderMetaData.BookTableMetaData;

public class BookProvider extends ContentProvider
{
	private final static String TAG="tag";

	//Create a Projection Map for Columns
	//Projection maps are similar to "as" construct in an sql
	//statement whereby you can rename the
	//columns.
	private static HashMap<String, String> sBooksProjectionMap;
	static
	{
		sBooksProjectionMap = new HashMap<String, String>();
		sBooksProjectionMap.put(BookTableMetaData._ID, BookTableMetaData._ID);
		//name, isbn, author
		sBooksProjectionMap.put(BookTableMetaData.MANDANT_NAME
				, BookTableMetaData.MANDANT_NAME);
		sBooksProjectionMap.put(BookTableMetaData.MANDANT_ENABLE
				, BookTableMetaData.MANDANT_ENABLE);
		sBooksProjectionMap.put(BookTableMetaData.MANDANT_URI
				, BookTableMetaData.MANDANT_URI);
		
		sBooksProjectionMap.put(BookTableMetaData.GROUP_NAME
				, BookTableMetaData.GROUP_NAME);
		sBooksProjectionMap.put(BookTableMetaData.WB_NAME
				, BookTableMetaData.WB_NAME);
		sBooksProjectionMap.put(BookTableMetaData.INGROUP_ID
				, BookTableMetaData.INGROUP_ID);

		sBooksProjectionMap.put(BookTableMetaData.GROUP_ENABLE
				, BookTableMetaData.GROUP_ENABLE);
	/*	sBooksProjectionMap.put(BookTableMetaData.GROUP_STT
				, BookTableMetaData.GROUP_STT);*/
		//created date, modified date
		sBooksProjectionMap.put(BookTableMetaData.WB_EANABLE
				, BookTableMetaData.WB_EANABLE);
		sBooksProjectionMap.put(BookTableMetaData.WB_URI
				, BookTableMetaData.WB_URI);
		sBooksProjectionMap.put(BookTableMetaData.GROUP_ID
				, BookTableMetaData.GROUP_ID);
		sBooksProjectionMap.put(BookTableMetaData.MODIFIED_DATE
				, BookTableMetaData.MODIFIED_DATE);
		sBooksProjectionMap.put(BookTableMetaData.TOTAL_COUNT
				, BookTableMetaData.TOTAL_COUNT);
		sBooksProjectionMap.put(BookTableMetaData.UNREAD_COUNT
				, BookTableMetaData.UNREAD_COUNT);
		sBooksProjectionMap.put(BookTableMetaData.PROCESS_OPENNED
				, BookTableMetaData.PROCESS_OPENNED);
		sBooksProjectionMap.put(BookTableMetaData.STATUS_PROCESS
				, BookTableMetaData.STATUS_PROCESS);
		sBooksProjectionMap.put(BookTableMetaData.EXPAND
				, BookTableMetaData.EXPAND);
		sBooksProjectionMap.put(BookTableMetaData.EXPAND_PROCESS
				, BookTableMetaData.EXPAND_PROCESS);
		sBooksProjectionMap.put(BookTableMetaData.TIME_DATE
				, BookTableMetaData.TIME_DATE);
	}
	//Provide a mechanism to identify all the incoming uri patterns.
	private static final UriMatcher sUriMatcher;
	private static final int INCOMING_BOOK_COLLECTION_URI_INDICATOR = 1;

	private static final int INCOMING_SINGLE_BOOK_URI_INDICATOR = 2;
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(BookProviderMetaData.AUTHORITY
				, "books"
				, INCOMING_BOOK_COLLECTION_URI_INDICATOR);
		sUriMatcher.addURI(BookProviderMetaData.AUTHORITY
				, "books/#",
				INCOMING_SINGLE_BOOK_URI_INDICATOR);
	}
	// Deal with OnCreate call back
	private DatabaseHelper mOpenHelper;

	@Override
	public boolean onCreate() {
		mOpenHelper = new DatabaseHelper(getContext());
		return true;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper 
	{
		DatabaseHelper(Context context) 
		{
			super(context, BookProviderMetaData.DATABASE_NAME, null
					, BookProviderMetaData.DATABASE_VERSION);
		}

		//Create the database
		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			db.execSQL("CREATE TABLE " + BookTableMetaData.TABLE_NAME + " ("
					+ BookProviderMetaData.BookTableMetaData._ID
					+ " INTEGER PRIMARY KEY,"
					+ BookTableMetaData.MANDANT_NAME + " TEXT,"
					+ BookTableMetaData.MANDANT_ENABLE + " TEXT,"
					+ BookTableMetaData.MANDANT_URI + " TEXT,"
					+ BookTableMetaData.GROUP_NAME + " TEXT,"
					+ BookTableMetaData.WB_NAME + " TEXT,"
					+ BookTableMetaData.WB_URI + " TEXT,"
					+ BookTableMetaData.INGROUP_ID + " TEXT,"
					+ BookTableMetaData.GROUP_ID + " TEXT,"
					+ BookTableMetaData.TOTAL_COUNT + " TEXT,"
					+ BookTableMetaData.UNREAD_COUNT + " TEXT,"
					+ BookTableMetaData.PROCESS_OPENNED + " TEXT,"
					+ BookTableMetaData.STATUS_PROCESS + " TEXT,"
					+ BookTableMetaData.EXPAND + " TEXT,"
					+ BookTableMetaData.EXPAND_PROCESS + " TEXT,"
					+ BookTableMetaData.TIME_DATE + " TEXT,"
			/*		+ BookTableMetaData.ITEM4 + " TEXT,"
					+ BookTableMetaData.ITEM5 + " TEXT,"
					+ BookTableMetaData.ITEM6 + " TEXT,"*/
					+ BookTableMetaData.GROUP_ENABLE + " TEXT,"
					//+ BookTableMetaData.GROUP_STT + " TEXT,"
					+ BookTableMetaData.WB_EANABLE + " TEXT,"
					+ BookTableMetaData.MODIFIED_DATE + " INTEGER"
					+ ");");
		}
		//Deal with version changes
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + BookTableMetaData.TABLE_NAME);
			onCreate(db);
		}
	}

	@Override
	public String getType(Uri uri) 
	{
		switch (sUriMatcher.match(uri)) 
		{
			case INCOMING_BOOK_COLLECTION_URI_INDICATOR:
				return BookTableMetaData.CONTENT_TYPE;

			case INCOMING_SINGLE_BOOK_URI_INDICATOR:
				return BookTableMetaData.CONTENT_ITEM_TYPE;

			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection
			, String[] selectionArgs, String sortOrder)
	{
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch (sUriMatcher.match(uri))
		{
			case INCOMING_BOOK_COLLECTION_URI_INDICATOR:
				qb.setTables(BookTableMetaData.TABLE_NAME);
				qb.setProjectionMap(sBooksProjectionMap);
				break;

			case INCOMING_SINGLE_BOOK_URI_INDICATOR:
				qb.setTables(BookTableMetaData.TABLE_NAME);
				qb.setProjectionMap(sBooksProjectionMap);
				qb.appendWhere(BookTableMetaData._ID + "="
						+ uri.getPathSegments().get(1));
				break;

			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}

		// If no sort order is specified use the default
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = BookTableMetaData.DEFAULT_SORT_ORDER;
		} else {
			orderBy = sortOrder;
		}

		// Get the database and run the query
		SQLiteDatabase db =
			mOpenHelper.getReadableDatabase();

		Cursor c = qb.query(db, projection, selection,
				selectionArgs, null, null, orderBy);

		int i = c.getCount();
		// Tell the cursor what uri to watch,
		// so it knows when its source data changes
		c.setNotificationUri(getContext().getContentResolver(), uri);
		
		
		return c;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) 
	{
		// Validate the requested uri
		if (sUriMatcher.match(uri) != INCOMING_BOOK_COLLECTION_URI_INDICATOR) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		Long now = Long.valueOf(System.currentTimeMillis());

		//validate input fields
		// Make sure that the fields are all set
/*		if (values.containsKey(BookTableMetaData.CREATED_DATE) == false) {
			values.put(BookTableMetaData.CREATED_DATE, now);
		}

		if (values.containsKey(BookTableMetaData.MODIFIED_DATE) == false) {
			values.put(BookTableMetaData.MODIFIED_DATE, now);
		}*/
/*		if (values.containsKey(BookTableMetaData.GROUP_NAME) == false) {
			
					values.put(BookTableMetaData.GROUP_NAME, "NO GROUP");
		}*/

/*		if (values.containsKey(BookTableMetaData.GROUP_ENABLE) == false) {
			values.put(BookTableMetaData.GROUP_ENABLE, "Unknown ISBN");
		}
		if (values.containsKey(BookTableMetaData.GROUP_STT) == false) {
			values.put(BookTableMetaData.GROUP_ENABLE, "Unknown Author");
		}*/
		if (values.containsKey(BookTableMetaData.WB_NAME) == false) {
			values.put(BookTableMetaData.WB_NAME, "1");
		}
		if (values.containsKey(BookTableMetaData.GROUP_ENABLE) == false) {
			values.put(BookTableMetaData.GROUP_ENABLE, "0");
		}
		if (values.containsKey(BookTableMetaData.INGROUP_ID) == false) {
			values.put(BookTableMetaData.INGROUP_ID, "1");
		}
		if (values.containsKey(BookTableMetaData.PROCESS_OPENNED) == false) {
			values.put(BookTableMetaData.PROCESS_OPENNED, "0");
		}
		if (values.containsKey(BookTableMetaData.STATUS_PROCESS) == false) {
			values.put(BookTableMetaData.STATUS_PROCESS, "UNREAD");
		}
		if (values.containsKey(BookTableMetaData.EXPAND) == false) {
			values.put(BookTableMetaData.EXPAND, "1");
		}
		if (values.containsKey(BookTableMetaData.EXPAND_PROCESS) == false) {
			values.put(BookTableMetaData.EXPAND_PROCESS, "1");
		}
	/*	if (values.containsKey(BookTableMetaData.ITEM2) == false) {
			values.put(BookTableMetaData.ITEM2, "Unknown Author");
		}*/
/*		if (values.containsKey(BookTableMetaData.ITEM3) == false) {
			values.put(BookTableMetaData.GROUP_ENABLE, "Unknown Author");
		}
		if (values.containsKey(BookTableMetaData.ITEM4) == false) {
			values.put(BookTableMetaData.GROUP_ENABLE, "Unknown Author");
		}
		if (values.containsKey(BookTableMetaData.ITEM5) == false) {
			values.put(BookTableMetaData.GROUP_ENABLE, "Unknown Author");
		}
		if (values.containsKey(BookTableMetaData.ITEM6) == false) {
			values.put(BookTableMetaData.GROUP_ENABLE, "Unknown Author");
		}*/
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long rowId = db.insert(BookTableMetaData.TABLE_NAME
				, BookTableMetaData.GROUP_NAME, values);

		if (rowId > 0) {
			Uri insertedBookUri = ContentUris.withAppendedId(
					BookTableMetaData.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(insertedBookUri, null);
			return insertedBookUri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs)
	{
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri)) 
		{
			case INCOMING_BOOK_COLLECTION_URI_INDICATOR:
				count = db.update(BookTableMetaData.TABLE_NAME,
						values, where, whereArgs );
				break;
			case INCOMING_SINGLE_BOOK_URI_INDICATOR:
				String rowId = uri.getPathSegments().get(1);
				count = db.update(BookTableMetaData.TABLE_NAME
						, values
						, BookTableMetaData._ID + "=" + rowId
						+ (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : "")
						, whereArgs);
				break;
			default:
				throw new IllegalArgumentException("SAI URL ROI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		
		return count;
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) 
	{
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri)) 
		{
			case INCOMING_BOOK_COLLECTION_URI_INDICATOR:
				count = db.delete(BookTableMetaData.TABLE_NAME, where, whereArgs);
				break;
			case INCOMING_SINGLE_BOOK_URI_INDICATOR:
				String rowId = uri.getPathSegments().get(1);
				count = db.delete(BookTableMetaData.TABLE_NAME
						, BookTableMetaData._ID + "=" + rowId
						+ (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : "")
						, whereArgs);
				break;
			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
}
