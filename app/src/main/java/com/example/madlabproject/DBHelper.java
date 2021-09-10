package com.example.madlabproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper( Context context) {
        super(context, "Medicine.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table Medicinedetails(name TEXT primary key, date TEXT, time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Medicinedetails ");
    }
    public Boolean addMedicine(String name, String date, String time)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("date",date);
        contentValues.put("time",time);
        long result = DB.insert("Medicinedetails", null, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }



    public Boolean updateMedicine(String name, String date, String time) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("time", time);
        Cursor cursor = DB.rawQuery("SELECT * from Medicinedetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Medicinedetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deleteMedicine(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * from Medicinedetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Medicinedetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Cursor getMedicine() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * from Medicinedetails", null);
        return cursor;
    }
}