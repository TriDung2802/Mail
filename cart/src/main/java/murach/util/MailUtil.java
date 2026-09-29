package murach.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MailUtil {

    public static void sendMail(
            String to,
            String subject,
            String body) throws IOException, InterruptedException {

        String apiKey = System.getenv("BREVO_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "Chưa cấu hình BREVO_API_KEY trên Render"
            );
        }

        String json = "{"
        + "\"sender\":{"
        + "\"name\":\"Mail-2\","
        + "\"email\":\"tridung280208@gmail.com\""
        + "},"
        + "\"to\":[{"
        + "\"email\":\"" + escapeJson(to) + "\""
        + "}],"
        + "\"subject\":\"" + escapeJson(subject) + "\","
        + "\"textContent\":\"" + escapeJson(body) + "\""
        + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.brevo.com/v3/smtp/email"))
                .header("accept", "application/json")
                .header("api-key", apiKey)
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response =
                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        System.out.println("Brevo status: " + response.statusCode());
        System.out.println("Brevo response: " + response.body());

        if (response.statusCode() < 200 ||
            response.statusCode() >= 300) {

            throw new IOException(
                    "Brevo gửi mail thất bại: "
                    + response.statusCode()
                    + " - "
                    + response.body()
            );
        }
    }

    private static String escapeJson(String text) {
        if (text == null) {
            return "";
        }

        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}
