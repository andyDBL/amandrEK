package com.example.amandreka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button vendeur, client,connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vendeur = findViewById(R.id.buttonVendor);
        client = findViewById(R.id.ButtonClient);
        connexion= findViewById(R.id.buttonConnexion);
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent conn=new Intent(getApplicationContext(),ConnexionActivity.class);
                startActivity(conn);
            }
        });

        vendeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVendeur = new Intent(getApplicationContext(), VendeurActivity.class);
                startActivity(intentVendeur);
            }
        });

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentClient = new Intent(getApplicationContext(), ClientActivity.class);
                startActivity(intentClient);
            }
        });
    }
}