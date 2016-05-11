package edu.sjsu.cmpe275.controller;

import edu.sjsu.cmpe275.Constant;
import edu.sjsu.cmpe275.Util;
import edu.sjsu.cmpe275.dao.UserDao;
import edu.sjsu.cmpe275.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

/**
 * Created by yutao on 5/5/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute SignupFormTO signupFormTO, Model model) {
        logger.debug("signup form: {}", signupFormTO.toString());
        // check email
        List<User> existEmails = userDao.findByEmail(signupFormTO.getEmail());
        if (existEmails != null && existEmails.size() > 0) {
            // error
            return "redirect:/signupform?error=Email%20exist";
        }

        // password equal, check in front end

        String md5 = Util.md5(signupFormTO.getPassword() + "{" + Constant.SALT + "}");
        //String md5 = Util.md5(signupFormTO.getPassword());
        String uuid = UUID.randomUUID().toString();
        User newUser = new User(signupFormTO.getEmail(), md5, false, uuid);
        userDao.save(newUser);
        logger.debug("signup user:{}", newUser.toString());
        send(javaMailSender, "chrishou1109@gmail.com", signupFormTO.getEmail(), uuid);
        return "signupresult";
    }

    private void send(JavaMailSender javaMailSender, String from, String to, String uuid) {
        logger.debug("Sending email...");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mail = null;
        try {
            mail = new MimeMessageHelper(message, true);
            mail.setFrom(from);
            mail.setTo(to);
            mail.setSubject("YummyTeam9.Food Email Verification");
            String encodedEmail = URLEncoder.encode(to, StandardCharsets.UTF_8.toString());
            String link = "http://" + Util.serverIP() + ":8080/user/activate/" + encodedEmail + "/" + uuid;
            logger.debug("Email link: {}", link);
            String text = "<html><body><h3>Please click this <a href=\"" + link + "\">link</a></h3></body></html>";
            logger.debug("Email text: {}", text);
            mail.setText(text, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        logger.debug("Email Sent!");

    }

    @RequestMapping(value = "/activate/{email}/{uuid}", method = RequestMethod.GET)
    public String activate(@PathVariable String email, @PathVariable String uuid) {
        logger.debug("activate email:{}, uuid:{}", email, uuid);
        List<User> userList = userDao.findByEmail(email);
        if (userList == null || userList.size() == 0) {
            // error
            logger.error("user activation error: no user");
        }
        if (userList.size() > 1) {
            // error
            logger.error("user activation error: multi users");

        }

        User user = userList.get(0);

        if (!user.getActiveCode().equals(uuid)) {
            // error
            logger.error("user activation error: active code doesn't match");
        }
        user.setActive(true);
        userDao.save(user);
        return "redirect:/";
    }

    static class SignupResultTO {
        int code;
        String message;

        public SignupResultTO() {
        }

        public SignupResultTO(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    static class SignupFormTO {
        String email;
        String password;
        private String confirmPassword;

        public SignupFormTO() {
        }

        public SignupFormTO(String email, String password, String confirmPassword) {
            this.email = email;
            this.password = password;
            this.confirmPassword = confirmPassword;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "SignupFormTO{" +
                    "email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }


}
