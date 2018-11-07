package com.example.patty.andoridlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static final String ACTIVITY_NAME = "ChatDatabaseHelper";
   public static String DATABASE_NAME = "Messages.db";
   public static int VERSION_NUM = 5;
   public static final String KEY_ID = "_id";
   public static final String KEY_MESSAGE = "MESSAGE";
   public static final String TABLE_NAME = "MESSAGES";
    public ChatDatabaseHelper (Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_MESSAGE +" text )");
        Log.i("ChatDatabaseHelper", "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i(ACTIVITY_NAME,"Calling onUpgrade, oldVersion = " + oldVersion + " newVersion = " + newVersion);
    }
}
