package com.owpk.hw.controllers;

import com.owpk.hw.exceptions.ExceptionForPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/exc")
public class TestException {

    @GetMapping("/simple")
    private String testException() {
        throw new ExceptionForPage("Oops!!!");
//        return "err";
    }

    @GetMapping("/model")
    private String testExceptionWithModel() {
        throw new ExceptionForPage("Ooops!!");
    }

    @GetMapping("/check")
    @ResponseBody
    private String checkIfAlive() {
        return "Hello!";
    }

}
