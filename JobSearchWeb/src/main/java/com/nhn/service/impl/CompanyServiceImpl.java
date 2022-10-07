package com.nhn.service.impl;

import com.nhn.dto.request.UpdateCompanyRequest;
import com.nhn.entity.Company;
import com.nhn.entity.User;
import com.nhn.mapper.CompanyMapper;
import com.nhn.repository.CompanyRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company update(String username, UpdateCompanyRequest request) {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            return null;
        else {
            Optional<Company> company = companyRepository.findById(user.getCompany().getId());
            if (company.isPresent()){
                return companyRepository.save(companyMapper.toEntityUpdate(company.get(), request));
            }
        }

        return null;
    }

}
