package com.example.so;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private Spinner spinnerVille;
    private Button buttonSuivant;
    private MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        spinnerVille = findViewById(R.id.spinnerville);
        buttonSuivant = findViewById(R.id.button);
        database = new MyDatabase(this);

        buttonSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCity = spinnerVille.getSelectedItem().toString();

                int numberOfAnnouncements = database.getNombreAnnoncesParVille(selectedCity);
                if (numberOfAnnouncements > 0) {

                    Intent intent = new Intent(MainActivity3.this, MainActivity4.class);

                    intent.putExtra("selected_city", selectedCity);
                    startActivity(intent);
                } else {

                    long result = database.addAnnonce("Titre par défaut", "Catégorie par défaut", "Secteur par défaut", "Contrat par défaut", "Description par défaut", selectedCity);
                    if (result != -1) {

                        showToast("Annonce ajoutée avec succès pour la ville " + selectedCity);

                    } else {
                        showToast("Erreur lors de l'ajout de l'annonce pour la ville " + selectedCity);
                    }
                }
            }
        });
    }

    // Méthode pour afficher un message toast
    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
