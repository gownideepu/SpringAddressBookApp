package com.bridgelabz.AddressBookApp.controller;

import com.bridgelabz.AddressBookApp.Service.VerificationService;
import com.bridgelabz.AddressBookApp.dto.AddressBookDto;
import com.bridgelabz.AddressBookApp.dto.Login;
import com.bridgelabz.AddressBookApp.dto.Verification;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginContext;

@RestController
public class VerificationController {
    @Autowired
    private VerificationService verificationService;
    @PostMapping("/register")
    public String register(@RequestBody AddressBookDto addressBookDto){
        return verificationService.register(addressBookDto);
    }
    @PutMapping("/validate")
    public String validate(@RequestBody Verification verification) {

        return verificationService.validate(verification);
    }
    @PostMapping("/Login")
    public String login (@RequestBody Login login){
        return verificationService.login(login);
    }
    @PostMapping("/forgot")
    public String  forgotPassword(@RequestParam String email){
        return verificationService.forgotpassword(email);
    }
    @PutMapping("/Reset")
    public String resetPassword(@RequestParam String Email,@RequestParam String password,@RequestParam long otp){
        return verificationService.resetpassword(Email,password,otp);
    }
}
