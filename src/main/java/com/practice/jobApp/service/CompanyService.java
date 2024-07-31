package com.practice.jobApp.service;

import com.practice.jobApp.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company createCompany(Company company);

    Optional<Company> getCompanyById(Long id);

    Company updateCompany(Company company);

    Boolean deleteCompany(Long id);
}
