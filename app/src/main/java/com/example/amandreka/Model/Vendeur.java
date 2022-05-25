package com.example.amandreka.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.amandreka.DBHelper;

public class Vendeur {
    // TABLE
    public static final String TABLE_NAME = "vendeur";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_CABINET = "cabinet";
    public static final String COLUMN_SPECIALISATION = "specialisation";
    public static final String COLUMN_LOCALISATION = "localisation";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOM + " TEXT UNIQUE,"
                    + COLUMN_CABINET + " TEXT,"
                    + COLUMN_SPECIALISATION + " TEXT,"
                    + COLUMN_LOCALISATION + " TEXT)";

    private int id;
    private String nom;
    private String cabinet;
    private String specialisation;
    private String localisation;

    public Vendeur(int id, String nom, String cabinet, String specialisation, String localisation) {
        this.id = id;
        this.nom = nom;
        this.cabinet = cabinet;
        this.specialisation = specialisation;
        this.localisation = localisation;
    }

    public Vendeur(String nom, String cabinet, String specialisation, String localisation) {
        this.nom = nom;
        this.cabinet = cabinet;
        this.specialisation = specialisation;
        this.localisation = localisation;
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

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public long insererVendeur(@NonNull DBHelper helper) {
        long id = 0;
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues donnees = new ContentValues();
        donnees.put(COLUMN_NOM, this.getNom());
        donnees.put(COLUMN_CABINET, this.getCabinet());
        donnees.put(COLUMN_SPECIALISATION, this.getSpecialisation());
        donnees.put(COLUMN_LOCALISATION, this.getLocalisation());

        try {
            id = db.insert(TABLE_NAME, null, donnees);
        } catch (Exception e) {
            throw e;
        }

        return id;
    }

    public Vendeur findVendeur(@NonNull DBHelper helper, String nomVendeur) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_NOM, COLUMN_CABINET, COLUMN_SPECIALISATION, COLUMN_LOCALISATION},
                COLUMN_NOM + " = ?",
                new String[]{nomVendeur},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        @SuppressLint("Range") Vendeur vendeur = new Vendeur(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NOM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CABINET)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_SPECIALISATION)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_LOCALISATION)));

        cursor.close();

        return vendeur;
    }

}
