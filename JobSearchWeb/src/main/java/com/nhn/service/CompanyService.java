package com.nhn.service;

import com.nhn.model.request.UpdateCompanyRequest;
import com.nhn.entity.Company;

public interface CompanyService {

    Company update(String username, UpdateCompanyRequest request);

}