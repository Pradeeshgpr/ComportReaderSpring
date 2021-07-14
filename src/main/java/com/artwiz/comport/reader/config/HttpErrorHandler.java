package com.artwiz.comport.reader.config;

import com.artwiz.comport.reader.util.HttpRequestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;

@ControllerAdvice
public class HttpErrorHandler {

//    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ServletException.class)
    public ModelAndView error404() {
        return new ModelAndView(HttpRequestUtils.get404ErrorPage());
    }

}
