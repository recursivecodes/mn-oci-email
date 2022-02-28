package codes.recursive;

import io.micronaut.context.annotation.Property;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.email.javamail.sender.MailPropertiesProvider;
import io.micronaut.email.javamail.sender.SessionProvider;
import jakarta.inject.Singleton;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Singleton
public class OciSessionProvider implements SessionProvider {
    private final Properties properties;
    private final String user;
    private final String password;

    public OciSessionProvider(
            MailPropertiesProvider properties,
            @Property(name = "codes.recursive.smtp.user") String user,
            @Property(name = "codes.recursive.smtp.password") String password
    ) {
        this.properties = properties.mailProperties();
        this.user = user;
        this.password = password;
    }

    @Override
    @NonNull
    public Session session() {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
    }
}
