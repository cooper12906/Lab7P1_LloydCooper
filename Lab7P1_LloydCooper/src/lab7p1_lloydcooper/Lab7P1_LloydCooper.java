package lab7p1_lloydcooper;

import java.util.*;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class Lab7P1_LloydCooper {
    
    static Scanner leer = new Scanner(System.in);
    static Random random = new Random();
    
    public static void menu(){
        System.out.println("----------MENU----------");
        System.out.println("1. Promedios ");
        System.out.println("2. De landscape a portrait ");
        System.out.println("3. Higher order contains ");
        System.out.println("Ingrese una opcion: ");
        System.out.println("-------------------------");
    }
    
    public static void main(String[] args) {
        char resp = 's';
        do {
            menu();
            int opcion = leer.nextInt();
            switch (opcion){
                case 1:
                    System.out.print("Ingrese el número de filas: ");
                    int filas = leer.nextInt();
                    System.out.print("Ingrese el número de columnas: ");
                    int columnas = leer.nextInt();

                    // Hacer la matriz y llenarla con nUmeros aleatorios del 0 al 9
                    int[][] matriz = new int[filas][columnas];
                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            matriz[i][j] = random.nextInt(100);
                        }
                    }

                    // Pedir al usuario el tipo de promedio a calcular
                    String tipoString = JOptionPane.showInputDialog(null, imprimirMatriz(matriz) + "\nIngrese el tipo de promedio (1 para filas, 2 para columnas):", "Tipo de promedio", JOptionPane.QUESTION_MESSAGE);
                    char tipo = tipoString.charAt(0);

                    // Calcular los promedios y mostrarlos en un cuadro 
                    Double[] promedios = calcularPromedios(matriz, tipo);
                    imprimirPromedio(promedios, tipo);
                    break;
                case 2:
                    System.out.println("Ingrese las filas de la matriz: ");
                    filas = leer.nextInt();
                    System.out.println("Ingrese las columnas de la matriz: ");
                    columnas = leer.nextInt();

                    matriz = generarMatriz(filas, columnas);
                    JOptionPane.showConfirmDialog(null,"Matriz original\n:" + imprimirMatriz2(matriz));

                    matriz = rotarMatriz(matriz);
                    JOptionPane.showConfirmDialog(null,"Matriz rotada:\n" + imprimirMatriz2(matriz));
                                        
                    break;
                case 3:
                String inputM = JOptionPane.showInputDialog("Ingrese el número de filas de la matriz:");
                int M = Integer.valueOf(inputM);
                String inputN = JOptionPane.showInputDialog("Ingrese el número de columnas de la matriz:");
                int N = Integer.valueOf(inputN);
                String inputS = JOptionPane.showInputDialog("Ingrese el tamaño del arreglo:");
                int S = Integer.valueOf(inputS);

                // Generamos la matriz aleatoria y la mostramos al usuario
                int[][] matrix = new int[M][N];
                for (int i = 0; i < M; i++) {
                    for (int j = 0; j < N; j++) {
                        matrix[i][j] = (int) (Math.random() * 10);
                    }
                }
                String matrixString = "La matriz generada es:\n";
                for (int i = 0; i < M; i++) {
                    matrixString += Arrays.toString(matrix[i]) + "\n";
                }
                JOptionPane.showMessageDialog(null, matrixString);

                // Pedimos al usuario que ingrese los valores del arreglo
                int[] array = new int[S];
                String inputArray = JOptionPane.showInputDialog("Ingrese los valores del arreglo separados por coma:");
                String[] arrayValues = inputArray.split(",");
                for (int i = 0; i < S; i++) {
                    array[i] = Integer.valueOf(arrayValues[i]);
                }

                // Buscamos si el arreglo está contenido en la matriz
                boolean isContained = containsHO(matrix, array);

                // Mostramos el resultado al usuario y la matriz generada
                String resultString = "El arreglo ingresado es: " + Arrays.toString(array) + "\n";
                if (isContained) {
                    resultString += "El arreglo está contenido en la matriz generada.";
                    
                } else {
                    resultString += "El arreglo NO está contenido en la matriz generada.";
                }
                JOptionPane.showMessageDialog(null, resultString);
                
                    break;
            }
            System.out.println("Desea continuar? [s/n]");
            resp = leer.next().charAt(0);
        } while (resp == 's' || resp == 'S');   
    }
    //EJERCICIO 1:
    public static Double[] calcularPromedios(int[][] matriz, char tipoChar) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int tipo = Character.getNumericValue(tipoChar);
        Double[] promedios;
        if (tipo == 1) {
            promedios = new Double[filas];
        } else {
            promedios = new Double[columnas];
        }

        for (int i = 0; i < promedios.length; i++) {
            double suma = 0.0;
            for (int j = 0; j < (tipo == 1 ? columnas : filas); j++) {
                if (tipo == 1) {
                    suma += matriz[i][j];
                } else if (tipo == 2) {
                    suma += matriz[j][i];
                }
            }
            if (tipo == 1) {
                promedios[i] = suma / columnas;
            } else {
                promedios[i] = suma / filas;
            }
        }

        return promedios;
    }
    
    public static String imprimirMatriz(int[][] matriz) {
        String mensaje = "Matriz generada:\n";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                mensaje += "["+matriz[i][j] + "\t" + "]";
            }
            mensaje += "\n";
        }
        return mensaje; 
    }
    public static void imprimirPromedio(Double[] promedios, int tipo) {
        String tipoPromedio = (tipo == 1) ? "filas" : "columnas";
        String mensaje = "El promedio de las " + tipoPromedio + " es:\n";
        for (int i = 0; i < promedios.length; i++) {
            mensaje += "Promedio de " + tipoPromedio + " " + (i+1) + ": " +"["+ promedios[i] + "]" + "\n";
        }
        JOptionPane.showMessageDialog(null, mensaje, "Promedio de " + tipoPromedio, JOptionPane.INFORMATION_MESSAGE);
    }
    
    //EJERCICIO 2:
    public static int[][] rotarMatriz(int[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int[][] matrizRotada = new int[columnas][filas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizRotada[j][filas-1-i] = matriz[i][j];
            }
        }
        return matrizRotada;
    }
    
    public static int[][] generarMatriz(int filas, int columnas) {
        int[][] matriz = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = (int) (Math.random() * 10);
            }
        }
        return matriz;
    }
    
    public static String imprimirMatriz2(int[][] matriz) {
        String mensaje = "";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                mensaje += matriz[i][j] + " ";
            }
            mensaje += "\n";
        }
        return mensaje; 
    }
    
    //EJERCICIO 3:
        public static boolean containsHO(int[][] matriz, int[] array) {
        int M = matriz.length;
        int N = matriz[0].length;
        int S = array.length;

        // Buscamos si el arreglo está contenido en la matriz
        boolean isContained = false;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j <= N - S; j++) {
                boolean isEqual = true;
                for (int k = 0; k < S; k++) {
                    if (matriz[i][j+k] != array[k]) {
                        isEqual = false;
                        break;
                    }
                }
                if (isEqual) {
                    isContained = true;
                    break;
                }
            }
            if (isContained) {
                break;
            }
        }

        return isContained;
    }  
}
