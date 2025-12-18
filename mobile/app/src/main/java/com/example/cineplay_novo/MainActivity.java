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

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private EditText edtNome, edtGmail, edtSenha;
    private Button btnCadastrar;
    public static ConexaoController ccont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.idSenhaCadastro), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        // O cod vai aqui:
// Referência aos componentes

        edtGmail = findViewById(R.id.idEmailLogin);
        edtSenha = findViewById(R.id.idSenhalLogin);
        btnCadastrar = findViewById(R.id.idEntrar);

        // Clique do botão
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtGmail.getText().toString().trim();
                String senha = edtSenha.getText().toString().trim();

                // Verificação simples
                if ( email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Preencha todos os campos!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }


                Toast.makeText(MainActivity.this,
                        "Login realizado com sucesso!",
                        Toast.LENGTH_SHORT).show();



            }
        });
        try {
            Socket socket = new Socket("192.168.6.253", 12345);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            // com o OUT eu posso enviar coisas para o SERVIDOR
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            // com o IN eu posso receber coisas do SERVIDOR
            ccont = new ConexaoController(out, in);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}