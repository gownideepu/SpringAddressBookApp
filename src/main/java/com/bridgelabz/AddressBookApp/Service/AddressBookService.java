package com.bridgelabz.AddressBookApp.Service;

import com.bridgelabz.AddressBookApp.dto.AddressBookDto;
import com.bridgelabz.AddressBookApp.dto.ResponseDto;
import com.bridgelabz.AddressBookApp.dto.Verification;
import com.bridgelabz.AddressBookApp.model.AddressBookData;
import jakarta.validation.Validation;

import java.util.List;

public interface AddressBookService {
    ResponseDto addAddress(AddressBookDto addressBookDto);

    AddressBookData getAddressById(int id);

    AddressBookData UpdateAddress(int id, AddressBookDto addressBookDto);

    void delete(int id);

    List<AddressBookData> getAllData();

    AddressBookData getdataByToken(String token);

    List<AddressBookData> getdeleteData();
    


}
