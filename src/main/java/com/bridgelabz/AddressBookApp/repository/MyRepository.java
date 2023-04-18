package com.bridgelabz.AddressBookApp.repository;

import com.bridgelabz.AddressBookApp.model.AddressBookData;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends org.springframework.data.jpa.repository.JpaRepository<AddressBookData,Integer> {
}
