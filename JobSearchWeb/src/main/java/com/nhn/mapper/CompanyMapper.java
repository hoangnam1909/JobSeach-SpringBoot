package com.nhn.mapper;

import com.nhn.dto.request.UpdateCompanyRequest;
import com.nhn.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntityUpdate(Company company, UpdateCompanyRequest request) {

        company.setCompanyName(request.getCompanyName());
        company.setCompanySize(request.getCompanySize());
        company.setContactName(request.getContactName());
        company.setContactTel(request.getContactTel());
        company.setContactEmail(request.getContactEmail());
        company.setContactAddress(request.getContactAddress());
        company.setIntroduction(request.getIntroduction());
        company.setFoundedYear(request.getFoundedYear());
        company.setHeadquarters(request.getHeadquarters());
        company.setLink(request.getLink());

        return company;
    }

}
