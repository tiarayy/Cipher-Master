/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cipher;

/**
 *
 * @author user
 */
public class VigenereCipher {
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        key = key.toUpperCase();
        int keyLength = key.length();
        
        for (int i = 0, j = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (Character.isLetter(c)) {
                char encryptedChar = (char) (((c + key.charAt(j % keyLength) - 2 * 'A') % 26) + 'A');
                ciphertext.append(encryptedChar);
                j++;
            } else {
                ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        key = key.toUpperCase();
        int keyLength = key.length();
        
        for (int i = 0, j = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            if (Character.isLetter(c)) {
                char decryptedChar = (char) (((c - key.charAt(j % keyLength) + 26) % 26) + 'A');
                plaintext.append(decryptedChar);
                j++;
            } else {
                plaintext.append(c);
            }
        }
        return plaintext.toString();
    }
}
