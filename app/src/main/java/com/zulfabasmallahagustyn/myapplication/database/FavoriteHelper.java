package com.zulfabasmallahagustyn.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zulfabasmallahagustyn.myapplication.model.user.ModelUser;

import java.util.ArrayList;

import static com.zulfabasmallahagustyn.myapplication.database.DatabaseContract.FavoriteColoumn.ID;
import static com.zulfabasmallahagustyn.myapplication.database.DatabaseContract.FavoriteColoumn.TABLE_NAME;
import static com.zulfabasmallahagustyn.myapplication.database.DatabaseContract.FavoriteColoumn.IMAGE;
import static com.zulfabasmallahagustyn.myapplication.database.DatabaseContract.FavoriteColoumn.TITLE;
import static com.zulfabasmallahagustyn.myapplication.database.DatabaseContract.FavoriteColoumn.URL;

public class FavoriteHelper {

    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper favoriteDbHelper;
    private static volatile FavoriteHelper favoriteHelper;
    private static SQLiteDatabase db;

    public FavoriteHelper(Context context) {
        favoriteDbHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getFavoriteHelper(Context context) {
        if (favoriteHelper == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (favoriteHelper == null) {
                    favoriteHelper = new FavoriteHelper(context);
                }
            }
        }
        return favoriteHelper;
    }

    public void open() throws SQLException {
        db = favoriteDbHelper.getWritableDatabase();
    }

    public void close() {
        favoriteDbHelper.close();
        if (db.isOpen())
            db.close();
    }

    public ArrayList<ModelUser> getAllFavorites() {
        ArrayList<ModelUser> arrayList = new ArrayList<>();
        Cursor cursor = db.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                ID + " ASC",
                null);
        cursor.moveToFirst();
        ModelUser modelUser;
        if (cursor.getCount() > 0) {
            do {
                modelUser = new ModelUser();
                modelUser.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                modelUser.setLogin(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                modelUser.setAvatarUrl(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                modelUser.setHtmlUrl(cursor.getString(cursor.getColumnIndexOrThrow(URL)));
                arrayList.add(modelUser);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void favoriteInsert(ModelUser userResponse) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, userResponse.getId());
        contentValues.put(TITLE, userResponse.getLogin());
        contentValues.put(IMAGE, userResponse.getAvatarUrl());
        contentValues.put(URL, userResponse.getHtmlUrl());

        db.insert(DATABASE_TABLE, null, contentValues);
    }

    public void favoriteDelete(String title) {
        db.delete(TABLE_NAME, TITLE + " = '" + title + "'", null);
    }

    public Cursor cursorFavoriteGet() {
        return db.query(DATABASE_TABLE, null, null,
                null, null, null, ID + " ASC");
    }

    public Cursor cursorFavoriteGetId(String id) {
        return db.query(DATABASE_TABLE, null
                , ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public int favoriteDeleteProvider(String id) {
        return db.delete(TABLE_NAME, ID + "=?", new String[]{id});
    }

    public int favoriteUpdateProvider(String id, ContentValues values) {
        return db.update(DATABASE_TABLE, values, ID + " =?", new String[]{id});
    }

    public long favoriteInsertProvider(ContentValues values) {
        return db.insert(DATABASE_TABLE, null, values);
    }

}
