package com.cencosud.scan_commons.user.data.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by carlos on 23-03-18.
 */

public class UserLocal extends RealmObject {

    public String id;
    public String userProfileId;
    public String displayName;
    public String fullName;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String document;
    @PrimaryKey @Required
    public String email;
    public String profilePicture;
    public String sessionId;
    public String birthDate;
    public String gender;
    public Integer documentType;
    public String token;
    public String password;
    public boolean termsSyg;
}
