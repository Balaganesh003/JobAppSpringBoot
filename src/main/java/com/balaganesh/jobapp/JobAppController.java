package com.balaganesh.jobapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobAppController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
