
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
            String body)
            throws IOException, InterruptedException {

        // Lấy API key từ Environment Variable trên Render
        String apiKey = System.getenv("BREVO_API_KEY");

        // Kiểm tra Render có nhận API key hay không
        System.out.println("API KEY EXISTS: "
                + (apiKey != null && !apiKey.isBlank()));

        System.out.println("API KEY LENGTH: "
                + (apiKey == null ? 0 : apiKey.length()));

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "Chưa cấu hình BREVO_API_KEY trên Render"
            );
        }

        // Tạo JSON gửi cho Brevo
        String json =
                "{"
                + "\"sender\":{"
                + "\"name\":\"Mail-2\","
                + "\"email\":\"tridung280208@gmail.com\""
                + "},"
                + "\"to\":["
                + "{"
                + "\"email\":\"" + escapeJson(to) + "\""
                + "}"
                + "],"
                + "\"subject\":\"" + escapeJson(subject) + "\","
                + "\"textContent\":\"" + escapeJson(body) + "\""
                + "}";

        // Tạo HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://api.brevo.com/v3/smtp/email"
                ))
                .header("accept", "application/json")
                .header("api-key", apiKey)
                .header("content-type", "application/json")
                .POST(
                        HttpRequest.BodyPublishers.ofString(json)
                )
                .build();

        // Gửi request
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response =
                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        // In kết quả Brevo
        System.out.println(
                "Brevo status: "
                + response.statusCode()
        );

        System.out.println(
                "Brevo response: "
                + response.body()
        );

        // Kiểm tra kết quả
        if (response.statusCode() < 200
                || response.statusCode() >= 300) {

            throw new IOException(
                    "Brevo gửi mail thất bại: "
                    + response.statusCode()
                    + " - "
                    + response.body()
            );
        }

        System.out.println(
                "Email sent successfully to: " + to
        );
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
