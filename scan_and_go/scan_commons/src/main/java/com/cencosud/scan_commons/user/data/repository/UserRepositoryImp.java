package com.cencosud.scan_commons.user.data.repository;

import com.cencosud.scan_commons.BuildConfig;
import com.cencosud.scan_commons.user.data.entity.UserEntity;
import com.cencosud.scan_commons.user.data.entity.response.ResponseUser;
import com.cencosud.scan_commons.user.data.local.UserPreferences;
import com.cencosud.scan_commons.user.data.remote.UserApi;
import com.cencosud.scan_commons.user.data.repository.mapper.UserEntityToDomainMapper;
import com.cencosud.scan_commons.user.domain.model.User;
import com.core.data.entity.response.ResponseBaseEntity;
import com.google.gson.JsonObject;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by carlos on 23-03-18.
 */

public class UserRepositoryImp implements UserRepository {

    private final UserApi api;
    private final UserPreferences preferences;
    private final UserEntityToDomainMapper mapper;

    public UserRepositoryImp(UserApi api, UserPreferences preferences, UserEntityToDomainMapper mapper) {
        this.api = api;
        this.preferences = preferences;
        this.mapper = mapper;
    }

    @Override
    public Observable<User> getLogin(JsonObject fields) {
        return api.getLogin(BuildConfig.ApiKey, fields).flatMap(new Function<ResponseBaseEntity<String>, Observable<User>>() {
            @Override
            public Observable<User> apply(ResponseBaseEntity<String> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                preferences.saveJwtToken(response.payload);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("jwt", response.payload);
                return parser(jsonObject);
            }
        });
    }

    @Override
    public Observable<User> register(UserEntity userEntity) {
        return api.register(BuildConfig.ApiKey, userEntity).flatMap(new Function<ResponseBaseEntity<String>, Observable<User>>() {
            @Override
            public Observable<User> apply(ResponseBaseEntity<String> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                preferences.saveJwtToken(response.payload);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("jwt", response.payload);
                return parser(jsonObject);
            }
        });
    }

    @Override
    public Observable<Boolean> validateUser(UserEntity userEntity) {
        return api.validateUser(BuildConfig.ApiKey, userEntity).map(new Function<ResponseBaseEntity<String>,Boolean>() {
            @Override
            public Boolean apply(ResponseBaseEntity<String> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                return true;
            }
        });
    }

    @Override
    public Observable<String> encrypt(JsonObject fields) {
        return api.encrypt(BuildConfig.ApiKey, fields).map(new Function<ResponseBaseEntity<String>, String>() {
            @Override
            public String apply(ResponseBaseEntity<String> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                return response.payload;
            }
        });
    }

    @Override
    public Observable<User> parser(JsonObject fields) {

        return api.parser(BuildConfig.ApiKey, fields).map(new Function<ResponseBaseEntity<UserEntity>, User>() {
            @Override
            public User apply(ResponseBaseEntity<UserEntity> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                return mapper.map(response.payload);
            }
        });
    }

    @Override
    public Observable<String> getLastJwtToken() {
        return Observable.just(preferences.getJwtToken());
    }

    @Override
    public Observable<User> refreshUser(String token) {

        return api.refreshUser(BuildConfig.ApiKey, token).flatMap(new Function<ResponseBaseEntity<ResponseUser>, Observable<User>>() {
            @Override
            public Observable<User> apply(ResponseBaseEntity<ResponseUser> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                preferences.saveJwtToken(response.payload.client);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("jwt", response.payload.client);
                return parser(jsonObject);
            }
        });
    }

    @Override
    public Observable<Boolean> updateUser(String jwt, UserEntity userEntity) {
        return api.updateUser(BuildConfig.ApiKey, jwt, userEntity).map(new Function<ResponseBaseEntity<String>, Boolean>() {
            @Override
            public Boolean apply(ResponseBaseEntity<String> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                preferences.saveJwtToken(response.payload);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> generateToken(String email) {

        JsonObject objectEncrypt = new JsonObject();
        objectEncrypt.addProperty("email", email);

        return api.generateToken(BuildConfig.ApiKey, objectEncrypt).map(new Function<ResponseBaseEntity<String>, Boolean>() {
            @Override
            public Boolean apply(ResponseBaseEntity<String> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> changePassword(String token, String email, String password) {

        JsonObject objectEncrypt = new JsonObject();
        objectEncrypt.addProperty("token", token);
        objectEncrypt.addProperty("email", email);
        objectEncrypt.addProperty("password", password);

        return api.changePassword(BuildConfig.ApiKey, objectEncrypt).map(new Function<ResponseBaseEntity<String>, Boolean>() {
            @Override
            public Boolean apply(ResponseBaseEntity<String> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode); 
                }

                return true;
            }
        });

    }

    @Override
    public Observable<Boolean> whiteLists(String email) {
        return  api.whiteLists(BuildConfig.ApiKey, email).map(new Function<ResponseBaseEntity<Boolean>, Boolean>() {
            @Override
            public Boolean apply(ResponseBaseEntity<Boolean> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                return response.payload;
            }
        });
    }
}
