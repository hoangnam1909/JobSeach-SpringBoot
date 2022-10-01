package com.nhn.service.impl;

import com.nhn.Util.ImageUtils;
import com.nhn.model.ImageData;
import com.nhn.repository.StorageRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {

        String randomStr;
        do {
            randomStr = RandomStringUtils.randomAlphanumeric(19);
        } while (repository.findById(randomStr).isPresent());

        repository.save(ImageData.builder()
                .id(randomStr)
                .name(String.format("%s-%s", randomStr, file.getOriginalFilename()))
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        return randomStr;
    }

    public byte[] downloadImage(String id) {
        Optional<ImageData> dbImageData = repository.findById(id);
        if (dbImageData.isPresent()) {
            byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
            return images;
        } else {
            return null;
        }
    }

}
