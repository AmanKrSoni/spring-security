package com.cvt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRestController {

    @GetMapping("/confirm")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String confirm(){
        return "confirm";
    }
}
