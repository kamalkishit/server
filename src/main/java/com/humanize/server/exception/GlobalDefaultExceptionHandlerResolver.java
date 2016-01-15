package com.humanize.server.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class GlobalDefaultExceptionHandlerResolver implements HandlerExceptionResolver, Ordered {

    private static final String REQUEST_UUID_HEADER = "X-REQUEST-UUID";

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandlerResolver.class);

    private MessageSource exceptionMessageSource;

    private MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

    public GlobalDefaultExceptionHandlerResolver(MessageSource exceptionMessageSource) {
        this.exceptionMessageSource = exceptionMessageSource;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ServiceError serviceError = new ServiceError();
        serviceError.setTimeStamp(System.currentTimeMillis());
        serviceError.setErrorId(UUID.randomUUID().toString());
        serviceError.setRequestId(httpServletRequest.getHeader(REQUEST_UUID_HEADER));

        if(e instanceof MethodArgumentNotValidException) {
            serviceError.setDetails(toErrors(((MethodArgumentNotValidException) e).getBindingResult()));
        } else if(e instanceof BindException) {
            serviceError.setDetails(toErrors(((BindException) e).getBindingResult()));
        } else if(e instanceof ServerException) {
            ServerException serviceException = (ServerException) e;
            if(serviceException.getErrorCode() != 0 || serviceException.getErrorMsg() != null) {
                List<ServiceErrorDetails> serviceErrorDetails = new ArrayList<ServiceErrorDetails>();
                serviceErrorDetails.add(new ServiceErrorDetails(String.valueOf(serviceException.getErrorCode()), 
                        serviceException.getErrorMsg()));
                serviceError.setDetails(serviceErrorDetails);
            }
        }

        int status = this.getHttpStatus(e);
        String reason = this.getErrorCode(e);

        serviceError.setReason(reason);
        httpServletResponse.setStatus(status);
        logError(status, e, serviceError);

        // respond to multipart requests with text/plain
        // required for file upload browser support
        boolean multipart = httpServletRequest.getContentType() != null && httpServletRequest.getContentType().contains("multipart/form-data");

        try {
            converter.write(serviceError, multipart ? MediaType.TEXT_PLAIN : MediaType.APPLICATION_JSON, new ServletServerHttpResponse(httpServletResponse));
        } catch (IOException ioException) {
            logger.error("Unable to send error for status={} serviceError={} exception={}", status, serviceError, e.getMessage(), ioException);
        }

        // Make sure model and view is empty to prevent other handlers from being executed
        return new ModelAndView();
    }

    private void logError(int status, Exception e, ServiceError serviceError) {
        if(status >= 500) {
            logger.error("status={} serviceError={}", status, serviceError, e);
        } else {
            logger.info("status={} exception={} serviceError={}", status, e.getMessage(), serviceError);
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private List<ServiceErrorDetails> toErrors(BindingResult bindingResult) {
        List<ServiceErrorDetails> list = new ArrayList<ServiceErrorDetails>();
        if(bindingResult.hasFieldErrors()) {
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                list.add(new ServiceErrorDetails(fieldError.getField(), fieldError.getDefaultMessage()));
            }
        }
        if(bindingResult.hasGlobalErrors()) {
            for(ObjectError objectError : bindingResult.getGlobalErrors()) {
                list.add(new ServiceErrorDetails(null, objectError.getDefaultMessage()));
            }
        }
        return list;
    }

    private int getHttpStatus(Exception ex) {
        int status;
        try {
            status = HttpStatus.valueOf(this.exceptionMessageSource.getMessage(ex.getClass().getName() + ".httpStatus", new Object[0], "INTERNAL_SERVER_ERROR", Locale.US)).value();
        } catch (IllegalArgumentException var3) {
            status = Integer.valueOf(this.exceptionMessageSource.getMessage(ex.getClass().getName() + ".httpStatusCode", new Object[0], "500", Locale.US)).intValue();
        }

        return status;
    }

    private String getErrorCode(Exception ex) {
        return this.exceptionMessageSource.getMessage(ex.getClass().getName() + ".code", new Object[0], "InternalError", Locale.US);
    }

}
