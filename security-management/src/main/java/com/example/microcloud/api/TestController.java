package com.example.microcloud.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> hi() {
        return ResponseEntity.ok("***   OK    ***");
    }
}
