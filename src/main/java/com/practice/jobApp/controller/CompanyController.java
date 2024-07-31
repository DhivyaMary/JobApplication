package com.practice.jobApp.controller;

import com.practice.jobApp.entity.Company;
import com.practice.jobApp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        if (!companies.isEmpty()) {
            return new ResponseEntity(companies, HttpStatus.OK);
        } else
            return new ResponseEntity("No companies Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@RequestParam Long id) {
        Optional<Company> optionalCompany = companyService.getCompanyById(id);
        if (optionalCompany.isPresent()) {
            return new ResponseEntity(optionalCompany, HttpStatus.OK);
        } else
            return new ResponseEntity("No companies Found for Id " + id, HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity createCompany(@RequestBody Company company) {
        Company companyObj = companyService.createCompany(company);
        return new ResponseEntity(companyObj, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateCompany(@RequestBody Company company) {
        Company companyObj = companyService.updateCompany(company);
        if (companyObj != null)
            return new ResponseEntity(companyObj, HttpStatus.OK);
        else
            return new ResponseEntity("No companies to Update", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        Boolean isDeleted = companyService.deleteCompany(id);
        if (isDeleted)
            return new ResponseEntity("Company Deleted Success", HttpStatus.OK);
        else
            return new ResponseEntity("Company Doesn't Exist", HttpStatus.NOT_FOUND);
    }
}
