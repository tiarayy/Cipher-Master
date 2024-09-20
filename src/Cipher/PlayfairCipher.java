/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cipher;

/**
 *
 * @author user
 */
public class PlayfairCipher {
    private static final int MATRIX_SIZE = 5;

    public static String decrypt(String inputText, String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static String encrypt(String inputText, String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private String key;

    public PlayfairCipher(String key) {
        this.key = prepareKey(key);
    }

    private String prepareKey(String key) {
        StringBuilder uniqueKey = new StringBuilder();
        String keyUpper = key.toUpperCase().replace("J", "I");

        for (char c : keyUpper.toCharArray()) {
            if (Character.isLetter(c) && uniqueKey.indexOf(String.valueOf(c)) == -1) {
                uniqueKey.append(c);
            }
        }
        
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && uniqueKey.indexOf(String.valueOf(c)) == -1) {
                uniqueKey.append(c);
            }
        }
        return uniqueKey.toString();
    }

    private char[][] generateMatrix() {
        char[][] matrix = new char[MATRIX_SIZE][MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrix[i][j] = key.charAt(i * MATRIX_SIZE + j);
            }
        }
        return matrix;
    }

    private String formatPlaintext(String plaintext) {
        StringBuilder formattedText = new StringBuilder();
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            formattedText.append(c);
            if (i + 1 < plaintext.length() && c == plaintext.charAt(i + 1)) {
                formattedText.append('X'); // Insert 'X' between identical letters
            }
        }
        if (formattedText.length() % 2 != 0) {
            formattedText.append('X'); // Append 'X' if the length is odd
        }
        return formattedText.toString();
    }

    public String encrypt(String plaintext) {
        char[][] matrix = generateMatrix();
        String formattedText = formatPlaintext(plaintext);
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < formattedText.length(); i += 2) {
            char firstChar = formattedText.charAt(i);
            char secondChar = formattedText.charAt(i + 1);
            int[] firstPos = findPosition(matrix, firstChar);
            int[] secondPos = findPosition(matrix, secondChar);

            if (firstPos[0] == secondPos[0]) {
                ciphertext.append(matrix[firstPos[0]][(firstPos[1] + 1) % MATRIX_SIZE]);
                ciphertext.append(matrix[secondPos[0]][(secondPos[1] + 1) % MATRIX_SIZE]);
            } else if (firstPos[1] == secondPos[1]) {
                ciphertext.append(matrix[(firstPos[0] + 1) % MATRIX_SIZE][firstPos[1]]);
                ciphertext.append(matrix[(secondPos[0] + 1) % MATRIX_SIZE][secondPos[1]]);
            } else {
                ciphertext.append(matrix[firstPos[0]][secondPos[1]]);
                ciphertext.append(matrix[secondPos[0]][firstPos[1]]);
            }
        }
        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) {
        char[][] matrix = generateMatrix();
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char firstChar = ciphertext.charAt(i);
            char secondChar = ciphertext.charAt(i + 1);
            int[] firstPos = findPosition(matrix, firstChar);
            int[] secondPos = findPosition(matrix, secondChar);

            if (firstPos[0] == secondPos[0]) {
                plaintext.append(matrix[firstPos[0]][(firstPos[1] + MATRIX_SIZE - 1) % MATRIX_SIZE]);
                plaintext.append(matrix[secondPos[0]][(secondPos[1] + MATRIX_SIZE - 1) % MATRIX_SIZE]);
            } else if (firstPos[1] == secondPos[1]) {
                plaintext.append(matrix[(firstPos[0] + MATRIX_SIZE - 1) % MATRIX_SIZE][firstPos[1]]);
                plaintext.append(matrix[(secondPos[0] + MATRIX_SIZE - 1) % MATRIX_SIZE][secondPos[1]]);
            } else {
                plaintext.append(matrix[firstPos[0]][secondPos[1]]);
                plaintext.append(matrix[secondPos[0]][firstPos[1]]);
            }
        }
        return plaintext.toString();
    }

    private int[] findPosition(char[][] matrix, char c) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
