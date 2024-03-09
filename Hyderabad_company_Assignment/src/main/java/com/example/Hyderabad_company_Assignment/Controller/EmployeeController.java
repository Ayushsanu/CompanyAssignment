package com.example.Hyderabad_company_Assignment.Controller;

import com.example.Hyderabad_company_Assignment.Entity.Employee;
import com.example.Hyderabad_company_Assignment.Repository.EmployeeRepository;
import com.example.Hyderabad_company_Assignment.Service.EmployeeService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;

    private final String FILE_PATH = "C:\\Users\\Lenovo\\Downloads\\";
    @RestController
    public class FilesController {


        @PostMapping("/uploadFilesIntoDB")
        public ResponseEntity<String> storeFilesIntoDB(@RequestParam MultipartFile file) throws IOException, CsvException, CsvException {
            employeeService.save(file);

            return ResponseEntity.status(HttpStatus.OK).body("Success");
        }

        @GetMapping("/employee/{id}")
        public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);

            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                return ResponseEntity.ok().body(employee);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @GetMapping("/readRow")
        public ResponseEntity<String[]> readRow(@RequestParam("file") MultipartFile file,
                                                @RequestParam("rowIndex") int rowIndex) {
            try {
                CSVReader reader = new CSVReader(new FileReader(FILE_PATH + file.getOriginalFilename()));
                List<String[]> rows = reader.readAll();

                if (rowIndex >= 0 && rowIndex < rows.size()) {
                    String[] rowData = rows.get(rowIndex);
                    return ResponseEntity.ok().body(rowData);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String[]{"Invalid row index"});
                }
            } catch (IOException | CsvException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new String[]{"Error occurred while reading CSV"});
            }
        }
    }
}
