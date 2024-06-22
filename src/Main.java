import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    private static final String KEY = "7f6b2bec303f898164c1413b";
    private static final String URL_API = "https://v6.exchangerate-api.com/v6/7f6b2bec303f898164c1413b/latest/USD";

    public Main() {
    }

    public static void main(String[] args) {
        Scanner es = new Scanner(System.in);
        JsonObject rates = getRates();
        Converciones conversion = new Converciones();

        int opcion;

        do {
            printMenu();
            opcion = es.nextInt();
            double rate;
            switch (opcion) {
                case 1:
                    rate = rates.getAsJsonObject("conversion_rates").get("MXN").getAsDouble();
                    conversion.convert(rate, "USD", "MXN", es);
                    break;
                case 2:
                    rate = 1 / rates.getAsJsonObject("conversion_rates").get("MXN").getAsDouble();
                    conversion.convert(rate, "MXN", "USD", es);
                    break;
                case 3:
                    rate = rates.getAsJsonObject("conversion_rates").get("BRL").getAsDouble();
                    conversion.convert(rate, "USD", "BRL", es);
                    break;
                case 4:
                    rate = 1 / rates.getAsJsonObject("conversion_rates").get("BRL").getAsDouble();
                    conversion.convert(rate, "BRL", "USD", es);
                    break;
                case 5:
                    rate = rates.getAsJsonObject("conversion_rates").get("COP").getAsDouble();
                    conversion.convert(rate, "USD", "COP", es);
                    break;
                case 6:
                    rate = 1 / rates.getAsJsonObject("conversion_rates").get("COP").getAsDouble();
                    conversion.convert(rate, "COP", "USD", es);
                    break;
                case 7:
                    conversion.showHistory();
                    break;
                case 8:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 8);

        es.close();
    }

    private static void printMenu() {
        System.out.println("\033[0;31m*************************************************\033[0m");
        System.out.println("\033[0;34mBienvenido/a al Conversor de Moneda$ =]\033[0m");
        System.out.println("1) Dólar => Peso Mexicano");
        System.out.println("2) Peso Mexicano => Dólar");
        System.out.println("3) Dólar => Real brasileño");
        System.out.println("4) Real brasileño => Dólar");
        System.out.println("5) Dólar => Peso colombiano");
        System.out.println("6) Peso colombiano => Dólar");
        System.out.println("7) Ver historial de conversiones");
        System.out.println("8) Salir");
        System.out.print("\033[0;31mElija opción válida: \033[0m");
        System.out.println("\033[0;31m*************************************************\033[0m");
    }

    private static JsonObject getRates() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            JsonObject jsonResp = gson.fromJson(response.body(), JsonObject.class);
            return jsonResp;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener tasas: " + e.getMessage());
        }
    }
}









