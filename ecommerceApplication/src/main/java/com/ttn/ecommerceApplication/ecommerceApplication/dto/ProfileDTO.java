package com.ttn.ecommerceApplication.ecommerceApplication.dto;

import org.springframework.stereotype.Component;

@Component
public class ProfileDTO
{
    private String firstName;
    private String middleName;
    private String lastName;
    private String contactNo;
    private Boolean isActive;
    private String linkForImage="http://localhost:8080/viewProfileImage";

    public String getLinkForImage() {
        return linkForImage;
    }

    public void setLinkForImage(String linkForImage) {
        this.linkForImage = linkForImage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
