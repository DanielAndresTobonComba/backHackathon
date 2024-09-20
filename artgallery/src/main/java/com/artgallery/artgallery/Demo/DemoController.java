package com.artgallery.artgallery.Demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/api/v1")
@RequiredArgsConstructor
public class DemoController {

    // http://localhost:8090/api/v1/demo

    @PostMapping("/demo")
    public String welcome () {
        return "welcome from secure endpoint";
    }
}
