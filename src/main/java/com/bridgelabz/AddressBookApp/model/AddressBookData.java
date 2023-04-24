package com.bridgelabz.AddressBookApp.model;

import com.bridgelabz.AddressBookApp.dto.AddressBookDto;
import com.bridgelabz.AddressBookApp.dto.ResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class AddressBookData {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String phoneNumber;
    private String Email;
    private String password;
    private long otp;
    private String token;
    private boolean verifyotp;
    public AddressBookData(AddressBookDto addressBookDto) {

        this.updateAddressBookData(addressBookDto);
    }
    public AddressBookData(String token,long otp){
        this.otp=otp;
        this.token=token;
    }
    public void updateAddressBookData(AddressBookDto addressBookDto){
        this.name=addressBookDto.getName();
        this.Email=addressBookDto.getEmail();
        this.password=addressBookDto.getPassword();
        this.phoneNumber= addressBookDto.getPhoneNumber();
    }

}
