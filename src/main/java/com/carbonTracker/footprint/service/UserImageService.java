package com.carbonTracker.footprint.service;

import com.carbonTracker.footprint.dao.userImage.UserImageDao;
import com.carbonTracker.footprint.model.userImage.UserImage;
import com.carbonTracker.footprint.utils.ImageUtils;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

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
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build();
        imageDao.addUserImage(imageToSave, id);
        return "File uploaded successfully : " + file.getOriginalFilename();
    }

    public byte[] downloadImage(int userId){
        Optional<UserImage> dbImage = imageDao.findUserImage(userId);
        return dbImage.map(image ->{
            try{
                return ImageUtils.decompressImage(image.getImageData());
            } catch(DataFormatException | IOException exception){
                throw new ContextedRuntimeException("Error downloading an image", exception)
                        .addContextValue("Image id", userId)
                        .addContextValue("Image name", image.getImageName());
            }
        }).orElse(null);
    }

}
