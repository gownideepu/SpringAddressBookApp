package com.bridgelabz.AddressBookApp.dto;

import lombok.Data;

@Data
public class Verification {
    private String email;
    private long otp;
}
