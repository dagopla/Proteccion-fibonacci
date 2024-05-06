package com.example.fibonacci.controller;

import com.example.fibonacci.controller.dto.EmailFormaDto;
import com.example.fibonacci.service.FibonacciService;
import com.example.fibonacci.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping("/fibonacci")
public class FibonacciController {

    @Autowired
    private FibonacciService fibonacciService;
    @Autowired
    private MailService mailService;

    @GetMapping("/now")
    public ResponseEntity<?> fibonacciNow() {
        LocalTime date = LocalTime.now();
        try {
            int[] fibonacci = fibonacciService.fibonacci(date.getMinute(), date.getSecond());
            Map<String, Object> response = new HashMap<>();
            response.put("fibonacci", fibonacci);
            response.put("date", date);
            response.put("Semillas", date.getMinute());
            response.put("Numero de terminos", date.getSecond());
            EmailFormaDto emailFormDto = createMail(fibonacci);
            mailService.sendEmail(emailFormDto);
            response.put("email", "Emails enviados a: " + Arrays.toString(emailFormDto.getRecipients()));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                Map<String, Object> response = new HashMap<>();
                response.put("date", date);
                response.put("error", e.getMessage());
                return ResponseEntity.badRequest().body(response);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("date", date);
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }


    }
    private EmailFormaDto createMail(int[] fibonacci){
        EmailFormaDto emailFormDto = new EmailFormaDto();
        emailFormDto.setSubject("Fibonacci");
        emailFormDto.setBody(Arrays.toString(fibonacci));
        emailFormDto.setRecipients(new String[]{"dagopla@gmail.com"});
        return emailFormDto;
    }

}
