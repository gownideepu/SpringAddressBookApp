package com.bridgelabz.AddressBookApp.repository;

import com.bridgelabz.AddressBookApp.model.AddressBookData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends org.springframework.data.jpa.repository.JpaRepository<AddressBookData,Integer> {
    @Query(value = "select id from address_book_data where email= :email",nativeQuery=true)
    int findByEmail(String email);

    @Query(value = "select otp from address_book_data where email= :email",nativeQuery=true)
    long findOtpByEmail(String email);
    @Query(value = "select password from address_book_data where email= :email",nativeQuery = true)
    String getPassword(String email);

    @Query(value = "select email from address_book_data where email= :email",nativeQuery = true)
    String findEmail(String email);
}
