package com.nhn.controllers.admin_api;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.dto.request.*;
import com.nhn.model.Candidate;
import com.nhn.model.User;
import com.nhn.repository.UserRepository;
import com.nhn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/admin/api/v1/candidate")
public class CandidateController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private QualificationService qualificationService;

    @Autowired
    private ReferenceService referenceService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private TalentService talentService;

    @Autowired
    private WorkExperienceService workExperienceService;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {

        List<User> candidateUsers = userRepository.findUserByRole(Constant.USER_ROLE.CANDIDATE);
        return candidateUsers.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Candidate found", candidateUsers)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Fail", "No candidate found", "")
                );
    }

    @PutMapping("/update-candidate")
    ResponseEntity<RespondObject> updateCandidate(@RequestBody CandidateRequest candidateRequest) {

        try {
            Candidate candidateSaved = candidateService.updateCandidate(candidateRequest);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("OK", "Save candidate successfully", candidateSaved.getId()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new RespondObject("Failed", "Save candidate failed", ex));
        }
    }


    // ADD DETAIL
    @PostMapping("/add-languages")
    ResponseEntity<RespondObject> addLanguages(@RequestBody List<LanguageRequest> languageRequestList) {

        try {
            languageService.add(languageRequestList);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("OK", "Save languages to candidate successfully", ""));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new RespondObject("Failed", "Error", ex));
        }
    }

    @PostMapping("/add-qualifications")
    ResponseEntity<RespondObject> addQualifications(@RequestBody List<QualificationRequest> qualificationRequestList) {

        try {
            qualificationService.add(qualificationRequestList);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("OK", "Save qualifications to candidate successfully", ""));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new RespondObject("Failed", "Error", ex));
        }
    }

    @PostMapping("/add-references")
    ResponseEntity<RespondObject> addReferences(@RequestBody List<ReferenceRequest> referenceRequestList) {
        System.err.println(referenceRequestList.get(0).getLink());
        try {
            referenceService.add(referenceRequestList);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("OK", "Save references to candidate successfully", ""));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new RespondObject("Failed", "Error", ex));
        }
    }

    @PostMapping("/add-skills")
    ResponseEntity<RespondObject> addSkills(@RequestBody List<SkillRequest> skillRequestList) {

        try {
            skillService.add(skillRequestList);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("OK", "Save skills to candidate successfully", ""));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new RespondObject("Failed", "Error", ex));
        }
    }

    @PostMapping("/add-talents")
    ResponseEntity<RespondObject> addTalents(@RequestBody List<TalentRequest> talentRequestList) {

        try {
            talentService.add(talentRequestList);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("OK", "Save talents to candidate successfully", ""));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new RespondObject("Failed", "Error", ex));
        }
    }

    @PostMapping("/add-work-exps")
    ResponseEntity<RespondObject> addWorkExperiences(@RequestBody List<WorkExperienceRequest> workExperienceRequestList) {

        try {
            workExperienceService.add(workExperienceRequestList);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("OK", "Save work experiences to candidate successfully", ""));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new RespondObject("Failed", "Error", ex));
        }
    }

}
