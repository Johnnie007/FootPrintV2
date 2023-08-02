package com.carbonTracker.footprint.model.userImage;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserImage {

    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("homeType")
    private String type;

    @NotNull
    @JsonProperty("homeSize")
    private byte[] imageData;

    @NotNull
    @JsonProperty("userId")
    private int userId;

    public UserImage(int id, String type, @NotNull byte[] imageData, int userId) {
        this.id = id;
        this.type = type;
        this.imageData = imageData;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
