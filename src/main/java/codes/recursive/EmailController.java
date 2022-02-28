package codes.recursive;

import io.micronaut.core.util.CollectionUtils;
import io.micronaut.email.Attachment;
import io.micronaut.email.BodyType;
import io.micronaut.email.Email;
import io.micronaut.email.EmailSender;
import io.micronaut.email.template.TemplateBody;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.views.ModelAndView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Controller("/email")
@SuppressWarnings({"rawtypes"})
public class EmailController {

    private final EmailSender emailSender;

    public EmailController(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Get(uri="/", produces="text/plain")
    public String index() {
        Email.Builder emailBuilder = Email.builder()
                .to("pipar27174@shackvine.com")
                .subject("Basic Micronaut Email Test: " + LocalDateTime.now())
                .body("This is an email");
        emailSender.send(emailBuilder);
        return "Email sent.";
    }

    @Get(uri="/template/{name}", produces="text/plain")
    public String template(@PathVariable String name) {
        Map model = CollectionUtils.mapOf("name", name);
        Email.Builder emailBuilder = Email.builder()
                .to("pipar27174@shackvine.com")
                .subject("Micronaut Email Template Test: " + LocalDateTime.now())
                .body(new TemplateBody<>(BodyType.HTML, new ModelAndView<>("email", model)));
        emailSender.send(emailBuilder);
        return "Email sent.";
    }

    @Post(uri="/attachment", produces="text/plain", consumes = MediaType.MULTIPART_FORM_DATA)
    public String attachment(CompletedFileUpload file) throws IOException {
        Email.Builder emailBuilder = Email.builder()
                .to("pipar27174@shackvine.com")
                .subject("Micronaut Email Attachment Test: " + LocalDateTime.now())
                .body("This is an email")
                .attachment(
                        Attachment.builder()
                                .filename(file.getFilename())
                                .contentType(file.getContentType().isPresent() ? file.getContentType().get().toString() : MediaType.APPLICATION_OCTET_STREAM)
                                .content(file.getBytes())
                                .build()
                );
        emailSender.send(emailBuilder);
        return "Email sent.";
    }
}