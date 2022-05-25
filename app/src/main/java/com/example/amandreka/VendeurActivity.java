package com.example.amandreka;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amandreka.Model.Vendeur;

public class VendeurActivity extends AppCompatActivity {

    EditText nom, cabinet, specialisation, localisation;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendeur);

        DBHelper dbHelper = new DBHelper(getApplicationContext());
        nom = findViewById(R.id.editTextName);
        cabinet = findViewById(R.id.editTextCabinet);
        specialisation = findViewById(R.id.editTextSpecialisation);
        localisation = findViewById(R.id.editTextLocation);
        signin = findViewById(R.id.buttonVendorSignIn);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vendeur vendeur = new Vendeur(nom.getText().toString(), cabinet.getText().toString(), specialisation.getText().toString(), localisation.getText().toString());
                try {
                    vendeur.insererVendeur(dbHelper);
                } catch (Exception e) {
                    Toast.makeText(VendeurActivity.this, "Nom de vendeur déjà existant", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dbHelper.close();
    }
}