package com.sskk.example.bookprovidertest.provider;

import java.util.Locale;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

public class NewDBAdapter extends ContentProvider implements BaseColumns {
    public static final String MANDANT_ID = "mandantId";
    public static final String MANDANT_NAME = "mandantName";
    public static final String MANDANT_ENABLE = "mandantEnable";

    public static final String GROUP_ID = "groupId";
    public static final String GROUP_NAME = "groupName";
    public static final String GROUP_ENABLE = "groupEnable";
    public static final String GROUP_EXPAND = "group_expand";
    public static final String GROUP_POSITION = "groupPosition";

    public static final String WB_ID = "wBId";
    public static final String WB_NAME = "wBName";
    public static final String WB_CLICK = "wb_click";
    public static final String WB_INGROUP_ID = "wB_InGroup_ID";
    public static final String WB_ENABLE = "wB_Enable";
    public static final String WB_TOTAL_COUNT = "wB_Total_Count";
    public static final String WB_TIME_DATE = "wB_Time_Date";
    

    public static final String PROCESS_ID = "processId";
    public static final String PROCESS_PID = "process_pid";
    public static final String PROCESS_TYPE_ID = "process_typeid";
    public static final String PROCESS_TYPE_NAME = "process_typename";
    public static final String PROCESS_IS_READ = "process_isread";

    public static final String DOCTYPE_ID = "docId";
    public static final String DOCTYPE_OF_WB = "doc_typeOfWb";
    public static final String DOCTYPE_ID_NAME = "doc_type_IdName";
    public static final String DOCTYPE_COUNT_INDEX = "doc_type_count_index";
    public static final String DOCTYPE_INGROUP_ID = "doc_typeInGroupID";

    public static final String INDEX_ID = "index_Id";
    public static final String INDEX_TYPE_ID = "index_typeId";
    public static final String INDEX_TYPE_VALUE = "index_typeValue";
    public static final String INDEX_OF_WB = "index_typeOfWb";
    public static final String INDEX_INGROUP_ID = "index_typeInGroupID";

    public static final String TRANSLATE_ID = "translateId";
    public static final String TRANSLATE_IN_WB = "translateInWb";
    public static final String TRANSLATE_TYPE = "translateType";
    public static final String TRANSLATE_WORD = "translateWord";
    public static final String TRANSLATE_VALUE = "translateValue";

    public static final String LANGUAGE_NAME = "languageName";
    public static final String LANGUAGE_TYPE = "languageType";
    
    public static final String ITEM_CLICKED = "itemClicked";
    public static final String ITEM_NOT_CONVERTED = "itemNotConverted";
    public static final String ITEM_NUM = "ItemNum";
    private static final String TAG = "NewDBAdapter";
    
    public static final String COMMAND_ID = "commandId";
    public static final String COMMAND_OF_WB = "commandOfWb";
    public static final String COMMAND_NAME = "commandName";

    public static final String DB_NAME = "OXPLORER";
    private static final String DB_TABLE_MANDANT = "Mandants";
    private static final String DB_TABLE_GROUP = "Groups";
    private static final String DB_TABLE_WORKBASKET = "WorkBaskets";
    private static final String DB_TABLE_PROCESS = "Process";
    private static final String DB_TABLE_DOCTYPE = "Doctype";
    private static final String DB_TABLE_INDEXTYPE = "Indextype";
    private static final String DB_TABLE_TRANSLATE = "Translate";
    private static final String DB_TABLE_LANGUAGE = "Language";
    private static final String DB_TABLE_ITEM_CLICK = "ItemClick";
    private static final String DB_TABLE_COMMAND = "Command";
    private static final int DB_VERSION = 2;

    private static final String DB_CREATE_MANDANT = "create table Mandants (mandantId integer primary key autoincrement, "
            + "mandantName text, " + "mandantEnable text);";
    private static final String DB_CREATE_GROUP = "create table Groups (groupId integer primary key autoincrement, "
            + "groupName text, "
            + "groupEnable text, "
            + "group_expand text, "
            + "groupPosition text);";

    private static final String DB_CREATE_WORKBASKET = "create table WorkBaskets (wBId integer primary key autoincrement, "
            + "wBName text, "
            + "wb_click text, "
            + "wB_InGroup_ID text, "
            + "wB_Enable text, "
            + "wB_Total_Count text, " + "wB_Time_Date text);";

    private static final String DB_CREATE_PROCESS = "create table Process (processId integer primary key autoincrement, "
            + "process_pid text, "
            + "process_typeid text, "
            + "process_typename text, " + "process_isread text);";

    private static final String DB_CREATE_DOCTYPE = "create table DocType (docId  integer primary key autoincrement, "
            + "doc_typeOfWb text, "
            + "doc_type_IdName text, "
            + "doc_type_count_index text, " + "doc_typeInGroupID text);";

    private static final String DB_CREATE_INDEXTYPE = "create table IndexType (index_Id  integer primary key autoincrement, "
            + "index_typeId text, "
            + "index_typeValue text, "
            + "index_typeOfWb text, " + "index_typeInGroupID text);";

    private static final String DB_CREATE_TRANSLATE = "create table Translate (translateId integer primary key autoincrement, "
    		+ "translateInWb text, "
    		+ "translateType text, "
            + "translateWord text, "
            + "translateValue text);";

    private static final String DB_CREATE_LANGUAGE = "create table Language (languageId integer primary key autoincrement, "
            + "languageName text, "
            + "languageType text);";
    
    private static final String DB_CREATE_ITEM_CLICKED = "create table ItemClick (itemClickId integer primary key autoincrement, "
            + "itemClicked text, "
            + "itemNotConverted text, "
            + "ItemNum text);";

    private static final String DB_CREATE_COMMAND = "create table Command (commandId integer primary key autoincrement, "
            + "commandOfWb text, "
            + "commandName text);";

    private final Context context;

    private SQLiteDatabase db;
    private DatabaseHelper DBHelper;

    public NewDBAdapter(Context _context) {
        this.context = _context;
    }

    private DatabaseHelper mOpenHelper;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            try {
                Log.w(TAG, "Creating DB.......");
                db.execSQL(DB_CREATE_MANDANT);
                db.execSQL(DB_CREATE_GROUP);
                db.execSQL(DB_CREATE_WORKBASKET);
                db.execSQL(DB_CREATE_PROCESS);
                db.execSQL(DB_CREATE_DOCTYPE);
                db.execSQL(DB_CREATE_INDEXTYPE);
                db.execSQL(DB_CREATE_TRANSLATE);
                db.execSQL(DB_CREATE_LANGUAGE);
                db.execSQL(DB_CREATE_ITEM_CLICKED);
                db.execSQL(DB_CREATE_COMMAND);
            } catch (SQLException e) {
                // TODO: handle exception
                Log.w(TAG, "ERROR Creating DB.......");
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            Log.w(TAG, "Updating DB.......");
            db.execSQL("DROP TABLE IF EXISTS Mandants");
            db.execSQL("DROP TABLE IF EXISTS Groups");
            db.execSQL("DROP TABLE IF EXISTS WorkBaskets");
            db.execSQL("DROP TABLE IF EXISTS Process");
            db.execSQL("DROP TABLE IF EXISTS DocType");
            db.execSQL("DROP TABLE IF EXISTS Translates");
            onCreate(db);
        }

        public DatabaseHelper(Context _context) {
            // TODO Auto-generated constructor stub
            super(_context, DB_NAME, null, DB_VERSION);
        }
    }

    public NewDBAdapter openDB() throws SQLException {
        DBHelper = new DatabaseHelper(context);
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void closeDB() {
        Log.w(TAG, "DB is closing....");
        db.close();
    }

    // -------------------------MANDANT------------------------------
    public long insertNewMandant(String mandantName, String MandantAdded) {
        ContentValues cv_mandant = new ContentValues();
        cv_mandant.put(MANDANT_NAME, mandantName);
        cv_mandant.put(MANDANT_ENABLE, MandantAdded);
        return db.insert(DB_TABLE_MANDANT, null, cv_mandant);
    }

    public boolean deleteMandant(String mandantName) {
        return db.delete(DB_TABLE_MANDANT, MANDANT_NAME + "=?",
                new String[] { mandantName }) > 0;
    }

    public boolean updateMandant(long id, String mandantName,
            String mandantEnable, String email) {
        ContentValues cv_mandant = new ContentValues();
        cv_mandant.put(MANDANT_ID, id);
        cv_mandant.put(MANDANT_NAME, mandantName);
        cv_mandant.put(MANDANT_ENABLE, mandantEnable);
        return db.update(DB_TABLE_MANDANT, cv_mandant, MANDANT_ID + " = " + id,
                null) > 0;
    }

    public Cursor getAllMandant() {
        return db.query(DB_TABLE_MANDANT, new String[] { MANDANT_ID,
                MANDANT_NAME, MANDANT_ENABLE }, null, null, null, null, null);
    }

    public Cursor getMandantById(long id) {
        Cursor c = db.query(DB_TABLE_MANDANT, new String[] { MANDANT_ID,
                MANDANT_NAME, MANDANT_ENABLE }, MANDANT_ID + " = " + id, null,
                null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor getMandantByName(String mandantName) {
        Cursor c = db.query(DB_TABLE_MANDANT, new String[] { MANDANT_ID,
                MANDANT_NAME, MANDANT_ENABLE }, MANDANT_NAME + " = '"
                + mandantName + "'", null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor getMandantByAdded(String mandantAdded) {
        Cursor c = db.query(DB_TABLE_MANDANT, new String[] { MANDANT_ID,
                MANDANT_NAME, MANDANT_ENABLE }, MANDANT_ENABLE + " = '"
                + mandantAdded + "'", null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    // ------------------------GROUP-----------------------------
    public long insertNewGroup(String groupName, String groupEnable,
            String groupExpand, String groupPosition) {
        ContentValues cv_group = new ContentValues();
        cv_group.put(GROUP_NAME, groupName);
        cv_group.put(GROUP_ENABLE, groupEnable);
        cv_group.put(GROUP_EXPAND, groupExpand);
        cv_group.put(GROUP_POSITION, groupPosition);
        return db.insert(DB_TABLE_GROUP, null, cv_group);
    }

    public boolean deleteGroup(String groupName) {
        return db.delete(DB_TABLE_GROUP, GROUP_NAME + "=?",
                new String[] { groupName }) > 0;
    }

    public boolean updateGroup(long id, String groupName, String groupEnable,
            String groupExpand, String groupPosition) {
        ContentValues cv_group = new ContentValues();
        cv_group.put(GROUP_ID, id);
        cv_group.put(GROUP_NAME, groupName);
        cv_group.put(GROUP_ENABLE, groupEnable);
        cv_group.put(GROUP_EXPAND, groupExpand);
        cv_group.put(GROUP_POSITION, groupPosition);
        return db.update(DB_TABLE_GROUP, cv_group, GROUP_ID + " = " + id, null) > 0;
    }

    public boolean changeIDGroup(String OldIDGroup, String NewIDGroup) {
        ContentValues cv_group = new ContentValues();
        cv_group.put(GROUP_POSITION, NewIDGroup);
        return db.update(DB_TABLE_GROUP, cv_group, GROUP_ENABLE + "=?"
                + " and " + GROUP_POSITION + "=?", new String[] { "1",
                OldIDGroup }) > 0;
    }

    public boolean renameGroup(String GroupOldName, String GroupNewName) {
        ContentValues cv_group = new ContentValues();
        cv_group.put(GROUP_NAME, GroupNewName);
        return db.update(DB_TABLE_GROUP, cv_group, GROUP_NAME + "=?",
                new String[] { GroupOldName }) > 0;
    }

    public boolean removeGroup(String groupremoved, int idgroup) {
        ContentValues cv_group = new ContentValues();
        cv_group.put(GROUP_ENABLE, "0");
        cv_group.put(GROUP_POSITION, "1");
        return db.update(DB_TABLE_GROUP, cv_group, GROUP_NAME + "=?" + " and "
                + GROUP_POSITION + "=?", new String[] { groupremoved,
                "" + idgroup }) > 0;

    }

    public boolean renameExpandId(String ExpandOldId, String ExpandNewId,
            String GroupName) {
        ContentValues cv_group = new ContentValues();
        cv_group.put(GROUP_EXPAND, ExpandNewId);
        return db.update(DB_TABLE_GROUP, cv_group, GROUP_NAME + "=?" + " and "
                + GROUP_EXPAND + "=?", new String[] { GroupName, ExpandOldId }) > 0;
    }

    public Cursor getGrouptById(long id) {
        Cursor c = db.query(DB_TABLE_GROUP, new String[] { GROUP_ID,
                GROUP_NAME, GROUP_ENABLE, GROUP_EXPAND, GROUP_POSITION },
                GROUP_ID + " = " + id, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor getGroupByName(String groupName) {
        Cursor c = db.query(DB_TABLE_GROUP, new String[] { GROUP_ID,
                GROUP_NAME, GROUP_ENABLE, GROUP_EXPAND, GROUP_POSITION },
                GROUP_NAME + " = '" + groupName + "'", null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getGroupByEnable(String groupEnable) {
        Cursor c = db.query(DB_TABLE_GROUP, new String[] { GROUP_ID,
                GROUP_NAME, GROUP_ENABLE, GROUP_EXPAND, GROUP_POSITION },
                GROUP_ENABLE + " = '" + groupEnable + "'", null, null, null,
                null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor getGroupByPosition(String groupEnable, String Position) {
        Cursor c = db.query(DB_TABLE_GROUP, new String[] { GROUP_ID,
                GROUP_NAME, GROUP_ENABLE, GROUP_EXPAND, GROUP_POSITION },
                GROUP_ENABLE + "=?" + " and " + GROUP_POSITION + "=?",
                new String[] { "1", Position }, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor getExpand(String groupEnable) {
        Cursor c = db.query(DB_TABLE_GROUP, new String[] { GROUP_ID,
                GROUP_NAME, GROUP_ENABLE, GROUP_EXPAND, GROUP_POSITION },
                GROUP_ENABLE + " = '" + groupEnable + "'", null, null, null,
                null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    // ------------------------WORKBASKET-----------------------------
    public long insertNewWorkBasket(String workBasketName,String inGroupID,
            String wbEnable, String totalCount, String timeDate) {
        ContentValues cv_wb = new ContentValues();
        cv_wb.put(WB_NAME, workBasketName);
        cv_wb.put(WB_CLICK, "0");
        cv_wb.put(WB_INGROUP_ID, inGroupID);
        cv_wb.put(WB_ENABLE, wbEnable);// bo di
        cv_wb.put(WB_TOTAL_COUNT, totalCount);// bo di
        cv_wb.put(WB_TIME_DATE, timeDate);// bo di
        return db.insert(DB_TABLE_WORKBASKET, null, cv_wb);
    }

    public boolean deleteWorkBasket(String workBasketName) {
        return db.delete(DB_TABLE_WORKBASKET, WB_NAME + "=?",
                new String[] { workBasketName }) > 0;
    }

    public boolean updateWorkBasket(long id, String workBasketName,
            String inGroupID, String wbEnable, String totalCount,
            String timeDate) {
        ContentValues cv_wb = new ContentValues();
        cv_wb.put(WB_ID, id);
        cv_wb.put(WB_NAME, workBasketName);
        cv_wb.put(WB_INGROUP_ID, inGroupID);
        cv_wb.put(WB_ENABLE, wbEnable);
        cv_wb.put(WB_TOTAL_COUNT, totalCount);
        cv_wb.put(WB_TIME_DATE, timeDate);
        return db.update(DB_TABLE_WORKBASKET, cv_wb, WB_ID + " = " + id, null) > 0;
    }

    public boolean changeIDWB(String OldIDGroup, String NewIDGroup) {
        ContentValues cv_wb = new ContentValues();
        cv_wb.put(WB_INGROUP_ID, NewIDGroup);
        return db.update(DB_TABLE_WORKBASKET, cv_wb, WB_ENABLE + "=?" + " and "
                + WB_INGROUP_ID + "=?", new String[] { "1", OldIDGroup }) > 0;
    }

    public boolean UpdateCountWB(String Count, String workBasketName) {
        ContentValues cv_wb = new ContentValues();
        cv_wb.put(WB_TOTAL_COUNT, Count);
        return db.update(DB_TABLE_WORKBASKET, cv_wb, WB_NAME + "=?",
                new String[] { workBasketName }) > 0;
    }

    public boolean moveItemToGroup(String Wbname, String IDGroup) {
        ContentValues cv_wb = new ContentValues();
        cv_wb.put(WB_INGROUP_ID, IDGroup);
        return db.update(DB_TABLE_WORKBASKET, cv_wb, WB_ENABLE + "=?" + " and "
                + WB_NAME + "=?", new String[] { "1", Wbname }) > 0;
    }

    public Cursor getWbBy_InGroup_ID(String Wb_InGroup_Id) {
        Cursor c = db.query(DB_TABLE_WORKBASKET, new String[] { WB_ID, WB_NAME,
                WB_INGROUP_ID, WB_ENABLE, WB_TOTAL_COUNT, WB_TIME_DATE },
                WB_ENABLE + "=?" + " and " + WB_INGROUP_ID + "=?",
                new String[] { "1", Wb_InGroup_Id }, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getWbByEnable(String WbEnable) {
        Cursor c = db.query(DB_TABLE_WORKBASKET, new String[] { WB_ID, WB_NAME,
                WB_INGROUP_ID, WB_ENABLE, WB_TOTAL_COUNT, WB_TIME_DATE },
                WB_ENABLE + " = " + WbEnable, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor getWbByName(String wBName) {
        Cursor c = db.query(DB_TABLE_WORKBASKET, new String[] { WB_ID, WB_NAME,
                WB_INGROUP_ID, WB_ENABLE, WB_TOTAL_COUNT, WB_TIME_DATE },
                WB_NAME + " = '" + wBName + "'", null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }
    
    public Cursor getCountClickedWbByName(String wBName) {
        Cursor c = db.query(DB_TABLE_WORKBASKET, new String[] { WB_CLICK, WB_NAME},
                WB_NAME + " = '" + wBName + "'", null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }
    
    public boolean UpdateCountClickedWB(String Count, String workBasketName) {
        ContentValues cv_wb = new ContentValues();
        cv_wb.put(WB_CLICK, Count);
        return db.update(DB_TABLE_WORKBASKET, cv_wb, WB_NAME + "=?",
                new String[] { workBasketName }) > 0;
    }

    // ------------------------PROCESS-----------------------------
    public long insertNewProcess(String process_pid, String Process_TypeId,
            String Process_TypeName, String Process_IsRead) {
        ContentValues cv_process = new ContentValues();
        cv_process.put(PROCESS_PID, process_pid);
        cv_process.put(PROCESS_TYPE_ID, Process_TypeId);
        cv_process.put(PROCESS_TYPE_NAME, Process_TypeName);
        cv_process.put(PROCESS_IS_READ, Process_IsRead);
        return db.insert(DB_TABLE_PROCESS, null, cv_process);
    }

    public boolean changeProcessIsReaded(String Process_PID) {
        ContentValues cv_process = new ContentValues();
        cv_process.put(PROCESS_IS_READ, "1");
        return db.update(DB_TABLE_PROCESS, cv_process, PROCESS_IS_READ + "=?"
                + " and " + PROCESS_PID + "=?",
                new String[] { "0", Process_PID }) > 0;
    }

    public Cursor getProcessByAdded(String Process_PID) {
        Cursor c = db.query(DB_TABLE_PROCESS, new String[] { PROCESS_ID,
                PROCESS_PID, PROCESS_TYPE_ID, PROCESS_TYPE_NAME,
                PROCESS_IS_READ }, PROCESS_PID + "=?" + " and "
                + PROCESS_IS_READ + "=?", new String[] { Process_PID, "1" },
                null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    // ------------------------DOCTYPE-----------------------------

    public long insertNewDocTypeID(String WBID, String DocTypeIdName,
            String CountIndex, String InGroupID) {
        ContentValues cv_doctype = new ContentValues();
        cv_doctype.put(DOCTYPE_OF_WB, WBID);
        cv_doctype.put(DOCTYPE_ID_NAME, DocTypeIdName);
        cv_doctype.put(DOCTYPE_COUNT_INDEX, CountIndex);
        cv_doctype.put(DOCTYPE_INGROUP_ID, InGroupID);
        return db.insert(DB_TABLE_DOCTYPE, null, cv_doctype);
    }

    public Cursor getDocTypeIdNameByGroupIdAndWbID(String WbID, String InGroupID) {
        Cursor c = db.query(DB_TABLE_DOCTYPE, new String[] {
                DOCTYPE_OF_WB, DOCTYPE_ID_NAME, DOCTYPE_COUNT_INDEX,
                DOCTYPE_INGROUP_ID }, DOCTYPE_OF_WB + "=?" + " and "
                + DOCTYPE_INGROUP_ID + "=?", new String[] { WbID, InGroupID },
                null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getDocTypeIdNameByWbID(String WbID) {
        Cursor c = db.query(DB_TABLE_DOCTYPE, new String[] { DOCTYPE_ID,
                DOCTYPE_OF_WB, DOCTYPE_ID_NAME, DOCTYPE_COUNT_INDEX,
                DOCTYPE_INGROUP_ID }, DOCTYPE_OF_WB + "=?",
                new String[] { WbID }, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // ------------------------INDEX-----------------------------

    public long insertNewIndexTypeID(String WbID, String IndexTypeID,
            String IndexTypeValue, String InGroupID) {
        ContentValues cv_indextype = new ContentValues();
        cv_indextype.put(INDEX_OF_WB, WbID);
        cv_indextype.put(INDEX_TYPE_ID, IndexTypeID);
        cv_indextype.put(INDEX_TYPE_VALUE, IndexTypeValue);
        cv_indextype.put(INDEX_INGROUP_ID, InGroupID);
        return db.insert(DB_TABLE_INDEXTYPE, null, cv_indextype);
    }

    public Cursor getIndexTypeIdByGroupIdAndWbID(String WbID, String InGroupID) {
        Cursor c = db.query(DB_TABLE_INDEXTYPE, new String[] {INDEX_OF_WB, INDEX_TYPE_ID,
                INDEX_TYPE_VALUE,INDEX_INGROUP_ID }, INDEX_OF_WB
                + "=?" + " and " + INDEX_INGROUP_ID + "=?", new String[] {
                WbID, InGroupID }, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // ------------------------TRANSLATE-----------------------------

    public long insertNewTranslate(String translate_WB,String translate_type,
            String translate_word, String translate_value) {
        ContentValues cv_translate = new ContentValues();
        cv_translate.put(TRANSLATE_IN_WB, translate_WB);
        cv_translate.put(TRANSLATE_TYPE, translate_type);
        cv_translate.put(TRANSLATE_WORD, translate_word);
        cv_translate.put(TRANSLATE_VALUE, translate_value);
        return db.insert(DB_TABLE_TRANSLATE, null, cv_translate);
    }

    public Cursor getTranslateByTypeAndWordAndWb(String Type, String Word, String Wb) {
        Cursor c = db.query(DB_TABLE_TRANSLATE, new String[] {TRANSLATE_IN_WB, TRANSLATE_TYPE,
                TRANSLATE_WORD, TRANSLATE_VALUE }, TRANSLATE_TYPE + "=?"
                + " and " + TRANSLATE_WORD + "=?"+ " and " + TRANSLATE_IN_WB + "=?", new String[] { Type, Word,Wb },
                null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getTranslateByWbAndType(String WB,String Type) {
        Cursor c = db.query(DB_TABLE_TRANSLATE, new String[] { TRANSLATE_IN_WB,TRANSLATE_TYPE,
                TRANSLATE_VALUE }, TRANSLATE_IN_WB + "=?"+ " and " + TRANSLATE_TYPE + "=?",
                new String[] { WB,Type }, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    // ------------------------LANGUAGE-----------------------------
    public long insertLanguageType(String language_name,String language_type) {
        ContentValues cv_language = new ContentValues();
        cv_language.put(LANGUAGE_NAME, language_name);
        cv_language.put(LANGUAGE_TYPE, language_type);
        return db.insert(DB_TABLE_LANGUAGE, null, cv_language);
    }

    public boolean UpdateLanguageDB(String language) {
        ContentValues cv_language = new ContentValues();
        cv_language.put(LANGUAGE_TYPE, language);
        return db.update(DB_TABLE_LANGUAGE, cv_language, LANGUAGE_NAME + "=?",
                new String[] { "languageName" }) > 0;
    }

    public Cursor getLanguageByID() {
        Cursor c = db.query(DB_TABLE_LANGUAGE, new String[] { LANGUAGE_NAME,
                LANGUAGE_TYPE },LANGUAGE_NAME + "=?", new String[] {"languageName"}, null,
                null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    
    
    
    
    
 // ------------------------ITEMCLICKED----------------------------
    public long insertItemClicked(String item_name,String itemNotConverted) {
        ContentValues cv_itemClicked = new ContentValues();
        cv_itemClicked.put(ITEM_CLICKED, item_name);
        cv_itemClicked.put(ITEM_NOT_CONVERTED, itemNotConverted);
        cv_itemClicked.put(ITEM_NUM, "1");
        return db.insert(DB_TABLE_ITEM_CLICK, null, cv_itemClicked);
    }
    
    public boolean UpdateItemClicked(String item_name,String itemNotConverted) {
        ContentValues cv_itemClicked = new ContentValues();
        cv_itemClicked.put(ITEM_CLICKED, item_name);
        cv_itemClicked.put(ITEM_NOT_CONVERTED, itemNotConverted);
        return db.update(DB_TABLE_ITEM_CLICK, cv_itemClicked, ITEM_NUM + "=?",
                new String[] { "1" }) > 0;
    }
    
    
    public Cursor getItemClickByID() {
        Cursor c = db.query(DB_TABLE_ITEM_CLICK, new String[] { ITEM_CLICKED,
        		ITEM_NOT_CONVERTED,ITEM_NUM },ITEM_NUM + "=?", new String[] {"1"}, null,
                null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    
    
    
    // ------------------------COMMAND----------------------------
    public long insertCommand(String Wb,String CommandName) {
        ContentValues cv_command = new ContentValues();
        cv_command.put(COMMAND_OF_WB, Wb);
        cv_command.put(COMMAND_NAME, CommandName);
        return db.insert(DB_TABLE_COMMAND, null, cv_command);
    }
    
    public Cursor getCommandNameByWb(String Wb) {
        Cursor c = db.query(DB_TABLE_COMMAND, new String[] { COMMAND_OF_WB,
        		COMMAND_NAME},COMMAND_OF_WB + "=?", new String[] {Wb}, null,
                null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    
    
    
    
    
    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        // return false;
        mOpenHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where,
            String[] whereArgs) {
        // TODO Auto-generated method stub
        return 0;

    }

    /*
     * public void deleteAll(){ try{ DBHelper = new DatabaseHelper(context); db
     * = DBHelper.getWritableDatabase(); db.delete(DB_NAME, null, null);
     * 
     * }catch(Exception e){} }
     */

    public void DropDatabase(Context context) {
        context.deleteDatabase(db.getPath());

    }

}