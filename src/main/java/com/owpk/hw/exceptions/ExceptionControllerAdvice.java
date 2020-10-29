package com.owpk.hw.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage());
        MarketError err = new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GeneralAppException.class)
    @ResponseBody
    public ErrorInfo handleBadRequest(HttpServletRequest req, GeneralAppException ex) {
        return new ErrorInfo(req.getRequestURL(), ex);
    }

//  -- Returning simple page --
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(ExceptionForPage.class)
//    public String handlerGeneralError() {
//        return "err";
//    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(ExceptionForPage.class)
    public String handleError(Model model, ExceptionForPage ex) {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("message", ex.getMessage());
//        mav.setViewName("error_page");
        model.addAttribute("message", ex.getMessage());
        return "error_page";
    }
}
