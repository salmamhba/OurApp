// MainActivity4.java
package com.example.so;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    private TextView textViewNumberOfAnnouncements;
    private MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        textViewNumberOfAnnouncements = findViewById(R.id.textViewNumberOfAnnouncements);
        database = new MyDatabase(this);


        String selectedCity = getIntent().getStringExtra("selected_city");


        int numberOfAnnouncements = database.getNombreAnnoncesParVille(selectedCity);

        textViewNumberOfAnnouncements.setText("Nombre d'annonces pour " + selectedCity + ": " + numberOfAnnouncements);
    }
}
