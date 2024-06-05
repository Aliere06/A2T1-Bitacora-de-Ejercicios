import java.util.Scanner;

public class Truncador {
    public static void truncarValores(Scanner t) {
        System.out.print("Ingresa el valor verdadero: ");
        double vverda = t.nextDouble();
        System.out.print("Ingresa el número de decimales que desea truncar en el valor verdadero: ");
        int decimales = t.nextInt();
        vverda = truncar(vverda, decimales);
        System.out.println("El resultado del valor verdadero truncado: " + vverda + '\n');

        System.out.print("Ingresa el valor aproximado: ");
        double vaprox = t.nextDouble();
        System.out.print("Ingresa el número de decimales que desea truncar en el valor aproximado: ");
        decimales = t.nextInt();
        vaprox = truncar(vaprox, decimales);
        System.out.println("El resultado del valor aproximado truncado: " + vaprox + '\n');

        double errAbs = Math.abs(vverda - vaprox);
        System.out.print("Ingresa el número de decimales que desea truncar en el error absoluto: ");
        decimales = t.nextInt();
        System.out.println("El error absoluto truncado es: " + truncar(errAbs, decimales) + '\n');
    }

    public static double truncar(double numero, int decimales) {
        numero = numero * Math.pow(10, decimales);
        numero = Math.floor(numero);
        numero = numero / Math.pow(10, decimales);
        return numero;
    }
}
