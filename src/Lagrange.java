import java.util.Scanner;

public class Lagrange {
    public static void calcularLagrange(Scanner t) {
        System.out.println("====INTERPOLACIÓN DE LAGRANGE====");
        // Ingresar los puntos conocidos
        System.out.print("Ingrese el número de puntos conocidos: ");
        int n = t.nextInt();
        double[] x = new double[n];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese el valor de x" + (i + 1) + ": ");
            x[i] = t.nextDouble();
            System.out.print("Ingrese el valor de y" + (i + 1) + ": ");
            y[i] = t.nextDouble();
        }

        // Ingresar el punto en el que se desea interpolar
        System.out.print("Ingrese el valor de x para interpolar: ");
        double xInterpolar = t.nextDouble();

        // Calcular el valor interpolado
        double resultado = lagrangeInterpolation(x, y, xInterpolar);
        System.out.println("El valor interpolado en x = " + xInterpolar + " es y = " + resultado);
    }

    // Método para calcular la interpolación de Lagrange
    private static double lagrangeInterpolation(double[] x, double[] y, double xInterpolar) {
        double resultado = 0;
        for (int i = 0; i < x.length; i++) {
            double termino = y[i];
            for (int j = 0; j < x.length; j++) {
                if (j != i) {
                    termino *= (xInterpolar - x[j]) / (x[i] - x[j]);
                }
            }
            resultado += termino;
        }
        return resultado;
    }
}
