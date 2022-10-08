package com.nhn.controllers.admin;

import com.nhn.common.RespondObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api")
public class AdminController {

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {

        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Ok", "Authed by jwt", "ADMIN PAGE"));
    }

}
