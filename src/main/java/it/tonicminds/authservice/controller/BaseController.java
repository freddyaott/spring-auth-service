package it.tonicminds.authservice.controller;

import com.mongodb.MongoException;
import it.tonicminds.authservice.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class BaseController {

  Logger log = LoggerFactory.getLogger("BaseController");

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorInfo handleBadRequestException(BadRequestException e){
    ErrorInfo error = new ErrorInfo();
    error.setMessage(e.getMessage());
    error.setStatusCode("400");
    log.error("generic exception: "+e.getClass()+" "+e.getMessage());
    return error;
  }


  @ExceptionHandler(ForbiddenException.class)
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public ErrorInfo handleForbiddenException(ForbiddenException e){
    ErrorInfo error = new ErrorInfo();
    error.setMessage(e.getMessage());
    error.setStatusCode("403");
    log.error("generic exception: "+e.getClass()+" "+e.getMessage());
    return error;
  }


  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorInfo handleNotFoundException(NotFoundException e){
    ErrorInfo error = new ErrorInfo();
    error.setMessage(e.getMessage());
    error.setStatusCode("404");
    log.error("generic exception: "+e.getClass()+" "+e.getMessage());
    return error;
  }

  @ExceptionHandler(ConflictException.class)
  @ResponseStatus(value = HttpStatus.CONFLICT)
  public ErrorInfo handleConflictException(ConflictException e, HttpServletRequest request){
    ErrorInfo error = new ErrorInfo();
    error.setMessage(e.getMessage());
    error.setStatusCode("409");
    error.setUrl(request.getRequestURI());
    log.error("generic exception: "+e.getClass()+" "+e.getMessage());
    return error;
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public ErrorInfo handleBadCredentialsException(BadCredentialsException e){
    ErrorInfo error = new ErrorInfo();
    error.setMessage(e.getMessage());
    error.setStatusCode("403");
    //      writeLog(e.getMessage(),"409");
    return error;
  }

  @ExceptionHandler(MongoException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorInfo handleMongoException(MongoException e){
    ErrorInfo error = new ErrorInfo();
    error.setMessage("Internal server error: "+e.getMessage());
    error.setStatusCode("500");
    //		writeLog(e.getMessage(),"500");
    return error;
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorInfo handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
    ErrorInfo error = new ErrorInfo();
    error.setMessage(e.getMessage());
    error.setStatusCode("400");
    log.error("generic exception: "+e.getClass()+" "+e.getMessage());
    //		writeLog(e.getMessage(),"400");
    return error;
  }


  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
  public ErrorInfo handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e){
    ErrorInfo error = new ErrorInfo();
    error.setStatusCode("400");
    error.setMessage(e.getMessage());
    log.error("generic exception: "+e.getClass()+" "+e.getMessage());
    //		writeLog(e.getMessage(),"400");
    return error;
  }



  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public ErrorInfo handleAccessDeniedException(AccessDeniedException e){
    ErrorInfo error = new ErrorInfo();
    error.setStatusCode("403");
    error.setMessage(e.getMessage());
    //		writeLog(e.getMessage(),"403");
    return error;
  }

  @ExceptionHandler(FileNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorInfo handleFileNotFoundException(FileNotFoundException e){
    ErrorInfo error = new ErrorInfo();
    error.setStatusCode("404");
    error.setMessage(e.getMessage());
    //		writeLog(e.getMessage(),"404");
    return error;
  }

  @ExceptionHandler(IOException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorInfo handleIOException(IOException e){
    ErrorInfo error = new ErrorInfo();
    error.setStatusCode("400");
    error.setMessage(e.getMessage());
    log.error("generic exception: "+e.getClass()+" "+e.getMessage());
    return error;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorInfo handleGenericException(Exception e){
    ErrorInfo error = new ErrorInfo();
    error.setStatusCode("400");
    error.setMessage(e.getMessage());
    log.error("generic exception: "+e.getClass()+" "+e.getMessage());
    return error;
  }


}
