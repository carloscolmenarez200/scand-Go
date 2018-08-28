package com.cencosud.scan_commons.user.data.repository.mapper;

import com.cencosud.scan_commons.user.data.local.UserLocal;
import com.cencosud.scan_commons.user.domain.model.User;
import com.core.data.repository.mapper.Mapper;

import javax.inject.Inject;

/**
 * Created by carlos on 26-03-18.
 */

public class UserLocalToDomainMapper extends Mapper<UserLocal, User> {

    @Inject
    public UserLocalToDomainMapper() {
    }

    @Override
    public User map(UserLocal value) {
        User result = new User();
        result.userProfileId = value.userProfileId;
        result.firstName = value.firstName;
        result.lastName = value.lastName;
        result.displayName = value.displayName;
        result.fullName = value.fullName;
        result.document = value.document;
        result.email = value.email;
        result.profilePicture = value.profilePicture;
        result.birthDate = value.birthDate;
        result.phoneNumber = value.phoneNumber;
        result.gender = value.gender;
        result.documentType = value.documentType;
        result.token = value.token;
        result.password = value.password;
        result.termsSyg = value.termsSyg;
        return result;
    }

    @Override
    public UserLocal reverseMap(User value) {
        UserLocal result = new UserLocal();
        result.userProfileId = value.userProfileId;
        result.firstName = value.firstName;
        result.lastName = value.lastName;
        result.displayName = value.displayName;
        result.fullName = value.fullName;
        result.document = value.document;
        result.email = value.email;
        result.profilePicture = value.profilePicture;
        result.birthDate = value.birthDate;
        result.phoneNumber = value.phoneNumber;
        result.gender = value.gender;
        result.documentType = value.documentType;
        result.token = value.token;
        result.password = value.password;
        result.termsSyg = value.termsSyg;
        return result;
    }
}
