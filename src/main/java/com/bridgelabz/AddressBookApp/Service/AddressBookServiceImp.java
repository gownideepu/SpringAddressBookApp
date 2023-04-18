package com.bridgelabz.AddressBookApp.Service;

import com.bridgelabz.AddressBookApp.Exception.AddressBookCustomException;
import com.bridgelabz.AddressBookApp.Exception.AddressBookException;
import com.bridgelabz.AddressBookApp.Util.JWTToken;
import com.bridgelabz.AddressBookApp.dto.AddressBookDto;
import com.bridgelabz.AddressBookApp.dto.ResponseDto;
import com.bridgelabz.AddressBookApp.model.AddressBookData;
import com.bridgelabz.AddressBookApp.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookServiceImp implements AddressBookService {
    List<AddressBookData> list = new ArrayList<>();
    @Autowired
    private MyRepository myRepository;
    @Autowired
    private JWTToken jwtToken;

    @Override
    public ResponseDto addAddress(AddressBookDto addressBookDto) {
        AddressBookData addressBookData = new AddressBookData(addressBookDto);
        myRepository.save(addressBookData);
        String token=jwtToken.createToken(addressBookData.getId());
        list.add(addressBookData);
        ResponseDto responseDto=new ResponseDto(token,addressBookData);
        return responseDto;
    }

    @Override
    public AddressBookData getAddressById(int id) {
        return myRepository.findById(id).orElseThrow(() -> new AddressBookCustomException(" Employee Not found .. wih id: "+ id));
    }

    @Override
    public AddressBookData UpdateAddress(int id, AddressBookDto addressBookDto) {
        AddressBookData addressBookData=this.getAddressById(id);
        addressBookData.updateAddressBookData(addressBookDto);
        return myRepository.save(addressBookData);
    }

    @Override
    public void delete(int id) {
        AddressBookData addressBookData =this.getAddressById(id);
        myRepository.delete(addressBookData);
    }
    @Override
    public List<AddressBookData> getAllData() {
        return myRepository.findAll();
    }

    @Override
    public AddressBookData getdataByToken(String token) {
        int id=jwtToken.decodeToken(token);
        return myRepository.findById(id).orElseThrow(() -> new AddressBookCustomException("Employee Not found :- "+id));
    }
    }




