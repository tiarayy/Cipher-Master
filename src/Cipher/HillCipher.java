/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cipher;

/**
 *
 * @author user
 */
public class HillCipher {

    public static String encrypt(String inputText, String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static String decrypt(String inputText, String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private int[][] keyMatrix;
    private int matrixSize;

    public HillCipher(int[][] keyMatrix) {
        this.keyMatrix = keyMatrix;
        this.matrixSize = keyMatrix.length;
    }

    public String encrypt(String plaintext) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder ciphertext = new StringBuilder();

        // Padding plaintext
        if (plaintext.length() % matrixSize != 0) {
            while (plaintext.length() % matrixSize != 0) {
                plaintext += 'X'; // Padding with 'X'
            }
        }

        // Encrypting in blocks
        for (int i = 0; i < plaintext.length(); i += matrixSize) {
            int[] plaintextBlock = new int[matrixSize];
            for (int j = 0; j < matrixSize; j++) {
                plaintextBlock[j] = plaintext.charAt(i + j) - 'A';
            }

            int[] encryptedBlock = multiplyMatrix(keyMatrix, plaintextBlock);
            for (int value : encryptedBlock) {
                ciphertext.append((char) ((value % 26) + 'A'));
            }
        }
        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) {
        int[][] inverseKeyMatrix = invertMatrix(keyMatrix);
        StringBuilder plaintext = new StringBuilder();

        // Decrypting in blocks
        for (int i = 0; i < ciphertext.length(); i += matrixSize) {
            int[] ciphertextBlock = new int[matrixSize];
            for (int j = 0; j < matrixSize; j++) {
                ciphertextBlock[j] = ciphertext.charAt(i + j) - 'A';
            }

            int[] decryptedBlock = multiplyMatrix(inverseKeyMatrix, ciphertextBlock);
            for (int value : decryptedBlock) {
                plaintext.append((char) ((value % 26 + 26) % 26 + 'A')); // Adjusting for negative values
            }
        }
        return plaintext.toString();
    }

    private int[] multiplyMatrix(int[][] matrix, int[] vector) {
        int[] result = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    private int[][] invertMatrix(int[][] matrix) {
        // This method assumes that the matrix is invertible and only works for 2x2 matrices
        int determinant = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % 26;
        int inverseDeterminant = modInverse(determinant, 26);

        int[][] inverseMatrix = {
            {matrix[1][1] * inverseDeterminant % 26, -matrix[0][1] * inverseDeterminant % 26},
            {-matrix[1][0] * inverseDeterminant % 26, matrix[0][0] * inverseDeterminant % 26}
        };

        // Adjust for negative values
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                inverseMatrix[i][j] = (inverseMatrix[i][j] + 26) % 26;
            }
        }
        return inverseMatrix;
    }

    private int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1; // In case there is no modular inverse
    }
}
