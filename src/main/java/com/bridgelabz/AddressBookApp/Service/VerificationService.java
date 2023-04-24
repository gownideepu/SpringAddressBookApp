package com.bridgelabz.AddressBookApp.Service;

import com.bridgelabz.AddressBookApp.dto.AddressBookDto;
import com.bridgelabz.AddressBookApp.dto.Login;
import com.bridgelabz.AddressBookApp.dto.Verification;

public interface VerificationService {
    String register(AddressBookDto addressBookDto);
    String validate(Verification validation);
    String login(Login login);

    String forgotpassword(String email);

    String resetpassword(String email, String password,long otp);


}
