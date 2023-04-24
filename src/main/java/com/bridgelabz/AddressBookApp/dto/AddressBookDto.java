package com.bridgelabz.AddressBookApp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressBookDto {
    @NotEmpty
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$",message = "The Name is notEmpty")
    private String name;
    @NotEmpty(message = "The Phone number is not empty")
    private String phoneNumber;
    @NotEmpty(message = "The mail is not empty")
    private String email;
    private String password;
//    private String token;
//    private boolean verifyotp;
//    private long otp;
}
