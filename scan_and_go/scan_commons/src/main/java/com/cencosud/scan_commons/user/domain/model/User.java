package com.cencosud.scan_commons.user.domain.model;

/**
 * Created by carlos on 23-03-18.
 */

public class User {

    public String id;
    public String userProfileId;
    public String displayName;
    public String fullName;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String document;
    public String email;
    public String profilePicture;
    public String sessionId;
    public String birthDate;
    public String gender;
    public Integer documentType;
    public String password;
    public String token;
    public Boolean termsSyg;

    public User() {
    }

    public User(String firstName, String lastName, String phoneNumber, String document, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.document = document;
        this.email = email;
        this.password = password;
    }
}
