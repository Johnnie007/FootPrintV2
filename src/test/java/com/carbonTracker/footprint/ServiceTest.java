package com.carbonTracker.footprint;

import com.carbonTracker.footprint.dao.userImage.UserImageDaoImpl;
import com.carbonTracker.footprint.model.userImage.UserImage;
import com.carbonTracker.footprint.service.UserImageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private UserImageDaoImpl userImageDao;

    @InjectMocks
    private UserImageService userImageService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Test
    void shouldStoreFile(){
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        Mockito.doReturn(Optional.empty()).when(userImageDao).findUserImage(Mockito.anyInt());
        Mockito.doReturn(1).when(userImageDao).addUserImage(any(),Mockito.anyInt());

        int storeFile = userImageService.storeFile(file,1 );

        Assertions.assertEquals(1, storeFile);
    }

    @Test
    void shouldGetUserImage(){
        byte[] bytes = { (byte) 204, 29, (byte) 207, (byte) 217 };
        UserImage userImage = new UserImage();
        userImage.setUserId(1);
        userImage.setImageName("dog");
        userImage.setType("png");
        userImage.setImageData(bytes);

        Mockito.doReturn(Optional.of(userImage)).when(userImageDao).findUserImage(anyInt());
        Optional<UserImage> getImage = userImageService.getImage(1);

        Assertions.assertEquals("dog", getImage.get().getImageName());
    }

    @Test
    void shouldDeleteUserImage(){

        Mockito.doReturn(1).when(userImageDao).deleteUserImage(anyInt());

        int deleteImage = userImageService.deleteImage(1);

        Assertions.assertEquals(1,deleteImage);
    }

}
