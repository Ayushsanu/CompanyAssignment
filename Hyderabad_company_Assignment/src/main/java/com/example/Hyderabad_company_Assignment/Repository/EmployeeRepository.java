package com.example.Hyderabad_company_Assignment.Repository;

import com.example.Hyderabad_company_Assignment.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {
}
