package com.nhn.mapper;

import com.nhn.dto.request.CompanyRequest;
import com.nhn.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequest req) {
        Company company = new Company();

        company.setCompanyName(req.getCompanyName());
        company.setCompanySize(req.getCompanySize());
        company.setContactName(req.getContactName());
        company.setContactTel(req.getContactTel());
        company.setContactEmail(req.getContactEmail());
        company.setContactAddress(req.getContactAddress());
        company.setIntroduction(req.getIntroduction());
        company.setFoundedYear(req.getFoundedYear());
        company.setHeadquarters(req.getHeadquarters());
        company.setLink(req.getLink());

        return company;
    }

}
