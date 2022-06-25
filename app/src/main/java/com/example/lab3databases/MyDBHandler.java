package com.example.lab3databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "name";
    private static final String COLUMN_PRODUCT_PRICE = "price";
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;

    ArrayList<String> productList;

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table_cmd = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + "INTEGER PRIMARY KEY, " +
                COLUMN_PRODUCT_NAME + " TEXT, " +
                COLUMN_PRODUCT_PRICE + " DOUBLE " + ")";

        db.execSQL(create_table_cmd);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null); // returns "cursor" all products from the table
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(COLUMN_PRODUCT_PRICE, product.getProductPrice());


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<String> findProduct(String productname){
            ArrayList<String> productList = new ArrayList<String>();
            SQLiteDatabase db = this.getWritableDatabase();
            String likeName = productname + "%";
            String query = " SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_NAME +
                    " LIKE "+ "'" +likeName+ "'";

            Cursor cursor = db.rawQuery(query, null);
            if (cursor.getCount() == 0) {
                db.close();
                return productList;
            }
            cursor.moveToFirst();
            productList.add(cursor.getString(1) + " (" +cursor.getString(2)+")");
            cursor.moveToNext();
            while (!cursor.isAfterLast()) {
                productList.add(cursor.getString(1) + " (" +cursor.getString(2)+")");
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
            return productList;

    }

    public ArrayList<String> findProduct2(String productprice){
        ArrayList<String> productList = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_PRICE + " = \""
                + productprice + "\"";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            db.close();
            return productList;
        }
        cursor.moveToFirst();
        productList.add(cursor.getString(1) + " (" +cursor.getString(2)+")");
        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            productList.add(cursor.getString(1) + " (" +cursor.getString(2)+")");
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return productList;

    }

    public ArrayList<String> findProduct3(String productname, String productprice){
        ArrayList<String> productList = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();

        String likeName = productname + "%";
        String query = " SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_NAME +
                " LIKE "+ "'" +likeName+ "'" + " AND " + COLUMN_PRODUCT_PRICE + " = \""
                + productprice + "\"";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            db.close();
            cursor.close();
            return productList;
        }
        cursor.moveToFirst();
        productList.add(cursor.getString(1) + " (" +cursor.getString(2)+")");
        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            productList.add(cursor.getString(1) + " (" +cursor.getString(2)+")");
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return productList;

    }

    public boolean deleteProduct(String productname){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_NAME + " = \""
                + productname + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            db.close();
            cursor.close();
            return false;
        }
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(1);
            db.delete(TABLE_NAME, "name=?", new String[]{idStr});
            cursor.close();
            result = true;
        }
        db.close();

        return result;
    }
}
