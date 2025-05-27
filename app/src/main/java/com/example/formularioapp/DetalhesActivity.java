package com.example.formularioapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalhesActivity extends AppCompatActivity {

    TextView detalhesTextView;
    Button voltarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        detalhesTextView = findViewById(R.id.textViewDetalhes);
        voltarButton = findViewById(R.id.buttonVoltar);

        // Receber os dados passados pela intent
        String nome = getIntent().getStringExtra("nome");
        String idade = getIntent().getStringExtra("idade");

        // Mostrar os dados no TextView
        detalhesTextView.setText("Nome: " + nome + "\nIdade: " + idade);

        // Configurar o botão para voltar para a tela de cadastro
        voltarButton.setOnClickListener(v -> {
            Intent intent = new Intent(DetalhesActivity.this, MainActivity.class);
            // Para evitar múltiplas instâncias de MainActivity, pode usar finish()
            startActivity(intent);
            finish();
        });
    }
}
