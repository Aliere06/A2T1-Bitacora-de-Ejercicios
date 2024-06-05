import java.io.IOError;
import java.util.Scanner;
import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.parser.ParseException;

public class Simpson {
    public static void calcularSimpson(Scanner t) {
        System.out.println("====MÉTODO DE SIMPSON====");
        boolean loop;
        do {
            System.out.print("Ingrese la función: ");
            try {
                Expression funcion = new Expression(System.console().readLine());
                funcion.validate();
                System.out.print("Ingrese el inicio del intervalo: ");
                double intervaloA = Double.parseDouble(System.console().readLine());
                System.out.print("Ingrese el fin del intervalo: ");
                double intervaloB = Double.parseDouble(System.console().readLine());
                System.out.print("Ingrese el valor de n (intervalos): ");
                int intervalos = Integer.parseInt(System.console().readLine());
                System.console().flush();
                System.out.println();
                if (intervaloA >= intervaloB) {
                    throw new IllegalArgumentException("El inicio del intervalo debe ser menor que el final");
                }
                System.out.println("Resultado de la integral: " + metodoSimpson(funcion, intervaloA, intervaloB, intervalos));
                System.out.println();
                loop = false;
            } catch (IOError | ParseException | NullPointerException | IllegalArgumentException | EvaluationException e) {
                if (e instanceof IOError) {
                    System.out.println("Error de captura - " + e.getMessage());
                } else if (e instanceof ParseException) {
                    System.out.println("Función inválida - " + e.getMessage());
                } else if (e instanceof NullPointerException) {
                    System.out.println("Valor de intervalo nulo - " + e.getMessage());
                } else if (e instanceof IllegalArgumentException) {
                    System.out.println("Error en el intervalo - " + e.getMessage());
                } else if (e instanceof EvaluationException) {
                    System.out.println("Error al evaluar la función - " + e.getMessage());
                }
                loop = false;
                System.out.println("Presione 1 para reintentar");
                int opcion = t.nextInt();
                if (opcion == 1) {
                    loop = true;
                }
            }
        } while (loop);
    }

    public static double metodoSimpson(Expression funcion, double intervaloA, double intervaloB, int intervalos)
            throws EvaluationException, ParseException {
        double h = (intervaloB - intervaloA) / intervalos;
        double suma = funcion.with("x", intervaloA).evaluate().getNumberValue().doubleValue()
                + funcion.with("x", intervaloB).evaluate().getNumberValue().doubleValue();
        for (int i = 1; i < intervalos; i++) {
            double x = intervaloA + i * h;
            double fx = funcion.with("x", x).evaluate().getNumberValue().doubleValue();
            if (i % 2 == 0) {
                suma += 2 * fx;
            } else {
                suma += 4 * fx;
            }
        }
        return suma * h / 3;
    }
}
