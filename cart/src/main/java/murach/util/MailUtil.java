package murach.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class MailUtil {
public static void sendMail(
        String to,
        String subject,
        String body)
        throws MessagingException {

    final String from = "tridung280208@gmail.com";
    final String appPassword = "jkxt lyfn bthe anqn";

    Properties props = new Properties();

    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    Session session = Session.getInstance(
            props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                            from,
                            appPassword
                    );
                }
            }
    );

    Message message = new MimeMessage(session);

    message.setFrom(new InternetAddress(from));

    message.setRecipient(
            Message.RecipientType.TO,
            new InternetAddress(to)
    );

    message.setSubject(subject);

    message.setText(body);

    Transport.send(message);
}

}
