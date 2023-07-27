package com.carbonTracker.footprint.model.carbonOffsetters;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class CarbonOffsetters {

    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("type")
    private String type;

    @NotBlank
    @JsonProperty("product")
    private String product;


    @NotBlank
    @JsonProperty("CCS")
    private int CCS;

    public CarbonOffsetters(int id, String type, String product, int ccs){
        this.id = id;
        this.type = type;
        this.product = product;
        this.CCS = CCS;
    }

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

}
