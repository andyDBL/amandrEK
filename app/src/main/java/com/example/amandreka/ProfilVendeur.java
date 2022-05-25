package com.example.amandreka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class ProfilVendeur extends AppCompatActivity {

    TextView nom, cabinet, specialisation, localisation;
    String nomVendeur, cabinetVendeur, specialisationVendeur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_vendeur);

        DBHelper dbHelper = new DBHelper(getApplicationContext());

        nom = findViewById(R.id.textViewNameVendeur);
        cabinet = findViewById(R.id.textViewCabinet);
        specialisation = findViewById(R.id.textViewSpecialisation);


        Bundle extras = getIntent().getExtras();
        if (extras != null){
            nomVendeur = extras.getString("nom");
            cabinetVendeur = extras.getString("cabinet");
            specialisationVendeur = extras.getString("specialisation");
        }

        nom.setText(nomVendeur);
        cabinet.setText(cabinetVendeur);
        specialisation.setText(specialisationVendeur);


        dbHelper.close();
    }
}