package com.myaiagent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author tanghua
 * @date: 2026/02/23/ 16:41
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public String healthCheck() {
        return "OK";
    }
}
