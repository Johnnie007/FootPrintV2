package com.carbonTracker.footprint.model.offSetters;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OffSetters {

    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("type")
    private String type;

    @NotBlank
    @JsonProperty("product")
    private String product;


    @NotNull
    @JsonProperty("CCS")
    private int CCS;

    @NotNull
    @JsonProperty("userId")
    private int userId;

    public int getId() { return id;}

    public String getType() {
        return type;
    }

    public String getProduct(){
        return product;
    }

    public int getCCS(){
        return CCS;
    }

    public int getUserId() { return userId; }

    public void setId(int id ){
        this.id = id;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setProduct(String product){
        this.product = product;
    }

    public void setCCS(int CCS){
        this.CCS = CCS;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

}
