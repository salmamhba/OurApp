package com.example.so;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Button buttonSeConnecter;
    TextView textViewInscriptionLink;
    MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        database = new MyDatabase(this);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSeConnecter = findViewById(R.id.buttonSeConnecter);
        textViewInscriptionLink = findViewById(R.id.textViewInscriptionLink);

        buttonSeConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (authenticateUser(email, password)) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "L'email ou le mot de passe saisi est incorrect!", Toast.LENGTH_SHORT).show();
                    editTextPassword.setText("");
                }
            }
        });

        // Définition un OnClickListener pour le lien d'inscription
        textViewInscriptionLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour ouvrir RegisterActivity (MainActivity2)
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                // Démarrer RegisterActivity (MainActivity2)
                startActivity(intent);
            }
        });
    }

    private boolean authenticateUser(String email, String password) {
        SQLiteDatabase db = database.getReadableDatabase();
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        String[] selectionArgs = {email, password};
        return db.rawQuery(query, selectionArgs).getCount() > 0;
    }
}
