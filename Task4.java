import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Base Currency (e.g., USD, INR, EUR): ");
        String base = sc.next().toUpperCase();

        System.out.print("Enter Target Currency (e.g., USD, INR, EUR): ");
        String target = sc.next().toUpperCase();

        System.out.print("Enter Amount to Convert: ");
        double amount = sc.nextDouble();

        try {
            String apiKey = "c5508c76ade9426d6da33960";  
            String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + base;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // 🔹 Extract rate manually
            String json = response.toString();
            String search = "\"" + target + "\":";
            int index = json.indexOf(search);

            if (index == -1) {
                System.out.println("Invalid target currency!");
                return;
            }

            int start = index + search.length();
            int end = json.indexOf(",", start);
            if (end == -1) end = json.indexOf("}", start);

            double rate = Double.parseDouble(json.substring(start, end));

            double converted = amount * rate;

            System.out.println("\n--- Currency Conversion Result ---");
            System.out.println(amount + " " + base + " = " + converted + " " + target);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}