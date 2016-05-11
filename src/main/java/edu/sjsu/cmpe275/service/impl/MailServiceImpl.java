package edu.sjsu.cmpe275.service.impl;

import edu.sjsu.cmpe275.Util;
import edu.sjsu.cmpe275.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by yutao on 5/11/16.
 */
@Service
public class MailServiceImpl implements MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(String from , String to, String subject, String text) {
        logger.debug("Sending email...");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = null;
        try {
            mail = new MimeMessageHelper(message, true);
            mail.setFrom(from);
            mail.setTo(to);
            mail.setSubject(subject);
            logger.debug("Email text: {}", text);
            mail.setText(text, true);
            javaMailSender.send(message);
            logger.debug("Email Sent!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
