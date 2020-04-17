package com.ttn.ecommerceApplication.ecommerceApplication.dto;

import com.ttn.ecommerceApplication.ecommerceApplication.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class RegisteredSellersDTO
{
    Long id;
    String firstName;
    String middleName;
    String lastName;
    String username;
    Boolean isActive;
    String companyName;
    AddressDTO addressDTO;
    String companyContact;

    public RegisteredSellersDTO() {
    }

    public RegisteredSellersDTO(Long id, String firstName, String middleName, String lastName, String email, Boolean isActive, String companyName, AddressDTO addressDTO, String companyContact) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.username = email;
        this.isActive = isActive;
        this.companyName = companyName;
        this.addressDTO = addressDTO;
        this.companyContact = companyContact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }
}
