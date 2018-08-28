package com.cencosud.scanandgo.wallet.domain.usecase;

import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.domain.repository.CardRepository;
import com.core.domain.usecase.UseCase;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by carlos on 27-03-18.
 */

public class GetCardsUseCase extends UseCase<List<Card>> {
    private CardRepository repository;
    private String email;

    @Inject
    public GetCardsUseCase(CardRepository repository) {
        this.repository=repository;
    }

    public GetCardsUseCase setData(String email){
        this.email = email;
        return this;
    }


    @Override protected Observable<List<Card>> createObservableUseCase() {
        return repository.getCards(email).map(
                new Function<List<Card>, List<Card>>() {
                    @Override public List<Card> apply(List<Card> cards) throws Exception {
                        //Collections.reverse(cards);
                        return cards;
                    }
                });
    }
}

