package com.example.Hyderabad_company_Assignment.Service;

import com.example.Hyderabad_company_Assignment.Entity.Employee;
import com.example.Hyderabad_company_Assignment.Repository.EmployeeRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    private final String FILE_PATH = "C:\\Users\\Lenovo\\Downloads\\";



    public void save(MultipartFile file) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(FILE_PATH + file.getOriginalFilename()));

        List<String[]> rows = reader.readAll();

        for (String[] row : rows) {

            Employee employee=new Employee();
            employee.setId(row[0]);
            employee.setFirst_Name(row[1]);
            employee.setLast_Name(row[2]);
            employee.setCountry(row[3]);
            employee.setCompany(row[4]);
            employeeRepository.save(employee);


        }

    }


    }

