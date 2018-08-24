package com.example.ranjansingh.aryavratproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RANJAN SINGH on 5/8/2018.
 */
@SuppressWarnings("ALL")
public class DatabaseHelper extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Students";

    // Contacts table name
    private static final String TABLE_NAME = "student_table";

    // Contacts Table Columns names
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "AGE";
    private static final String COL_4 = "ADDRESS";
    private static final String COL_5 = "EMAIL";
    private static final String COL_6 = "PASSWORD";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_2 + " TEXT,"
            + COL_3 + " TEXT,"
            + COL_4 + " TEXT,"
            + COL_5 + " TEXT,"
            + COL_6 + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // here to be intialize to check data store or not in database.if we ill use insert then remove this code
        //and we use in insert part
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    // Adding new data
    boolean insertData(String name, String age, String address, String email, String password) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentvalues = new ContentValues();
            contentvalues.put(COL_2, name);
            contentvalues.put(COL_3, age);
            contentvalues.put(COL_4, address);
            contentvalues.put(COL_5, email);
            contentvalues.put(COL_6, password);
            // Inserting Row
            db.insert(TABLE_NAME, null, contentvalues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Here retrive and get data.....
   /* public List<DatabaseModel> getDataFromDB(String email){
        List<DatabaseModel> modelList = new ArrayList<DatabaseModel>();
        String query = "select * from "+TABLE_NAME +" where email="+email ;
        //String query = "select email, password FROM " + TABLE_NAME + " where email = ?" ,+email ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                DatabaseModel model = new DatabaseModel();

                model.setName(cursor.getString(0));
                model.setAge(cursor.getString(1));
                model.setAddress(cursor.getString(2));
                model.setEmail(cursor.getString(3));
                model.setPassword(cursor.getString(4));

                modelList.add(model);
            }while (cursor.moveToNext());
        }


        Log.d("student data", modelList.toString());


        return modelList;
    }*/


    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COL_1
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COL_5 + " = ?" + " AND " + COL_6 + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
    
}




