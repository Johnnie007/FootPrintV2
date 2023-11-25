package com.carbonTracker.footprint.model.user;

import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class User {

    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("month_joined")
    private String month_joined;
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
    @JsonProperty("password")
    private String password;


    @JsonProperty("lifestyle")
    private String lifeStyle;


    @JsonProperty("footPrint")
    private int footprint;

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

    public void setEmail(String email){
        this.email= email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public int  getFootPrint() {
        return footprint;
    }

    public void setFootPrint(int footPrint) {
        this.footprint = footPrint;
    }

    public String getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(String lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    public String getMonth_joined() {
        return month_joined;
    }

    public void setMonth_joined(String month_joined) {
        this.month_joined = month_joined;
    }

    @Override
    public String toString(){
        return "User{"+
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", lifestyle='" + lifeStyle + '\'' +
                ", footprint='" + footprint + '\'' +
                '}';
    }
}
