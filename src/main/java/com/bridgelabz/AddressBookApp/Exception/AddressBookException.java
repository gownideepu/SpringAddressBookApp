package com.bridgelabz.AddressBookApp.Exception;

import com.bridgelabz.AddressBookApp.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class AddressBookException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto>handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
        List<ObjectError>errorList=exception.getBindingResult().getAllErrors();
        List<String>errorMessage=errorList.stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
        ResponseDto responceDto=new ResponseDto("Exception While handling Rest Api call ,",errorMessage);
        return  new ResponseEntity<>(responceDto, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> HttpMessageNotReadableException(HttpMessageNotReadableException exception){
        ResponseDto responceDto=new ResponseDto("The Date shoid be in dd-mm-yyyy formate",exception.getMessage());
        return new ResponseEntity<>(responceDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AddressBookCustomException.class)
    public ResponseEntity<ResponseDto> handleEmployeeException(AddressBookCustomException exception){
        ResponseDto responceDto=new ResponseDto("Exception While handling Rest Api call ,",exception.getMessage());
        return new ResponseEntity<>(responceDto, HttpStatus.BAD_REQUEST);
    }

}
