package com.example.fibonacci.service;

import com.example.fibonacci.controller.dto.EmailFormaDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@PropertySource("classpath:email.properties")
public class MailServicImp implements MailService {
    @Value("${email.username}")
    private String username;

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmail(EmailFormaDto emailFormDto) {
        try{
            MimeMessage message=crearMensaje(emailFormDto);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    public MimeMessage crearMensaje(EmailFormaDto emailFormDto) throws MessagingException {
        MimeMessage message=javaMailSender.createMimeMessage();
        configueMessage(emailFormDto, message);
        return message;
    }
    public void configueMessage(EmailFormaDto emailFormDto, MimeMessage message) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo(emailFormDto.getRecipients());
        helper.setFrom(username);
        helper.setSubject(emailFormDto.getSubject());
        helper.setText(emailFormDto.getBody(), true);

    }

    @Override
    public Boolean verifyEmail(String[] emailAddre) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        for (String mail :
                emailAddre) {
            Matcher mather = pattern.matcher(mail);
            if (!mather.find()){
                return false;
            }
        }
        return true;
    }

}
