package com.balaganesh.jobapp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Boolean UpdateCompanyById(Long id, Company updatedCompany);
    Company addCompany(Company company);
    Boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);
}
