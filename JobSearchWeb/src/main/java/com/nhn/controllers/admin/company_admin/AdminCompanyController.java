package com.nhn.controllers.admin.company_admin;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.entity.Company;
import com.nhn.entity.User;
import com.nhn.model.request.UpdateCompanyRequest;
import com.nhn.repository.UserRepository;
import com.nhn.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin/api/company")
public class AdminCompanyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyService companyService;

    @PutMapping("/update/{company-user-id}")
    ResponseEntity<RespondObject> updateCompany(@PathVariable(name = "company-user-id") int companyUserId,
                                                @RequestBody UpdateCompanyRequest request) {

        try {
            User user = userRepository.findUserByIdAndRole(companyUserId, Constant.USER_ROLE.COMPANY);
            if (user == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject(HttpStatus.NOT_FOUND.name(), "No company found", ""));

            Company company = companyService.update(user.getUsername(), request);

            return company != null ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject(HttpStatus.OK.name(), "Save company info successfully", company))
                    :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new RespondObject(HttpStatus.BAD_REQUEST.name(), "Save company info failed", ""));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Error message", ex.getMessage()));
        }
    }

}
