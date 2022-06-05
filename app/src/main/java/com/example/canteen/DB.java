package com.example.canteen;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.*;

public class DB extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;
    public DB( Context context) {
        super(context, "data.db", null, 1);
        sqLiteDatabase=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String table = "login";
//        String query = "Create table IF NOT EXISTS " +table+" (email text primary key,password text,status integer)";
//        sqLiteDatabase.execSQL(query);
//        String query1 = "Create table a (food text primary key,stock integer)";
//        sqLiteDatabase.execSQL(query1);
//        ContentValues food=new ContentValues();
//        food.put("food","idly");
//        food.put("stock","10");
//        sqLiteDatabase.insert("a",null,food);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int checkUserExist(String username, String password){
        String[] columns = {"email"};

        String selection = "email=? and password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = sqLiteDatabase.query("login", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();

        if(count > 0){
            Cursor c=sqLiteDatabase.query("login",null,"email=?",new String[]{username},null,null,null);
            c.moveToFirst();
            @SuppressLint("Range") String l=c.getString(c.getColumnIndex("status"));
            return Integer.parseInt(l);
        } else {
            return 10;
        }
    }

    public String createUser(String username, String password) {
        ContentValues cv=new ContentValues();
        cv.put("email",username);
        cv.put("password",password);
        cv.put("status",0);
        long rowInserted = sqLiteDatabase.insert("login",null,cv);
        if(rowInserted != -1) {
            return "Created Successfully";
        } else {
            return "Create Failed";
        }
    }

    public String placeOrder(int i,int p,int d,int cb,int cfr) {

        String updated = "";
        String result = "No food selected";
        int avail_i,avail_p,avail_d,avail_cb,avail_cfr;
        int rem_i = 0,rem_p = 0,rem_d = 0,rem_cb =0,rem_cfr = 0;
        int flag = 0;
        int total_items=0;
        if(i > 0){
            total_items+=1;
            Cursor c=sqLiteDatabase.query("menu",null,"food=?",new String[]{"idly"},null,null,null);
            c.moveToFirst();
            @SuppressLint("Range") String l=c.getString(c.getColumnIndex("stock"));
            avail_i = Integer.parseInt(l);
            rem_i = avail_i-i;
            if(rem_i < 0){
                updated += "Only "+avail_i+" Idly available.";
            }
            else{
                flag+=1;
            }
        }

        if(p > 0){
            total_items+=1;
            Cursor c=sqLiteDatabase.query("menu",null,"food=?",new String[]{"pongal"},null,null,null);
            c.moveToFirst();
            @SuppressLint("Range") String l=c.getString(c.getColumnIndex("stock"));
            avail_p = Integer.parseInt(l);
            rem_p = avail_p-p;
            if(rem_p < 0){
                updated += "Only "+avail_p+" Pongal available.";
            }
            else{
                flag+=1;
            }
        }

        if(d > 0){
            total_items+=1;
            Cursor c=sqLiteDatabase.query("menu",null,"food=?",new String[]{"dosa"},null,null,null);
            c.moveToFirst();
            @SuppressLint("Range") String l=c.getString(c.getColumnIndex("stock"));
            avail_d = Integer.parseInt(l);
            rem_d = avail_d-d;
            if(rem_d < 0){
                updated += "Only "+avail_d+" Dosa available.";
            }
            else{
                flag+=1;
            }
        }

        if(cb > 0){
            total_items+=1;
            Cursor c=sqLiteDatabase.query("menu",null,"food=?",new String[]{"cb"},null,null,null);
            c.moveToFirst();
            @SuppressLint("Range") String l=c.getString(c.getColumnIndex("stock"));
            avail_cb = Integer.parseInt(l);
            rem_cb = avail_cb-cb;
            if(rem_cb < 0){
                updated += "Only "+avail_cb+" Chicken Biriyani available.";
            }
            else{
                flag+=1;
            }
        }

        if(cfr > 0){
            total_items+=1;
            Cursor c=sqLiteDatabase.query("menu",null,"food=?",new String[]{"cfr"},null,null,null);
            c.moveToFirst();
            @SuppressLint("Range") String l=c.getString(c.getColumnIndex("stock"));
            avail_cfr = Integer.parseInt(l);
            rem_cfr = avail_cfr-cfr;
            if(rem_cfr < 0){
                updated += "Only "+avail_cfr+" Chicken Fried Rice available.";
            }
            else{
                flag+=1;
            }
        }

        if(updated.length()>0){
            return updated;
        }
        else if( (flag == total_items) && total_items>0){
            int temp = 0;
            if (i>0){
                ContentValues cv = new ContentValues();
                cv.put("stock",Integer.toString(rem_i));
                sqLiteDatabase.update("menu", cv, "food = ?", new String[]{"idly"});
                temp+=1;
            }
            if (p>0){
                ContentValues cv = new ContentValues();
                cv.put("stock",Integer.toString(rem_p));
                sqLiteDatabase.update("menu", cv, "food = ?", new String[]{"pongal"});
                temp+=1;
            }
            if (d>0){
                ContentValues cv = new ContentValues();
                cv.put("stock",Integer.toString(rem_d));
                sqLiteDatabase.update("menu", cv, "food = ?", new String[]{"dosa"});
                temp+=1;
            }
            if (cb>0){
                ContentValues cv = new ContentValues();
                cv.put("stock",Integer.toString(rem_cb));
                sqLiteDatabase.update("menu", cv, "food = ?", new String[]{"cb"});
                temp+=1;
            }
            if (cfr>0){
                ContentValues cv = new ContentValues();
                cv.put("stock",Integer.toString(rem_cfr));
                sqLiteDatabase.update("menu", cv, "food = ?", new String[]{"cfr"});
                temp+=1;
            }
            if (temp == flag){
                return "Order Placed Successfully.";
            }
            else {
                return  "Server Down.Order processing failed.";
            }
        }
        else {
            return result;
        }
    }
}

