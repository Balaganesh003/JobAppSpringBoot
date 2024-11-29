package com.balaganesh.jobapp.company;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;


    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;

    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PutMapping("/{id}")
    public Boolean UpdateCompanyById(@PathVariable Long id, @RequestBody Company updatedCompany) {
        return companyService.UpdateCompanyById(id, updatedCompany);
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCompanyById(@PathVariable Long id) {

        return companyService.deleteCompanyById(id);
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }


}
