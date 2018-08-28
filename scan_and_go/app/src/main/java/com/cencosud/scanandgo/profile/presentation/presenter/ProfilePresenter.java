package com.cencosud.scanandgo.profile.presentation.presenter;

import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scan_commons.user.domain.usecase.SetUserUseCase;
import com.cencosud.scanandgo.login_register.domain.usecase.UpdateUserUseCase;
import com.cencosud.scanandgo.profile.presentation.ProfileContract;
import com.core.domain.usecase.UseCaseObserver;

/**
 * Created by carlos on 18-06-18.
 */

public class ProfilePresenter implements ProfileContract.Presenter{

    private final UpdateUserUseCase updateUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final SetUserUseCase setUserUseCase;

    private ProfileContract.View view;
    private User user;

    public ProfilePresenter(UpdateUserUseCase updateUserUseCase, GetUserUseCase getUserUseCase, SetUserUseCase setUserUseCase) {
        this.updateUserUseCase = updateUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.setUserUseCase = setUserUseCase;
    }

    @Override
    public void initialize(ProfileContract.View view) {
        this.view = view;
        getUserLocal();
    }

    private void getUserLocal() {
        user = getUserUseCase.getLoggedUser();
        if(user!=null){
            view.showProfile(user);
        }
    }

    @Override
    public void updateUser(String firstName, String lastName, String phoneNumber) {
        user.firstName = firstName;
        user.lastName = lastName;
        user.phoneNumber = phoneNumber;

        updateUserUseCase.setData(user).execute(new UseCaseObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {
                setUserUseCase.setData(user).setUser();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
}
