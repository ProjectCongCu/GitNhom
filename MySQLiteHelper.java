package com.example.administrator.ssd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 6/3/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    //DATABASE NAME
    public static final String DATABASE_NAME = "RollTheBall";
    //PROPERTIES TABLE NAME
    public static final String TABLE_DataGame = "DataGame";
    //CỘT CỦA BẢNG DataGame
    public static final String KEY_Level = "Level";
    public static final String KEY_Star = "Star";
    public static final String KEY_BestMove = "BestMove";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DataGame_TABLE = "CREATE TABLE " + TABLE_DataGame + "("
                + KEY_Level + " INTEGER PRIMARY KEY,"
                + KEY_Star + " INTEGER,"
                + KEY_BestMove + " INTEGER"+ ")";
        db.execSQL(CREATE_DataGame_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DataGame);
        onCreate(db);
    }
}
