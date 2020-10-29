package com.owpk.hw.exceptions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class PageExcpController {

    @ExceptionHandler(ExceptionForPage.class)
    public String handlerGeneralError() {
        return "err";
    }
}
