package com.example.itai.ex_4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Itai on 20/03/2016.
 */
public class AssigmmentsDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "sql.db";

    public AssigmmentsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTERIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELET_ENTRIES);
        onCreate(db);
    }

    public long InsertNew(SQLiteDatabase db,String first, String last, String number){
        ContentValues values = new ContentValues();
        values.put(Constant.Person.FIRST_NAME, first);
        values.put(Constant.Person.LAST_NAME, last);
        values.put(Constant.Person.PHONE_NUM, number);

        long id = db.insert(Constant.Person.TABLE_NAME, null, values);
        return id;
    }

    public Cursor allData(SQLiteDatabase db) {
        return db.query(Constant.Person.TABLE_NAME,null,null,null,null,null,null);
    }

    private static final String SQL_INSERT = "INSERT INTO " + Constant.Person.TABLE_NAME + " VALUES (";
    private static final String SQL_GET_ALL = "SELECT " + Constant.Person.TABLE_NAME;

    private static final String SQL_CREATE_ENTERIES = "CREATE TABLE " + Constant.Person.TABLE_NAME + "(" +
                                                                        Constant.Person._ID + " INTEGER PRIMARY KEY, " +
                                                                        Constant.Person.FIRST_NAME + " TEXT, " +
                                                                        Constant.Person.LAST_NAME + " TEXT, " +
                                                                        Constant.Person.PHONE_NUM + " TEXT " + ");";

    private  static  final String SQL_DELET_ENTRIES = "DROP TABLE IF EXISTS " + Constant.Person.TABLE_NAME + ";";
}