package com.nhn.controllers.company_api;


import com.nhn.common.RespondObject;
import com.nhn.entity.CompanyIndustry;
import com.nhn.repository.CompanyIndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/company/api/company-industry")
public class CompanyIndustryController {

    @Autowired
    private CompanyIndustryRepository companyIndustryRepository;

    @PostMapping("")
    ResponseEntity<RespondObject> insert(@RequestBody CompanyIndustry companyIndustry) {
        List<CompanyIndustry> companyIndustries = companyIndustryRepository
                .findAllByCompanyIdAndIndustryId(companyIndustry.getCompanyId(), companyIndustry.getIndustryId());

        if (companyIndustries.size() != 0)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new RespondObject("Failed", "Company industry already exists", "")
            );

        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Ok", "Save company industry successfully", companyIndustryRepository.save(companyIndustry))
        );
    }

}
