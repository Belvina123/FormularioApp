package com.example.formularioapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        // Ao clicar no botão, valida, envia dados e puxa da API
        cadastrarButton.setOnClickListener(v -> {
            String nome = nomeEditText.getText().toString().trim();
            String idade = idadeEditText.getText().toString().trim();

            if (nome.isEmpty()) {
                nomeEditText.setError("Por favor, insira o nome");
                nomeEditText.requestFocus();
                return;
            }

            if (idade.isEmpty()) {
                idadeEditText.setError("Por favor, insira a idade");
                idadeEditText.requestFocus();
                return;
            }

            // Envia para outra Activity
            Intent intent = new Intent(MainActivity.this, DetalhesActivity.class);
            intent.putExtra("nome", nome);
            intent.putExtra("idade", idade);
            startActivity(intent);

            // Faz a chamada à API pública
            fetchUsuariosDaAPI();
        });
    }

    private void fetchUsuariosDaAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);

        api.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body();
                    for (User u : users) {
                        Log.d("API_USER", "Nome: " + u.name + ", Email: " + u.email);
                    }

                    Toast.makeText(MainActivity.this,
                            "Usuários carregados com sucesso!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Erro ao obter utilizadores", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Falha na API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
