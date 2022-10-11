package com.nhn.model.request;

import com.nhn.valid.CandidateUsername;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidateRequest {

    @CandidateUsername
    private String candidateUsername;

    private int yearsExp;

    private String linkedin;

    private String cv;

    List<LanguageRequest> languageRequests;

    List<QualificationRequest> qualificationRequests;

    List<ReferenceRequest> referenceRequests;

    List<SkillRequest> skillRequests;

    List<TalentRequest> talentRequests;

    List<WorkExperienceRequest> workExperienceRequests;

}
