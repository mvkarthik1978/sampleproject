package com.example.sum.controller;

import com.example.sum.dto.SumResponse;
import com.example.sum.service.SumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SumController {

    private static final Logger LOGGER = LogManager.getLogger(SumController.class);

    private final SumService sumService;

    public SumController(SumService sumService) {
        this.sumService = sumService;
    }

    @GetMapping("/sum")
    public SumResponse sum(@RequestParam int a, @RequestParam int b) {
        LOGGER.info("Received sum request a={}, b={}", a, b);
        return new SumResponse(a, b, sumService.add(a, b));
    }
}
