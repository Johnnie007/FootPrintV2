package com.carbonTracker.footprint.service;

import com.carbonTracker.footprint.dao.userImage.UserImageDao;
import com.carbonTracker.footprint.model.userImage.UserImage;
import com.carbonTracker.footprint.responses.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserImageService {



    @Autowired
    private UserImageDao userImageDao;

    public int storeFile(MultipartFile file, int id){

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            if(fileName.contains("..")){
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            UserImage userImage = new UserImage();
            userImage.setImageName(fileName);
            userImage.setType(file.getContentType());
            userImage.setImageData(file.getBytes());

            return userImageDao.addUserImage(userImage, id);
        } catch(IOException ex ){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Optional<UserImage> getImage(int id){
        return userImageDao.findUserImage(id);
    }

}
