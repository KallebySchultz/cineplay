package com.example.cineplay_novo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaCadastro extends AppCompatActivity {
    private EditText edtNome, edtGmail, edtSenha;
    private Button btnCadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.idSenhaCadastro), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Referência aos componentes
        edtNome = findViewById(R.id.idNomeCadastro);
        edtGmail = findViewById(R.id.idEmailCadastro);
        edtSenha = findViewById(R.id.idSenhaCadastro);
        btnCadastrar = findViewById(R.id.idCadastrarUsuarioCadastro);

        // Clique do botão
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edtNome.getText().toString().trim();
                String email = edtGmail.getText().toString().trim();
                String senha = edtSenha.getText().toString().trim();

                // Verificação simples
                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(TelaCadastro.this,
                            "Preencha todos os campos!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Aqui entraria o salvamento no banco, Firebase etc.
                Toast.makeText(TelaCadastro.this,
                        "Cadastro realizado com sucesso!",
                        Toast.LENGTH_SHORT).show();

                // Fecha a tela e volta para o login
                finish();
            }
        });




    }
}