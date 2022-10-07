package com.nhn.service;

import com.nhn.model.request.LanguageRequest;

import java.util.List;

public interface LanguageService {

    boolean add(List<LanguageRequest> languageRequestList);

}
