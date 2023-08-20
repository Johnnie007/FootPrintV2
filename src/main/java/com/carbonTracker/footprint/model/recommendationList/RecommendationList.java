package com.carbonTracker.footprint.model.recommendationList;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class RecommendationList {

    @NotBlank
    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("type")
    private String type;

    @NotBlank
    @JsonProperty("product")
    private String product;

    @NotBlank
    @JsonProperty("productLocation")
    private String productLocation;

    @NotBlank
    @JsonProperty("CCS")
    private int CCS;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getProduct() {
        return product;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public int getCCS() { return CCS;}

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public void setCCS(int CCS){this.CCS = CCS;}
}
