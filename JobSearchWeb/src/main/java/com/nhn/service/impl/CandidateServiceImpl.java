package com.nhn.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhn.entity.*;
import com.nhn.mapper.CandidateMapper;
import com.nhn.model.request.*;
import com.nhn.repository.*;
import com.nhn.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Candidate update(String username, CandidateRequest request, MultipartFile file) {

        User user = userRepository.findUserByUsername(username);
        if (user == null)
            return null;

        Candidate candidate = candidateMapper.toEntity(user.getCandidate(), request);
        if (candidate == null)
            return null;
        try {
            // languages
            if (request.getLanguageRequests() != null) {
                List<Language> languageList = new ArrayList<>();
                languageRepository.deleteAll(candidate.getLanguages());
                for (LanguageRequest languageRequest : request.getLanguageRequests())
                    languageList.add(languageRepository.
                            save(new Language(languageRequest.getName(), languageRequest.getDescription(), candidate)));
                candidate.setLanguages(languageList);
            }

            // qualifications
            if (request.getQualificationRequests() != null) {
                List<Qualification> qualificationList = new ArrayList<>();
                qualificationRepository.deleteAll(candidate.getQualifications());
                for (QualificationRequest qualificationRequest : request.getQualificationRequests())
                    qualificationList.add(qualificationRepository.
                            save(new Qualification(qualificationRequest.getName(), candidate)));
                candidate.setQualifications(qualificationList);
            }

            // reference
            if (request.getReferenceRequests() != null) {
                List<Reference> referenceList = new ArrayList<>();
                referenceRepository.deleteAll(candidate.getReferences());
                for (ReferenceRequest referenceRequest : request.getReferenceRequests())
                    referenceList.add(referenceRepository.
                            save(new Reference(referenceRequest.getName(), referenceRequest.getLink(), candidate)));
                candidate.setReferences(referenceList);
            }

            // skills
            if (request.getSkillRequests() != null) {
                List<Skill> skillList = new ArrayList<>();
                skillRepository.deleteAll(candidate.getSkills());
                for (SkillRequest skillRequest : request.getSkillRequests())
                    skillList.add(skillRepository.
                            save(new Skill(skillRequest.getName(), skillRequest.getLevel(), candidate)));
                candidate.setSkills(skillList);
            }

            // talents
            if (request.getTalentRequests() != null) {
                List<Talent> talentList = new ArrayList<>();
                talentRepository.deleteAll(candidate.getTalents());
                for (TalentRequest talentRequest : request.getTalentRequests())
                    talentList.add(talentRepository.
                            save(new Talent(talentRequest.getContent(), candidate)));
                candidate.setTalents(talentList);
            }

            // work experience
            if (request.getWorkExperienceRequests() != null) {
                List<WorkExperience> workExperienceList = new ArrayList<>();
                workExperienceRepository.deleteAll(candidate.getWorkExperiences());
                for (WorkExperienceRequest workExperienceRequest : request.getWorkExperienceRequests())
                    workExperienceList.add(workExperienceRepository.
                            save(new WorkExperience(workExperienceRequest.getFromDate(),
                                    workExperienceRequest.getToDate(),
                                    workExperienceRequest.getContent(),
                                    workExperienceRequest.getPosition(),
                                    candidate)
                            ));
                candidate.setWorkExperiences(workExperienceList);
            }

            if (!file.isEmpty()) {
                Map r = null;
                try {
                    r = this.cloudinary.uploader().upload(file.getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assert r != null;
                candidate.setCv((String) r.get("secure_url"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return candidateRepository.save(candidate);
    }

}
