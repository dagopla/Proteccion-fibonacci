package com.example.fibonacci.service;

import com.example.fibonacci.controller.dto.EmailFormaDto;

public interface MailService {
    void sendEmail(EmailFormaDto emailFormDto);

    Boolean verifyEmail(String[] emailAddre);
}