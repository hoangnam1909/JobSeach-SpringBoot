package com.nhn.service;

import com.nhn.entity.Company;
import com.nhn.model.request.UpdateCompanyRequest;

public interface CompanyService {

    Company update(String companyUsername, UpdateCompanyRequest request);

}
