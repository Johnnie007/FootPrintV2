package com.carbonTracker.footprint.model.recommendationList;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class RecommendationList {

    @NotBlank
    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("plants")
    private String plants;

    @NotBlank
    @JsonProperty("energy")
    private String energy;

    @NotBlank
    @JsonProperty("goods")
    private String goods;

    public RecommendationList(int id, String plants, String energy, String goods ){
        this.id = id;
        this.plants = plants;
        this.energy = energy;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public String getEnergy() {
        return energy;
    }

    public String getGoods() {
        return goods;
    }

    public String getPlants() {
        return plants;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public void setPlants(String plants) {
        this.plants = plants;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }
}
