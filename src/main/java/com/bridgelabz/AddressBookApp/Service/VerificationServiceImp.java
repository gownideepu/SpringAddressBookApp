package com.bridgelabz.AddressBookApp.Service;

import com.bridgelabz.AddressBookApp.Exception.AddressBookCustomException;
import com.bridgelabz.AddressBookApp.Util.EmailService;
import com.bridgelabz.AddressBookApp.Util.JWTToken;
import com.bridgelabz.AddressBookApp.dto.AddressBookDto;
import com.bridgelabz.AddressBookApp.dto.Login;
import com.bridgelabz.AddressBookApp.dto.Verification;
import com.bridgelabz.AddressBookApp.model.AddressBookData;
import com.bridgelabz.AddressBookApp.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationServiceImp implements VerificationService {
    @Autowired
    private MyRepository myRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JWTToken jwtToken;

    @Override
    public String register(AddressBookDto addressBookDto) {
        String email=addressBookDto.getEmail();
        System.out.println("the email is "+email);
        String mail=myRepository.findEmail(email);
        System.out.println("the-"+mail+"the int mail is ");
        if(mail!=null){
            return "Enter the Email id ";
        }else {
            AddressBookData addressBookData = new AddressBookData(addressBookDto);
            long generateOtp = (long) ((Math.random() * 9999) % 8998) + 1001;
            addressBookData.setOtp(generateOtp);
            myRepository.save(addressBookData);
            emailService.sendEmail(addressBookData.getEmail(), "The data added successfully ", "hi  .." + addressBookData.getName() + "\n your data added successfully " + "\n your otp is  <- " + generateOtp + " ->");
            return "otp generated successfully........ ";
        }
    }
    @Override
    public String validate(Verification validation) {
        String email=validation.getEmail();
        int id=myRepository.findByEmail(email);
        System.out.println("the id "+id);
        Optional<AddressBookData> data=myRepository.findById(id);
        if(validation.getOtp()==data.get().getOtp()){
            String token=jwtToken.createToken(id);
            data.get().setVerifyotp(true);
            data.get().setToken(token);
            myRepository.save(data.get());
            return "validation done............ " + data.get().getEmail() ;
        }
        else {
            return "validation not done";
        }
    }

    @Override
    public String resetpassword(String email, String password,long otp) {
        String mail = myRepository.findEmail(email);
        if (mail==null){
            return "email is not present";
        }
        else{
            int id =myRepository.findByEmail(email);
            long actualOtp= myRepository.findOtpByEmail(email);
            System.out.println(actualOtp+"otp");
            Optional<AddressBookData> data = myRepository.findById(id);
            if ((data.isPresent() && (otp==actualOtp))) {
                data.get().setPassword(password);
                data.get().setVerifyotp(true);
                myRepository.save(data.get());
                return "The Password reset successfully..........";
            } else {
                return "password validation not completed ";
            }
        }
    }
    @Override
    public String login(Login login) {
        String email =login.getEmail();
        String password=login.getPassword();
        String verifyPassword=myRepository.getPassword(email);
        if (password.equals(verifyPassword)){
            return "login successfully..... ";
        }
        else{
            return" check the email and password";
        }
    }

    @Override
    public String forgotpassword(String email) {
        int id = myRepository.findByEmail(email);
        AddressBookData addressBookData = myRepository.findById(id).orElseThrow(() -> new AddressBookCustomException(" Employee Not found .. wih id: " + id));
        Optional<AddressBookData> data = myRepository.findById(id);
        if (id <= 0) {
            return "The mail is not Registered..............";
        } else {
            long generateOtp = (long) ((Math.random() * 9999) % 8998) + 1001;
            data.get().setOtp(generateOtp);
            data.get().setVerifyotp(false);
            myRepository.save(data.get());
            emailService.sendEmail(addressBookData.getEmail(), "The data added successfully ", "hi...." + addressBookData.getName() + "\n your data added successfully " + "\n your otp is  <- " + generateOtp + " ->");
            return "The forgot password otp sent " ;
        }
    }
}