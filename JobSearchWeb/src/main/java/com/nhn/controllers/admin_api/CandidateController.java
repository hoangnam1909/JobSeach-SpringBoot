package com.nhn.controllers.admin_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nhn.Util.CandidateJSONUtils;
import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.model.Candidate;
import com.nhn.model.Language;
import com.nhn.model.Skill;
import com.nhn.model.User;
import com.nhn.repository.CandidateRepository;
import com.nhn.repository.LanguageRepository;
import com.nhn.repository.SkillRepository;
import com.nhn.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
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
    private CandidateJSONUtils candidateJSONUtils;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private SkillRepository skillRepository;

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

    @PostMapping("")
    ResponseEntity<RespondObject> insert(@RequestBody String requestStr) {

        Gson gsonBuilder = new GsonBuilder().create();

        JSONObject jsonObject = new JSONObject(requestStr);
        String candidateStr = jsonObject.getJSONObject("candidate").toString();
        Candidate candidate = gsonBuilder.fromJson(candidateStr, Candidate.class);

        Candidate candidateSaved = candidateRepository.save(candidate);

        System.err.println(candidate.getClass());
        System.err.println(candidate.getLinkedin());

        JSONArray languagesArr = jsonObject.getJSONArray("languages");
        if (!languagesArr.isEmpty()){
            List<Language> languageList = candidateJSONUtils.toListLanguages(languagesArr);

            for (Language language : languageList){
                language.setCandidate(candidateSaved);
                languageRepository.save(language);
            }
        }

        JSONArray skillsArr = jsonObject.getJSONArray("skills");
        if (!skillsArr.isEmpty()){
            List<Skill> skillList = candidateJSONUtils.toListSkills(skillsArr);

            for (Skill skill : skillList){
                skill.setCandidate(candidateSaved);
                skillRepository.save(skill);
            }
        }

//        System.err.println(arr.getClass());
//        System.err.println(arr.isEmpty());

        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("OK", "Save User successfully", "")
        );
    }

}
