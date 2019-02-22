package org.kwin.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
@Slf4j
public class ErrController implements ErrorController {


    @Override
    public String getErrorPath() {
        log.error("出错了！进入自定义错误控制器");
        return "error/error";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }
}
