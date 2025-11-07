package com.sicredi.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AuthUtils {

    private static final String TOKEN_PATH = "src/test/resources/token.txt";

    /**
     * Salva o token em um arquivo para ser usado nos próximos testes.
     */
    public static void setToken(String token) {
        try {
            // Garante que a pasta exista
            File file = new File(TOKEN_PATH);
            file.getParentFile().mkdirs(); // cria src/test/resources se não existir

            // Escreve o token no arquivo
            FileWriter writer = new FileWriter(file);
            writer.write(token);
            writer.close();

            System.out.println("🔐 Token armazenado com sucesso!");
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar o token: " + e.getMessage());
        }
    }

    /**
     * Lê o token salvo do arquivo.
     */
    public static String getToken() {
        try {
            if (Files.exists(Paths.get(TOKEN_PATH))) {
                String token = new String(Files.readAllBytes(Paths.get(TOKEN_PATH)));
                System.out.println("🪪 Token carregado para o teste: " + token);
                return token;
            } else {
                System.out.println("⚠️ Nenhum token encontrado. Execute o AuthTest primeiro!");
            }
        } catch (IOException e) {
            System.err.println("❌ Erro ao ler o token: " + e.getMessage());
        }
        return null;
    }
}
