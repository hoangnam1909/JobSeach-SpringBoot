package com.nhn.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidateRequest {

    @Min(1)
    private int yearsExp;

    private String linkedin;

    List<LanguageRequest> languageRequests;

    List<QualificationRequest> qualificationRequests;

    List<ReferenceRequest> referenceRequests;

    List<SkillRequest> skillRequests;

    List<TalentRequest> talentRequests;

    List<WorkExperienceRequest> workExperienceRequests;

}
