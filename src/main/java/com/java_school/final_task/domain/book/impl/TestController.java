package com.java_school.final_task.domain.book.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "world")
@RestController
public class TestController {
    @GetMapping
    public ResponseEntity<Object> getText() {
        return ResponseEntity.ok().body("Hello world");
    }
}
