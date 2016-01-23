package com.humanize.server.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.humanize.server.data.CommonResponse;

@ControllerAdvice
public class CommonExceptionHandler {
   
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
      public CommonResponse error(UserNotFoundException e){
         CommonResponse response = new CommonResponse();
         response.setErrorCode(e.getErrorCode());
         response.setErrorMsg(e.getErrorMsg());
         return response;
      }
  }