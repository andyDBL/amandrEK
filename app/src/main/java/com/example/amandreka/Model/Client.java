package com.example.amandreka.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.amandreka.DBHelper;

public class Client {
    // TABLE
    public static final String TABLE_NAME = "client";
    // COLUMNS
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_PRENOM = "prenom";
    public static final String COLUMN_DATENAISSANCE = "dateNaissance";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOM + " TEXT UNIQUE,"
                    + COLUMN_PRENOM + " TEXT,"
                    + COLUMN_DATENAISSANCE + " TEXT)";

    private int id;
    private String nom;
    private String prenom;
    private String dateNaissance;

    public Client(int id, String nom, String prenom, String dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public Client(String nom, String prenom, String dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public long inserer(@NonNull DBHelper helper) {
        long id = 0;
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues donnees = new ContentValues();
        donnees.put(COLUMN_NOM, this.getNom());
        donnees.put(COLUMN_PRENOM, this.getPrenom());
        donnees.put(COLUMN_DATENAISSANCE, this.getDateNaissance());

        try {
            id = db.insert(TABLE_NAME, null, donnees);
        } catch (Exception e) {
            throw e;
        }

        return id;
    }

    public Client find(@NonNull DBHelper helper, String nomVendeur) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_NOM, COLUMN_PRENOM, COLUMN_DATENAISSANCE},
                COLUMN_NOM + " = ? ",
                new String[]{nomVendeur},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        @SuppressLint("Range") Client client = new Client(
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_NOM)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PRENOM)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DATENAISSANCE)));

        cursor.close();

        return client;
    }

    public boolean exists(@NonNull DBHelper helper, String nom) {
        return find(helper, nom) != null;
    }
}
