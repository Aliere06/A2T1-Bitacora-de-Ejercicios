import java.util.Scanner;

public class Redondeador {
    public static void redondearValores(Scanner t) {
        System.out.print("Ingresa el valor verdadero: ");
        double vverda = t.nextDouble();
        System.out.print("Ingresa el número de decimales que desea redondear en el valor verdadero: ");
        int decimales = t.nextInt();
        vverda = redondear(vverda, decimales);
        System.out.println("El resultado del valor verdadero redondeado: " + vverda + '\n');

        System.out.print("Ingresa el valor aproximado: ");
        double vaprox = t.nextDouble();
        System.out.print("Ingresa el número de decimales que desea redondear en el valor aproximado: ");
        decimales = t.nextInt();
        vaprox = redondear(vaprox, decimales);
        System.out.println("El resultado del valor aproximado redondeado: " + vaprox + '\n');

        double errAbs = Math.abs(vverda - vaprox);
        System.out.print("Ingresa el número de decimales que desea redondear en el error absoluto: ");
        decimales = t.nextInt();
        System.out.println("El error absoluto redondeado es: " + redondear(errAbs, decimales) + '\n');
    }

    public static double redondear(double numero, int decimales) {
        numero = numero * Math.pow(10, decimales);
        numero = Math.rint(numero);
        numero = numero / Math.pow(10, decimales);
        return numero;
    }
}
