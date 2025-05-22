package com.example.formularioapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalhesActivity extends AppCompatActivity {

    TextView detalhesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        detalhesTextView = findViewById(R.id.textViewDetalhes);


        String nome = getIntent().getStringExtra("nome");
        String idade = getIntent().getStringExtra("idade");


        String mensagem = "Nome: " + nome + "\nIdade: " + idade;
        detalhesTextView.setText(mensagem);


        Button voltarButton = findViewById(R.id.buttonVoltar);
        voltarButton.setOnClickListener(v -> finish());
    }
}

