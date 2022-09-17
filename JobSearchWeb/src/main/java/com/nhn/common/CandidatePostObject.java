package com.nhn.common;

import com.nhn.model.Candidate;
import com.nhn.model.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePostObject {

    private Candidate candidate;

    private List<Language> languages;

}
