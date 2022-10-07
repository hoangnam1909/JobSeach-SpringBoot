package com.nhn.service.impl;

import com.nhn.model.request.ReferenceRequest;
import com.nhn.mapper.ReferenceMapper;
import com.nhn.entity.Candidate;
import com.nhn.entity.Reference;
import com.nhn.entity.User;
import com.nhn.repository.ReferenceRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceServiceImpl implements ReferenceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReferenceMapper referenceMapper;

    @Autowired
    private ReferenceRepository referenceRepository;

    @Override
    public boolean add(List<ReferenceRequest> referenceRequestList) {

        User user = userRepository.findOneByUsernameEqualsIgnoreCase(referenceRequestList.get(0).getUsername());
        Candidate candidate = user.getCandidate();

        referenceRepository.deleteAll(candidate.getReferences());

        for (ReferenceRequest referenceRequest : referenceRequestList) {

            Reference reference = referenceMapper.toEntity(referenceRequest);
            reference.setCandidate(user.getCandidate());

            referenceRepository.save(reference);
        }

        User userCheckAfterSaving = userRepository.findOneByUsernameEqualsIgnoreCase(referenceRequestList.get(0).getUsername());

        return userCheckAfterSaving.getCandidate().getReferences().size() == referenceRequestList.size();
    }
}
