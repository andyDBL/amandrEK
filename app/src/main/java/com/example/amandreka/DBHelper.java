package com.example.amandreka;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.amandreka.Model.Client;
import com.example.amandreka.Model.Vendeur;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "AmandrEK.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String rendez_vous = "CREATE TABLE client (id INTEGER primary key, nom TEXT, prenom TEXT, dateNaissance TEXT, localisation TEXT)";
        db.execSQL(Vendeur.CREATE_TABLE);
        db.execSQL(Client.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS vendeur");
        db.execSQL("DROP TABLE IF EXISTS client");
        db.execSQL("DROP TABLE IF EXISTS rendez_vous");
        onCreate(db);
    }
}
