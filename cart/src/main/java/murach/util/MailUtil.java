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

        // ==============================
        // KIỂM TRA API KEY
        // ==============================

        System.out.println("API KEY EXISTS: "
                + (apiKey != null && !apiKey.isBlank()));

        System.out.println("API KEY LENGTH: "
                + (apiKey == null ? 0 : apiKey.length()));

        // Nếu Render không có API key
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "Chưa cấu hình BREVO_API_KEY trên Render"
            );
        }

        // ==============================
        // TẠO JSON GỬI BREVO
        // ==============================

        String json = """
                {
                    "sender": {
                        "name": "Mail-2",
                        "email": "tridung280208@gmail.com"
                    },
                    "to": [
                        {
                            "email": "%s"
                        }
                    ],
                    "subject": "%s",
                    "textContent": "%s"
                }
                """.formatted(
                    escapeJson(to),
                    escapeJson(subject),
                    escapeJson(body)
                );

        // ==============================
        // TẠO HTTP REQUEST
        // ==============================

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

        // ==============================
        // GỬI REQUEST
        // ==============================

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response =
                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        // ==============================
        // IN KẾT QUẢ
        // ==============================

        System.out.println(
                "Brevo status: "
                + response.statusCode()
        );

        System.out.println(
                "Brevo response: "
                + response.body()
        );

        // ==============================
        // KIỂM TRA GỬI MAIL
        // ==============================

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

    // ==============================
    // ESCAPE JSON
    // ==============================

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
