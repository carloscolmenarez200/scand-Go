package com.cencosud.scanandgo.wallet.domain.usecase;

import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.domain.repository.CardRepository;
import com.core.domain.usecase.UseCase;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * Created by carlos on 27-03-18.
 */

public class DeleteCardUseCase extends UseCase<Boolean> {
    private CardRepository repository;
    private Card data;
    private String email;

    @Inject
    DeleteCardUseCase(CardRepository repository) {
        this.repository=repository;
    }

    public DeleteCardUseCase setData(Card data,String email) {
        this.data = data;
        this.email = email;
        return this;
    }

    @Override protected Observable<Boolean> createObservableUseCase() {
        return repository.deleteCard(data,email);
    }
}
