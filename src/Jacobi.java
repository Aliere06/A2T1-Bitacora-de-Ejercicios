import java.util.Scanner;

public class Jacobi {
    public static void calcularJacobi(Scanner t) {
        System.out.println("====MÉTODO DE JACOBI====");
        // Ingresar la matriz de coeficientes
        System.out.println("Ingrese la matriz de coeficientes:");
        double[][] coeficientes = leerMatriz(t);

        // Ingresar el vector de términos independientes
        System.out.println("Ingrese el vector de términos independientes:");
        double[] terminosIndependientes = leerVector(t, coeficientes.length);

        // Ingresar el vector inicial
        System.out.println("Ingrese el vector inicial:");
        double[] vectorInicial = leerVector(t, coeficientes.length);

        // Ingresar el número de iteraciones
        System.out.println("Ingrese el número de iteraciones:");
        int iteraciones = t.nextInt();

        // Ejecutar el método de Jacobi
        double[] solucion = jacobi(coeficientes, terminosIndependientes, vectorInicial, iteraciones);

        // Mostrar la solución
        System.out.println("La solución encontrada es:");
        for (int i = 0; i < solucion.length; i++) {
            System.out.println("x" + (i + 1) + " = " + solucion[i]);
        }
    }

    // Método para leer una matriz de coeficientes
    private static double[][] leerMatriz(Scanner t) {
        System.out.print("Ingrese el tamaño de la matriz (filas x columnas): ");
        int filas = t.nextInt();
        int columnas = t.nextInt();
        double[][] matriz = new double[filas][columnas];
        System.out.println("Ingrese los elementos de la matriz:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = t.nextDouble();
            }
        }
        return matriz;
    }

    // Método para leer un vector
    private static double[] leerVector(Scanner t, int longitud) {
        double[] vector = new double[longitud];
        System.out.println("Ingrese los elementos del vector:");
        for (int i = 0; i < longitud; i++) {
            vector[i] = t.nextDouble();
        }
        return vector;
    }

    // Método para ejecutar el método de Jacobi
    private static double[] jacobi(double[][] coeficientes, double[] terminosIndependientes, double[] vectorInicial, int iteraciones) {
        int n = coeficientes.length;
        double[] x = new double[n];
        double[] xNuevo = new double[n];
        for (int k = 0; k < iteraciones; k++) {
            for (int i = 0; i < n; i++) {
                double suma = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        suma += coeficientes[i][j] * vectorInicial[j];
                    }
                }
                xNuevo[i] = (terminosIndependientes[i] - suma) / coeficientes[i][i];
            }
            System.arraycopy(xNuevo, 0, vectorInicial, 0, n);
        }
        return vectorInicial;
    }
}
