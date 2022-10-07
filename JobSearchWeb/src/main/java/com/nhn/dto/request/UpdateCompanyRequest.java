package com.nhn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyRequest {

    private String companyName;
    private int companySize;
    private String contactName;
    private String contactTel;
    private String contactEmail;
    private String contactAddress;
    private String introduction;
    private int foundedYear;
    private String headquarters;
    private String link;

}
