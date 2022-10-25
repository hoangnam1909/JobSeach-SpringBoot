package com.nhn.controllers.company;

import com.nhn.util.JwtUtils;
import com.nhn.common.RespondObject;
import com.nhn.entity.Company;
import com.nhn.model.request.UpdateCompanyRequest;
import com.nhn.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(path = "/company/api")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest servletRequest;

    /*
        Nhà tuyển dụng cập nhật thông tin nhà tuyển dụng
    */
    @PutMapping("")
    ResponseEntity<RespondObject> updateCompany(@RequestBody UpdateCompanyRequest request) {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String username = jwtUtils.extractUsername(accessToken);

            Company company = companyService.update(username, request);

            return company != null ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("OK", "Save company successfully", company))
                    :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new RespondObject("Fail", "Save company failed", ""));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Error", "Error message", ex.getMessage()));
        }
    }

}
