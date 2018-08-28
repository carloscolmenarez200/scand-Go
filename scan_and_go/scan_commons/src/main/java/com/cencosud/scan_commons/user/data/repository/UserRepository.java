package com.cencosud.scan_commons.user.data.repository;

import com.cencosud.scan_commons.user.data.entity.UserEntity;
import com.cencosud.scan_commons.user.domain.model.User;
import com.google.gson.JsonObject;
import io.reactivex.Observable;

/**
 * Created by carlos on 23-03-18.
 */

public interface UserRepository {

    Observable<User> getLogin(JsonObject fields);
    Observable<User> register(UserEntity userEntity);
    Observable<Boolean>  validateUser(UserEntity userEntity);
    Observable<String> encrypt(JsonObject fields);
    Observable<User> parser(JsonObject fields);
    Observable<String> getLastJwtToken();
    Observable<User> refreshUser(String token);
    Observable<Boolean> updateUser(String tokenjwt, UserEntity userEntity);
    Observable<Boolean> generateToken(String email);
    Observable<Boolean> changePassword(String token, String email, String password);
    Observable<Boolean> whiteLists(String email);

}
