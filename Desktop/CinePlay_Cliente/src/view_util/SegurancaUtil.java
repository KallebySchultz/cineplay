/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view_util;

/**
 *
 * @author victo
 */
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SegurancaUtil {

    /**
     * Criptografa uma senha usando o algoritmo SHA-256.
     * @param senha Texto da senha em formato puro.
     * @return Hash da senha em hexadecimal.
     */
    public static String criptografarSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(senha.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
