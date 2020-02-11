package com.group.backend.demo.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/*
Author. oigo edwin
*/

@Service
public class EmailService{

    public static final String SENDER_ACCOUNTS = "Account Manager";
    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;

    public static final String REG_EMAIL ="to";
    public static final String REG_URL_VALUE ="http://localhost:8080/login";
    public static final String REG_URL ="url";
    public static final String REF_TEMPLATE = "verification";
    public static final String SEND_FROM = "edwinoigoboni@gmail.com";

    public static final String USER_BLOCKED = "blocked";


    @Autowired
    public EmailService(JavaMailSender mailSender, TemplateEngine engine) {
        this.mailSender = mailSender;
        this.templateEngine = engine;

    }

    //user should verify email before allowed to login.
    public void sentRegistrationVerificationEmail(final String recipientName, final String recipientEmail)
            throws MessagingException {

          //context.
        final Context ctx = new Context();
        ctx.setVariable(REG_EMAIL, recipientEmail);
        ctx.setVariable(REG_URL, REG_URL_VALUE);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        message.setSubject("Registration successful");
        message.setFrom("edwinbonioigo@gmail.com");
        message.setTo(recipientEmail);

        final String htmlContent = this.templateEngine.process(REF_TEMPLATE, ctx);
        message.setText(htmlContent, true); // true = isHtml
        // Send mail
        this.mailSender.send(mimeMessage);
    }


    public void UserBlockedEmail(String sender, String recipient, String email)   throws MessagingException{

        //context.
        final Context ctx = new Context();
        ctx.setVariable("name", recipient);
        ctx.setVariable("sender", sender);
        ctx.setVariable("date", new Date());
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart

        message.setSubject("Account status changed");

        message.setFrom(SEND_FROM);
        message.setTo(email);
        final String htmlContent = this.templateEngine.process(USER_BLOCKED, ctx);
        message.setText(htmlContent, true); // true = isHtml
        this.mailSender.send(mimeMessage);




    }


}
