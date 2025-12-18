# CinePlay

Sistema de gerenciamento de cinemas com aplicativos desktop e mobile.

## Estrutura do Projeto

- **Desktop/CinePlay_Servidor**: Servidor Java que gerencia banco de dados e conexões dos clientes
- **Desktop/CinePlay_Cliente**: Cliente Desktop Java (Swing)
- **mobile**: Aplicativo Android (Kotlin/Java)
- **banco de dados**: Scripts SQL para criar o banco de dados

## Pré-requisitos

### Para o Servidor e Cliente Desktop
- Java JDK 17 ou superior
- Apache Ant (para compilação)
- MySQL 5.7 ou superior
- MySQL Connector/J 9.5.0 (já incluído no projeto)

### Para o Aplicativo Mobile
- Android Studio
- JDK 17 ou superior
- SDK Android 24 ou superior

## Configuração do Banco de Dados

1. Instale e inicie o MySQL Server
2. Execute o script SQL para criar o banco de dados:
   ```bash
   mysql -u root -p < "banco de dados/BDCinePlay.sql"
   ```
3. O banco de dados `cineplay` será criado com as tabelas:
   - `cinema`: Armazena informações dos cinemas
   - `filme`: Armazena informações dos filmes

## Configuração e Execução

### 1. Servidor

O servidor deve ser iniciado primeiro para aceitar conexões dos clientes.

**Compilar:**
```bash
cd Desktop/CinePlay_Servidor
ant clean compile
```

**Executar:**
```bash
ant run
```

O servidor irá:
- Conectar ao banco de dados MySQL (localhost:3306/cineplay)
- Iniciar na porta 12345
- Aguardar conexões dos clientes

**Saída esperada:**
```
=== Iniciando Servidor CinePlay ===
Verificando conexão com banco de dados...
Conexão com banco de dados estabelecida com sucesso!
✓ Conectado ao banco cineplay com sucesso!
Iniciando servidor na porta 12345...
✓ Servidor CinePlay inicializado com sucesso!
Aguardando conexões de clientes...
=====================================
```

### 2. Cliente Desktop

**Compilar:**
```bash
cd Desktop/CinePlay_Cliente
ant clean compile
```

**Executar:**
```bash
ant run
```

O cliente irá:
- Conectar ao servidor em 127.0.0.1:12345
- Abrir a interface gráfica

**Se houver erro de conexão:**
- Verifique se o servidor está rodando
- Verifique se a porta 12345 está disponível
- Verifique configurações de firewall

### 3. Aplicativo Mobile

**Configuração:**
1. Abra o projeto `mobile` no Android Studio
2. Edite `MainActivity.java` e altere o IP do servidor:
   ```java
   private static final String SERVER_IP = "SEU_IP_AQUI"; // Ex: "192.168.1.100"
   ```
3. O IP deve ser do computador onde o servidor está rodando
4. Para encontrar o IP:
   - Windows: `ipconfig`
   - Linux/Mac: `ifconfig` ou `ip addr`

**Executar:**
1. Conecte um dispositivo Android ou inicie um emulador
2. Execute o app no Android Studio

## Problemas Comuns

### Servidor não conecta ao banco de dados
**Erro:** `Não foi possível conectar ao banco de dados`

**Solução:**
- Verifique se o MySQL está rodando: `systemctl status mysql` (Linux) ou verifique nos serviços (Windows)
- Verifique se o banco `cineplay` existe: `SHOW DATABASES;`
- Verifique as credenciais em `Desktop/CinePlay_Servidor/src/factory/Conector.java`:
  - URL: `jdbc:mysql://localhost:3306/cineplay`
  - Usuário: `root`
  - Senha: (vazia)

### Cliente Desktop não conecta ao servidor
**Erro:** `Não foi possível conectar ao servidor!`

**Solução:**
- Verifique se o servidor está rodando
- Verifique se a porta 12345 está livre: `netstat -an | grep 12345`
- Desative temporariamente o firewall para testar

### Mobile não conecta ao servidor
**Erro:** `Erro ao conectar ao servidor`

**Solução:**
- Verifique se o IP está correto em `MainActivity.java`
- Verifique se o dispositivo mobile está na mesma rede que o servidor
- Verifique se o firewall permite conexões na porta 12345
- Teste com `telnet IP_DO_SERVIDOR 12345` de outro computador

### Porta 12345 já está em uso
**Erro:** `A porta 12345 já está em uso!`

**Solução:**
- Mate o processo que está usando a porta:
  - Linux/Mac: `lsof -i :12345` e depois `kill -9 PID`
  - Windows: `netstat -ano | findstr :12345` e depois `taskkill /PID PID /F`

## Funcionalidades

### Servidor
- Gerenciamento de conexões de múltiplos clientes
- Acesso ao banco de dados MySQL
- Autenticação de cinemas com senha criptografada (SHA-256)
- CRUD de cinemas e filmes

### Cliente Desktop
- Login de cinemas
- Cadastro de novos cinemas
- Gerenciamento de filmes
- Edição de dados do cinema

### Cliente Mobile
- Login de cinemas
- Visualização de filmes

## Correções Implementadas

### Problemas Corrigidos
1. **FilmeDao.java**:
   - Query SQL incompleta em `getLista()` corrigida
   - Número incorreto de placeholders no INSERT corrigido
   - Ordem dos parâmetros do construtor Filme corrigida

2. **CinemaDao.java**:
   - Parâmetro WHERE faltando no método `editar()` adicionado

3. **Conector.java**:
   - Validação de conexão adicionada
   - Mensagens de erro melhoradas
   - Verificação de conexão fechada

4. **Servidor Principal**:
   - Tratamento de erros aprimorado
   - Mensagens informativas adicionadas
   - Validação de porta em uso

5. **Cliente Desktop**:
   - Tratamento de erro de conexão adicionado
   - Mensagens de erro amigáveis
   - Dialog de erro para o usuário

6. **Cliente Mobile**:
   - Conexão movida para thread em background
   - Mensagens de erro adicionadas
   - Configuração de IP do servidor facilitada
   - Ordem de inicialização de ObjectStreams corrigida

## Arquitetura

```
CinePlay_Servidor (Porta 12345)
    ↓
MySQL Database (localhost:3306/cineplay)

CinePlay_Cliente (Desktop) → Socket → Servidor
CinePlay_Mobile (Android) → Socket → Servidor
```

## Tecnologias Utilizadas

- **Backend**: Java, JDBC, Sockets
- **Frontend Desktop**: Java Swing
- **Frontend Mobile**: Android (Java/Kotlin)
- **Banco de Dados**: MySQL
- **Build Tools**: Apache Ant (Desktop), Gradle (Mobile)

## Licença

Este é um projeto acadêmico.
