package com.karwowski.auth.web;

import com.karwowski.auth.model.User;
import com.karwowski.auth.service.SecurityService;
import com.karwowski.auth.service.UserService;
import com.karwowski.auth.validator.UserValidator;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);
        send("karwowski.pawel992@gmail.com",userForm.getUsername());
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String login(Model model) {
        List<User> allUsers = userService.findAllUsers();
        model.addAttribute("users",allUsers);
        logger.debug(allUsers.toString());
        return "users";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    private void send(String emailTo, String name) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(emailTo);
            // helper.setReplyTo("someone@localhost");
            helper.setFrom("karwowski.pawel992@gmail.com");
            String content = "Welcome "+name+"!";
            helper.setSubject(content);
            helper.setText("Lorem ipsum dolor sit amet [...]");
            logger.info("emailTo:"+emailTo);
            logger.info("name:"+name);
            logger.info("content:"+content);
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        } finally {
        }
        javaMailSender.send(mail);

    }
}