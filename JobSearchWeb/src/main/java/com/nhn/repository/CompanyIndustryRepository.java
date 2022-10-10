package com.nhn.repository;

import com.nhn.entity.CompanyIndustry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyIndustryRepository extends JpaRepository<CompanyIndustry, Integer> {

    List<CompanyIndustry> findAllByCompanyIdAndIndustryId(int companyId, int industryId);

    void deleteAllByCompanyId(Integer companyId);

}
