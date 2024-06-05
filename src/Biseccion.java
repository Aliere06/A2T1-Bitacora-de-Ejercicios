import java.io.IOError;
import java.util.Scanner;
import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.parser.ParseException;

public class Biseccion {
    public static void calcularBiseccion(Scanner t) {
        System.out.println("====MÉTODO DE BISECCIÓN====");
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
                System.console().flush();
                System.out.println();
                if (intervaloA >= intervaloB) {
                    throw new IllegalArgumentException("El inicio del intervalo debe ser menor que el final");
                }
                System.out.println("Raíz: " + metodoBiseccion(funcion, 0, intervaloA, intervaloB));
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

    public static double metodoBiseccion(Expression funcion, double intervaloM, double intervaloA, double intervaloB)
            throws EvaluationException, ParseException {
        double error = (intervaloB - intervaloA) / 2;
        intervaloM = (intervaloA + intervaloB) / 2;
        System.out.println("Evaluando en intervalo [" + intervaloA + ", " + intervaloB + "]");
        System.out.println("Punto medio: " + intervaloM);
        System.out.println("Error: " + error + '\n');
        if (error <= .01) {
            return intervaloM;
        }
        double funcionA = funcion.with("x", intervaloA).evaluate().getNumberValue().doubleValue();
        double funcionB = funcion.with("x", intervaloB).evaluate().getNumberValue().doubleValue();
        double funcionM = funcion.with("x", intervaloM).evaluate().getNumberValue().doubleValue();
        if (funcionM == 0) {
            error = 0;
            return intervaloM;
        }
        if (funcionM * funcionA < 0) {
            intervaloB = intervaloM;
        } else if (funcionM * funcionB < 0) {
            intervaloA = intervaloM;
        } else {
            throw new IllegalArgumentException("No existe raíz en el intervalo dado");
        }
        return metodoBiseccion(funcion, intervaloM, intervaloA, intervaloB);
    }
}
