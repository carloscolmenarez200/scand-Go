package com.cencosud.scanandgo.wallet.domain.usecase;

import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.domain.repository.CardRepository;
import com.core.domain.usecase.UseCase;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * Created by carlos on 27-03-18.
 */

public class SetDefaultCardUseCase extends UseCase<Boolean> {
    private CardRepository repository;
    private Card data;
    private String email;

    @Inject
    SetDefaultCardUseCase(CardRepository repository) {
        this.repository=repository;
    }

    public SetDefaultCardUseCase setData(Card data, String email) {
        this.data = data;
        this.email = email;
        return this;
    }


    @Override protected Observable<Boolean> createObservableUseCase() {
        return repository.setDefaultCard(data,email);
    }
}