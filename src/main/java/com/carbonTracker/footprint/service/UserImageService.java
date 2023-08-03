package com.carbonTracker.footprint.service;

import com.carbonTracker.footprint.dao.userImage.UserImageDao;
import com.carbonTracker.footprint.model.userImage.UserImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserImageService {

    private final UserImageDao imageDao;

    public UserImageService(UserImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public String uploadImage(MultipartFile file, int id) throws IOException {
        var imageToSave = UserImage.builder()
                .imageName(file.getName())
                .type(file.getContentType())
                .imageData(file.getBytes())
                .build();
        imageDao.addUserImage(imageToSave, id);
        return "File uploaded successfully : " + file.getOriginalFilename();
    }

  //  public b
}
