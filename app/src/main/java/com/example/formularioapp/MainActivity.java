package com.example.formularioapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nomeEditText, idadeEditText;
    Button cadastrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomeEditText = findViewById(R.id.editTextNome);
        idadeEditText = findViewById(R.id.editTextIdade);
        cadastrarButton = findViewById(R.id.buttonCadastrar);

        cadastrarButton.setOnClickListener(v -> {
            String nome = nomeEditText.getText().toString();
            String idade = idadeEditText.getText().toString();

            Intent intent = new Intent(MainActivity.this, DetalhesActivity.class);
            intent.putExtra("nome", nome);
            intent.putExtra("idade", idade);
            startActivity(intent);
        });
    }
}
