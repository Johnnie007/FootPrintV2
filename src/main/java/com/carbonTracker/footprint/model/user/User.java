package com.carbonTracker.footprint.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class User {

    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("firstName")
    private String firstName;

    @NotBlank
    @JsonProperty("lastName")
    private String lastName;

    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("lifestyle")
    private String lifeStyle;

    @NotBlank
    @JsonProperty("diet")
    private String diet;

    public User(int id, String firstName, String lastName, String email, String lifeStyle, String diet){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.lifeStyle = lifeStyle;
        this.diet = diet;
    }
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(){
        this.email= email;
    }
    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(String lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    @Override
    public String toString(){
        return "User{"+
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", lifestyle='" + lifeStyle + '\'' +
                ", diet='" + diet + '\'' +
                '}';
    }
}
