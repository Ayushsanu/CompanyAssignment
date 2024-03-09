package com.example.Hyderabad_company_Assignment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    public String id;
    public String First_Name;
    public String Last_Name;
    public String Country;
    public String Company;

}
