package com.example.fibonacci.controller;

import com.example.fibonacci.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fibonacci")
public class FibonacciController {

    @Autowired
    private FibonacciService fibonacciService;

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
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }


    }

}
