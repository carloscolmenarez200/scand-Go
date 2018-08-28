package com.cencosud.scanandgo.wallet.presentation.presenter;

import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.domain.usecase.DeleteCardUseCase;
import com.cencosud.scanandgo.wallet.domain.usecase.GetCardsUseCase;
import com.cencosud.scanandgo.wallet.domain.usecase.SetDefaultCardUseCase;
import com.cencosud.scanandgo.wallet.presentation.contract.CardsContract;
import com.core.domain.usecase.UseCaseObserver;

import java.util.List;

/**
 * Created by carlos on 30-05-18.
 */

public class CardsPresenter implements CardsContract.Presenter {
    private CardsContract.View view;
    private GetCardsUseCase getCardsUseCase;
    private DeleteCardUseCase deleteCardsUseCase;
    private SetDefaultCardUseCase setDefaultCardUseCase;
    private GetUserUseCase getUserUseCase;
    private final Analytic analytic;

    private User user;

    public CardsPresenter(GetCardsUseCase getCardsUseCase, DeleteCardUseCase deleteCardsUseCase, SetDefaultCardUseCase setDefaultCardUseCase, GetUserUseCase getUserUseCase, Analytic analytic) {
        this.getCardsUseCase = getCardsUseCase;
        this.deleteCardsUseCase=deleteCardsUseCase;
        this.setDefaultCardUseCase=setDefaultCardUseCase;
        this.getUserUseCase = getUserUseCase;
        this.analytic = analytic;
    }

    @Override public void initialize(CardsContract.View view) {
        this.view=view;
        getUserLocal();
    }

    private void getUserLocal() {

        User user = getUserUseCase.getLoggedUser();
        if (user!=null){
            this.user = user;
            refreshCards();
        }
    }


    @Override public void refreshCards() {
        view.showProgress(true);
        getCardsUseCase.setData(user.email).execute(new UseCaseObserver<List<Card>>() {
            @Override public void onNext(List<Card> value) {
                view.showProgress(false);
                if(value.isEmpty())
                    view.showEmptyState(true);
                else{
                    view.showEmptyState(false);
                    view.setCards(value);
                }
            }

            @Override public void onError(Throwable e) {
                e.printStackTrace();
                view.showProgress(false);
                if(e.getMessage().equals("500")){
                    view.showEmptyState(true);
                }else  view.showError();

            }
        });
    }

    @Override public void deleteCard(final Card item) {
        view.showProgress(true);
        view.showEmptyState(false);
        deleteCardsUseCase.setData(item,user.email).execute(new UseCaseObserver<Boolean>() {
            @Override public void onNext(Boolean value) {
                view.showProgress(false);
                refreshCards();
                analyticSendAction("Tarjeta Borrada");
            }

            @Override public void onError(Throwable e) {
                e.printStackTrace();
                view.showError();
            }
        });
    }

    @Override public void setCardAsDefault(Card item) {
        view.showProgress(true);
        setDefaultCardUseCase.setData(item,user.email).execute(new UseCaseObserver<Boolean>() {
            @Override public void onNext(Boolean value) {
                refreshCards();
                analyticSendAction("Predeterminada");
            }

            @Override public void onError(Throwable e) {
                e.printStackTrace();
                view.showError();
            }
        });
    }

    @Override
    public void analyticSendAction(String label) {
        analytic.sendAction("Scan And Go", "Wallet", user.userProfileId, label);
    }
}