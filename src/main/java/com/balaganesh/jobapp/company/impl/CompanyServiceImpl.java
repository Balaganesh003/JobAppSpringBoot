package com.balaganesh.jobapp.company.impl;

import com.balaganesh.jobapp.company.Company;
import com.balaganesh.jobapp.company.CompanyRepository;
import com.balaganesh.jobapp.company.CompanyService;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Boolean UpdateCompanyById(Long id, Company updatedCompany) {

        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isEmpty()) {
            return false;
        }

        Company company = optionalCompany.get();
        company.setName(updatedCompany.getName());
        company.setDescription(updatedCompany.getDescription());
        company.setJobs(updatedCompany.getJobs());
        companyRepository.save(company);
        return true;
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

}
