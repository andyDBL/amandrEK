package com.example.amandreka.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.amandreka.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class RendezVous {
    // TABLE
    public static final String TABLE_NAME = "rendez_vous";
    // COLUMNS
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMCLIENT = "nom_lient";
    public static final String COLUMN_NOMVENDEUR = "nom_vendeur";
    public static final String COLUMN_DATE = "date";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOMCLIENT + " TEXT,"
                    + COLUMN_NOMVENDEUR + " TEXT,"
                    + COLUMN_DATE + " TEXT)";

    private int id;
    private String nomClient;
    private String nomVendeur;
    private String date;

    public RendezVous(int id, String nomClient, String nomVendeur, String date) {
        this.id = id;
        this.nomClient = nomClient;
        this.nomVendeur = nomVendeur;
        this.date = date;
    }

    public RendezVous(String nomClient, String nomVendeur, String date) {
        this.nomClient = nomClient;
        this.nomVendeur = nomVendeur;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getNomVendeur() {
        return nomVendeur;
    }

    public void setNomVendeur(String nomVendeur) {
        this.nomVendeur = nomVendeur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long inserer(@NonNull DBHelper helper) {
        long id = 0;
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues donnees = new ContentValues();
        donnees.put(COLUMN_NOMCLIENT, this.getNomClient());
        donnees.put(COLUMN_NOMVENDEUR, this.getNomVendeur());
        donnees.put(COLUMN_DATE, this.getDate());

        try {
            id = db.insert(TABLE_NAME, null, donnees);
        } catch (Exception e) {
            throw e;
        }

        return id;
    }

    public List<RendezVous> find(@NonNull DBHelper helper) {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<RendezVous> listRdv = new ArrayList<>();
        String nomClient = this.getNomClient().compareTo("") == 0 ? "%" : this.getNomClient();
        String nomVendeur = this.getNomVendeur().compareTo("") == 0 ? "%" : this.getNomVendeur();
        String date = this.getDate().compareTo("") == 0 ? "%" : this.getDate();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_NOMCLIENT, COLUMN_NOMVENDEUR, COLUMN_DATE},
                COLUMN_NOMCLIENT + " LIKE ? OR " + COLUMN_NOMVENDEUR + " LIKE ? OR " + COLUMN_DATE + " LIKE ? ",
                new String[]{nomClient, nomVendeur, date},
                null, null, null, null);

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") RendezVous rdv = new RendezVous(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NOMCLIENT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NOMVENDEUR)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                listRdv.add(rdv);
            }
        }

        cursor.close();

        return listRdv;
    }

}
