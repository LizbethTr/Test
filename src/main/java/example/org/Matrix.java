package example.org;
import java.io.*;
import java.util.Scanner;

public class Matrix {

    public static double[][] multiply(double[][] firstMatrix, double[][] secondMatrix) {

        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }
        return result;
    }


    private static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    public static void print(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }



    public static double[][] add(double[][] firstMatrix, double[][] secondMatrix) {

        if (firstMatrix.length!=secondMatrix.length || firstMatrix[0].length != secondMatrix[0].length)
            throw new IllegalArgumentException();

        double[][] result = new double[firstMatrix.length][firstMatrix[0].length];
        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix[0].length; j++) {
                result[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
            }
        }
        return result;
    }

    public static double[][] subtract(double[][] firstMatrix, double[][] secondMatrix) {

        if (firstMatrix.length!=secondMatrix.length || firstMatrix[0].length != secondMatrix[0].length)
            throw new IllegalArgumentException();

        double[][] result = new double[firstMatrix.length][firstMatrix[0].length];
        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix[0].length; j++) {
                result[i][j] = firstMatrix[i][j] - secondMatrix[i][j];
            }
        }
        return result;
    }

    public static double[][] multiplyByNumber(double[][] firstMatrix, double number) {
        double[][] result = new double[firstMatrix.length][firstMatrix[0].length];
        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix[0].length; j++) {
                result[i][j] = firstMatrix[i][j] * number;
            }
        }
        return result;
    }


    public static double[][] ReadFromFile(String name) {
       File file = new File(name);
       double[][] result;

       try {
           Scanner sc = new Scanner(file);
           int rows = sc.nextInt();
           int cols = sc.nextInt();

           result = new double[rows][cols];
           for(int i=0;i<rows;i++){
               for(int j=0;j<cols;j++){
                   result[i][j]=sc.nextDouble();
               }
           }
       } catch (Exception exception){
           result = null;
       }
       return result;
    }
}
