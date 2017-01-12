package com.example.administrator.ssd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 6/3/2016.
 */
public class SQLiteDataGame extends MySQLiteHelper {
    public SQLiteDataGame(Context context) {
        super(context);
    }

    public void addDataGame() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            for (int i = 0; i < 18; i++) {
                values.put(KEY_Level, i + 1);
                values.put(KEY_Star, 0);
                values.put(KEY_BestMove, 0);
                db.insert(TABLE_DataGame, null, values);
            }
            db.close();
        }
        catch (Exception ex) {}
    }

    public DataGame_DTO getDataGame(int level){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_DataGame, new String[]{
                        KEY_Level,
                        KEY_Star,
                        KEY_BestMove,
                }, KEY_Level + "=?",
                new String[]{String.valueOf(level)}, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        DataGame_DTO d = new DataGame_DTO();
        d.setLevel(Integer.valueOf(cursor.getString(0)));
        d.setStar(Integer.valueOf(cursor.getString(1)));
        d.setBestMove(Integer.valueOf(cursor.getString(2)));
        return d;
    }

    public List<DataGame_DTO> getAllDataGame(){
        List<DataGame_DTO> DataGameList = new ArrayList<DataGame_DTO>();
        String selectQR = "SELECT * FROM " + TABLE_DataGame;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQR, null);
        if(cursor.moveToFirst()){
            do{
                DataGame_DTO d = new DataGame_DTO();
                d.setLevel(Integer.valueOf(cursor.getString(0)));
                d.setStar(Integer.valueOf(cursor.getString(1)));
                d.setBestMove(Integer.valueOf(cursor.getString(2)));

                DataGameList.add(d);
            }
            while(cursor.moveToNext());
        }
        return DataGameList;
    }

    public boolean updateDataGame(int Level, int Star, int BestMove){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Level, Level);
        values.put(KEY_Star, Star);
        values.put(KEY_BestMove, BestMove);

        db.update(TABLE_DataGame, values, KEY_Level + " = ?", new String[]{String.valueOf(Level)});
        return true;
    }
}
