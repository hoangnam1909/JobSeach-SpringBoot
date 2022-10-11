package com.nhn.service.impl;

import com.nhn.entity.*;
import com.nhn.mapper.CandidateMapper;
import com.nhn.model.request.*;
import com.nhn.repository.*;
import com.nhn.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private ReferenceRepository referenceRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private TalentRepository talentRepository;

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public Candidate updateCandidate(CandidateRequest candidateRequest) {

        User user = userRepository.findUserByUsername(candidateRequest.getCandidateUsername());
        if (user == null)
            return null;

        Candidate candidate = candidateMapper.toEntity(user.getCandidate(), candidateRequest);
        if (candidate == null)
            return null;
        try {
            // languages
            if (candidateRequest.getLanguageRequests() != null) {
                List<Language> languageList = new ArrayList<>();
                languageRepository.deleteAll(candidate.getLanguages());
                for (LanguageRequest languageRequest : candidateRequest.getLanguageRequests())
                    languageList.add(languageRepository.
                            save(new Language(languageRequest.getName(), languageRequest.getDescription(), candidate)));
                candidate.setLanguages(languageList);
            }

            // qualifications
            if (candidateRequest.getQualificationRequests() != null) {
                List<Qualification> qualificationList = new ArrayList<>();
                qualificationRepository.deleteAll(candidate.getQualifications());
                for (QualificationRequest qualificationRequest : candidateRequest.getQualificationRequests())
                    qualificationList.add(qualificationRepository.
                            save(new Qualification(qualificationRequest.getName(), candidate)));
                candidate.setQualifications(qualificationList);
            }

            // reference
            if (candidateRequest.getReferenceRequests() != null) {
                List<Reference> referenceList = new ArrayList<>();
                referenceRepository.deleteAll(candidate.getReferences());
                for (ReferenceRequest referenceRequest : candidateRequest.getReferenceRequests())
                    referenceList.add(referenceRepository.
                            save(new Reference(referenceRequest.getName(), referenceRequest.getLink(), candidate)));
                candidate.setReferences(referenceList);
            }

            // skills
            if (candidateRequest.getSkillRequests() != null) {
                List<Skill> skillList = new ArrayList<>();
                skillRepository.deleteAll(candidate.getSkills());
                for (SkillRequest skillRequest : candidateRequest.getSkillRequests())
                    skillList.add(skillRepository.
                            save(new Skill(skillRequest.getName(), skillRequest.getLevel(), candidate)));
                candidate.setSkills(skillList);
            }

            // talents
            if (candidateRequest.getTalentRequests() != null) {
                List<Talent> talentList = new ArrayList<>();
                talentRepository.deleteAll(candidate.getTalents());
                for (TalentRequest talentRequest : candidateRequest.getTalentRequests())
                    talentList.add(talentRepository.
                            save(new Talent(talentRequest.getContent(), candidate)));
                candidate.setTalents(talentList);
            }

            // work experience
            if (candidateRequest.getWorkExperienceRequests() != null) {
                List<WorkExperience> workExperienceList = new ArrayList<>();
                workExperienceRepository.deleteAll(candidate.getWorkExperiences());
                for (WorkExperienceRequest workExperienceRequest : candidateRequest.getWorkExperienceRequests())
                    workExperienceList.add(workExperienceRepository.
                            save(new WorkExperience(workExperienceRequest.getFromDate(),
                                    workExperienceRequest.getToDate(),
                                    workExperienceRequest.getContent(),
                                    workExperienceRequest.getPosition(),
                                    candidate)
                            ));
                candidate.setWorkExperiences(workExperienceList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return candidateRepository.save(candidate);
    }

}
