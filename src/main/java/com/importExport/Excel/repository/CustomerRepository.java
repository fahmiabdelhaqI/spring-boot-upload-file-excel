package com.importExport.Excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.importExport.Excel.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
