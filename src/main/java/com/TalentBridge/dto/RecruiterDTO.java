package com.TalentBridge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterDTO extends UserDTO {
    private String Id; // from backend -> pk but fk for user
    private String companyName;
    private String title; // job title
    private String website; // company website
    private String location; // company location
    private String industry; // company industry
    private String about; // company about
    private String createdAt = null;
    private String updatedAt = null;
}