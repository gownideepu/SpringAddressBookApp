package com.bridgelabz.AddressBookApp.controller;

import com.bridgelabz.AddressBookApp.Service.AddressBookService;
import com.bridgelabz.AddressBookApp.dto.AddressBookDto;
import com.bridgelabz.AddressBookApp.dto.ResponseDto;
import com.bridgelabz.AddressBookApp.dto.Verification;
import com.bridgelabz.AddressBookApp.model.AddressBookData;
import jakarta.validation.Valid;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MyController {
    @Autowired
    private AddressBookService addressBookService;
    @PostMapping("/add")
    public ResponseDto addAddress(@Valid @RequestBody AddressBookDto addressBookDto){
       return addressBookService.addAddress(addressBookDto);
    }

    @GetMapping("/{id}")
    public ResponseDto getDataByid(@PathVariable int id){
        AddressBookData addressBookData = addressBookService.getAddressById(id);
        ResponseDto responceDto = new ResponseDto("Data is",addressBookData);
        return responceDto;
    }
    @PutMapping("/update/{id}")
    public ResponseDto update(@RequestBody AddressBookDto addressBookDto,@PathVariable int id){
        AddressBookData addressBookData =addressBookService.UpdateAddress(id,addressBookDto);
        ResponseDto responceDto = new ResponseDto("Data is",addressBookData);
        return responceDto;
    }
    @DeleteMapping("/delete/{id}")
    public String Delete(@PathVariable int id){
        addressBookService.delete(id);
        return "Deleted the data from the id"+id;
    }

    @GetMapping("/")
    public ResponseDto getAllData(){
        List<AddressBookData> data=addressBookService.getAllData();
        ResponseDto responceDto =new ResponseDto("The All Employees ",data);
        return responceDto;
    }
    @GetMapping("/token/{token}")
    public ResponseEntity<ResponseDto> getDataByToken(@PathVariable String token){
        AddressBookData addressBookData=addressBookService.getdataByToken(token);
        ResponseDto responceDto = new ResponseDto("Data for the token is ",addressBookData);
        return new ResponseEntity<>(responceDto, HttpStatus.CREATED);
    }
    @GetMapping("/DeletedData")
    public ResponseDto getDeletedData(){
        List<AddressBookData> data=addressBookService.getdeleteData();
        ResponseDto responceDto=new ResponseDto("The data was deleted ",data);
        return responceDto;
    }
}

