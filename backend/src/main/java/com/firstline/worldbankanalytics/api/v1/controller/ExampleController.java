package com.firstline.worldbankanalytics.api.v1.controller;

import com.firstline.worldbankanalytics.api.v1.dto.ExampleDto;
import com.firstline.worldbankanalytics.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("api/v1/example")
public class ExampleController {

    private final ExampleService exampleService;

    @Autowired
    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping(value = "/get")
    public ExampleDto getExample(@RequestParam long exampleId) {
        return exampleService.getExample(exampleId);
    }
}
