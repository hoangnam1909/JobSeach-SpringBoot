package com.nhn.service.impl;

import com.nhn.entity.Company;
import com.nhn.entity.CompanyIndustry;
import com.nhn.entity.User;
import com.nhn.mapper.CompanyMapper;
import com.nhn.model.request.UpdateCompanyRequest;
import com.nhn.repository.CompanyIndustryRepository;
import com.nhn.repository.CompanyRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyIndustryRepository companyIndustryRepository;

    @Override
    @Transactional
    public Company update(String companyUsername, UpdateCompanyRequest request) {
        try {
            User user = userRepository.findUserByUsername(companyUsername);

            if (user == null)
                return null;

            Optional<Company> company = companyRepository.findById(user.getCompany().getId());
            if (company.isPresent()) {
                Company companyUpdating = company.get();

                companyIndustryRepository.deleteAllByCompanyId(companyUpdating.getId());
                for (Integer industryId : request.getIndustryId())
                    companyIndustryRepository.save(new CompanyIndustry(companyUpdating.getId(), industryId));

                return companyRepository.save(companyMapper.toEntityUpdate(company.get(), request));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return null;
    }

}
