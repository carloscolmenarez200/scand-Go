package com.cencosud.scanandgo.wallet.domain.usecase;

import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.domain.repository.CardRepository;
import com.core.domain.usecase.UseCase;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * Created by carlos on 27-03-18.
 */

public class GetDefaultCardUseCase extends UseCase<Card> {
    private CardRepository repository;
    private String email;

    @Inject
    public GetDefaultCardUseCase(CardRepository repository) {
        this.repository=repository;
    }

    public GetDefaultCardUseCase setData(String email){
        this.email = email;
        return this;
    }

    @Override protected Observable<Card> createObservableUseCase() {
        return repository.getDefaultCard(email);
    }
}