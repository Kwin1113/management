package org.kwin.management.common.handler;

import org.kwin.management.common.exception.SysUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UserExceptionHandler {

    //拦截登陆异常
    @ExceptionHandler(value = SysUserException.class)
    public ModelAndView handlerUserException() {
        return new ModelAndView("redirect:/user/login");
    }

}
