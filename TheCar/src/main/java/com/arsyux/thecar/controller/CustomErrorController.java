package com.arsyux.thecar.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
	
	// 에러페이지 이동
	@RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request) {
		/*
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            int statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()){
        		System.out.println("404 에러 진입");
                return "error/404";
            }
            if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
        		System.out.println("500 에러 진입");
                return "error/500";
            }
        }
        */
        return "error/err";
    }
	
}
