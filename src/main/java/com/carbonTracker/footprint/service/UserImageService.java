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
        Optional<UserImage> image = getImage(id);

        try{

            if(fileName.contains("..")){
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            UserImage userImage = new UserImage();
            userImage.setImageName(fileName);
            userImage.setType(file.getContentType());
            userImage.setImageData(file.getBytes());
            if(image.isEmpty()){
                return userImageDao.addUserImage(userImage, id);
            }
            else{
                return userImageDao.updateUserImage(id, userImage);
            }


        } catch(IOException ex ){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Optional<UserImage> getImage(int id){
        return userImageDao.findUserImage(id);
    }

    public int deleteImage(int id){
        return userImageDao.deleteUserImage(id);
    }


}
