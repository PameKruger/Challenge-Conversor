import java.io.*;
import java.util.Scanner;

public class Converciones {

    private static final String HIST_FILE = "historial.txt";

    public void convert(double rate, String origin, String destination, Scanner es) {
        System.out.print("Ingrese cantidad que quieres convertir en " + origin + ": ");
        double amount = es.nextDouble();
        double converted = amount * rate;
        System.out.println("Tu cantidad " + amount + " " + origin + " son " + converted + " " + destination);
        saveHistory(amount, origin, converted, destination);
    }

    private void saveHistory(double amount, String origin, double converted, String destination) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(HIST_FILE, true)))) {
            out.printf("Valor convertido: %.2f %s a %.2f %s%n", amount, origin, converted, destination);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de historial: " + e.getMessage());
        }
    }

    public void showHistory() {
        try (BufferedReader in = new BufferedReader(new FileReader(HIST_FILE))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de historial: " + e.getMessage());
        }
    }
}
