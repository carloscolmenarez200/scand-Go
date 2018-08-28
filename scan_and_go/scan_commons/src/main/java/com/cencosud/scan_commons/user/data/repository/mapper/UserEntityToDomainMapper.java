package com.cencosud.scan_commons.user.data.repository.mapper;

import com.cencosud.scan_commons.user.data.entity.UserEntity;
import com.cencosud.scan_commons.user.domain.model.User;
import com.core.data.repository.mapper.Mapper;
import javax.inject.Inject;

/**
 * Created by carlos on 23-03-18.
 */

public class UserEntityToDomainMapper extends Mapper<UserEntity, User> {

    @Inject
    public UserEntityToDomainMapper() {
    }

    @Override
    public User map(UserEntity value) {
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
        result.password = value.password;
        result.token = value.token;
        result.termsSyg = value.termsSyg!=null?value.termsSyg:false;
        return result;
    }

    @Override
    public UserEntity reverseMap(User value) {
        UserEntity result = new UserEntity();
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
        result.password = value.password;
        result.token = value.token;
        result.termsSyg = value.termsSyg;
        return result;
    }
}