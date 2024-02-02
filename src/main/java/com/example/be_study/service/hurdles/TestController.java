package com.example.be_study.service.hurdles;

import com.example.be_study.service.hurdles.service.TestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {


    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public String startStackOverFlow() throws JsonProcessingException {
        return testService.letsGoStackOverFlowDtoVer();
    }
}
