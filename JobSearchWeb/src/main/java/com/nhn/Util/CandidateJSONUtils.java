package com.nhn.Util;

import com.nhn.entity.Language;
import com.nhn.entity.Skill;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CandidateJSONUtils {

    public List<Language> toListLanguages(JSONArray arr) {
        List<Language> languageList = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            languageList.add(new Language(
                    arr.getJSONObject(i).getString("name"),
                    arr.getJSONObject(i).getString("description")
            ));

            System.err.println(arr.getJSONObject(i).getString("name"));
            System.err.println(arr.getJSONObject(i).getString("description"));
        }

        return languageList;
    }

    public List<Skill> toListSkills(JSONArray arr) {
        List<Skill> skillList = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            skillList.add(new Skill(
                    arr.getJSONObject(i).getString("name"),
                    arr.getJSONObject(i).getString("level")
            ));

            System.err.println(arr.getJSONObject(i).getString("name"));
            System.err.println(arr.getJSONObject(i).getString("level"));
        }

        return skillList;
    }

}
