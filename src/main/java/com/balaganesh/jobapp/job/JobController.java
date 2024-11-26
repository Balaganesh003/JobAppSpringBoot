package com.balaganesh.jobapp.job;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
