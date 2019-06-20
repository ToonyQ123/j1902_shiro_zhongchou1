package com.qf.j1902.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = UnauthorizedException.class)
    public String defaultErrorHandler(HttpServletRequest request,Exception e){
        return "/exception/unauth";
    }
    @ExceptionHandler(value = AuthorizationException.class)
    public String error(){
        return "/exception/500";
    }
}
