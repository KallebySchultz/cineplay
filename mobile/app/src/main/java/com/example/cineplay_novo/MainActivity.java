package com.example.cineplay_novo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
    
    // Configuração do servidor - altere aqui conforme necessário
    private static final String SERVER_IP = "192.168.6.253"; // Altere para o IP do seu servidor
    private static final int SERVER_PORT = 12345;
    
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

        // Conectar ao servidor em thread separada
        connectToServer();

        // Clique do botão
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtGmail.getText().toString().trim();
                String senha = edtSenha.getText().toString().trim();

                // Verificação simples
                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Preencha todos os campos!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Verificar se está conectado ao servidor
                if (ccont == null) {
                    Toast.makeText(MainActivity.this,
                            "Não conectado ao servidor! Tentando reconectar...",
                            Toast.LENGTH_SHORT).show();
                    connectToServer();
                    return;
                }

                Toast.makeText(MainActivity.this,
                        "Login realizado com sucesso!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void connectToServer() {
        // Conectar em thread separada para não bloquear UI
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Tentando conectar ao servidor " + SERVER_IP + ":" + SERVER_PORT + "...");
                    Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.flush(); // Important: flush to ensure header is sent
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    
                    ccont = new ConexaoController(out, in);
                    System.out.println("✓ Conectado ao servidor com sucesso!");
                    
                    // Mostrar mensagem na UI thread
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,
                                    "Conectado ao servidor!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final Exception e) {
                    System.err.println("✗ ERRO ao conectar ao servidor:");
                    e.printStackTrace();
                    
                    // Mostrar erro na UI thread
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            String errorMessage = String.format(
                                "Erro ao conectar ao servidor: %s:%d\n\n" +
                                "Verifique se o servidor está rodando e se o IP está correto.",
                                SERVER_IP, SERVER_PORT);
                            Toast.makeText(MainActivity.this,
                                    errorMessage,
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }
}