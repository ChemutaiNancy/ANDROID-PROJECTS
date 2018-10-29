package com.chemutai.busticketing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class Database extends SQLiteOpenHelper  {

    public static final String TAG = Database.class.getSimpleName();
    public static final String DB_NAME = "busTicketing.db";
    public static final int DB_VERSION = 1;

    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_MIDDLENAME = "middlename";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_PHONENUMBER = "phonenumber";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CPASSWORD = "confirmpassword";


    public static final String CREATE_TABLE_USERS = "CREATE TABLE "+USER_TABLE+"("
                                +COLUMN_ID+"INTEGER PRIMARY KEY AUTOINCREMENT,"
                                +COLUMN_FIRSTNAME+"TEXT,"
                                +COLUMN_MIDDLENAME+"TEXT,"
                                +COLUMN_LASTNAME+"TEXT,"
                                +COLUMN_PHONENUMBER+"TEXT,"
                                +COLUMN_PASSWORD+"TEXT,"
                                +COLUMN_CPASSWORD+"TEXT);";

    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /*public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public void addUser(String firstName, String middlename, String lastName, String phoneNo, String pass, String confPass){//storing user details in the database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME,firstName);
        values.put(COLUMN_MIDDLENAME, middlename);
        values.put(COLUMN_LASTNAME, lastName);
        values.put(COLUMN_PHONENUMBER, phoneNo);
        values.put(COLUMN_PASSWORD, pass);
        values.put(COLUMN_CPASSWORD, confPass);

        long id = db.insert(USER_TABLE, null,values);
        db.close();

        Log.d(TAG, "addUser: "+id);
    }

    public boolean getUser(String phoneNo, String password){
        String selectQuery = "select * from " + USER_TABLE + "where"
                +COLUMN_PHONENUMBER + "-" +"'"+phoneNo+"'"+"and"+COLUMN_PASSWORD+"-"+"'"+password+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            return true;
        }
            cursor.close();
            db.close();

            return false;

    }
}
