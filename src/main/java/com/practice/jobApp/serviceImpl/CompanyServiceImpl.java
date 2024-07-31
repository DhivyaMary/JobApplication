package com.practice.jobApp.serviceImpl;

import com.practice.jobApp.entity.Company;
import com.practice.jobApp.repository.CompanyRepository;
import com.practice.jobApp.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company updateCompany(Company company) {
        Optional<Company> companyObj = companyRepository.findById(company.getId());
        if (companyObj.isPresent()) {
            Company companyOb = companyObj.get();
            companyOb.setName(company.getName());
            companyOb.setCompanyDesc(company.getCompanyDesc());
            companyOb.setCompanyLoc(company.getCompanyLoc());
            companyRepository.save(companyOb);
            return companyOb;
        }
        return null;
    }

    @Override
    public Boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
