package com.cencosud.scanandgo.wallet.domain.repository;

import com.cencosud.scanandgo.wallet.domain.model.Card;
import java.util.List;
import io.reactivex.Observable;

/**
 * Created by carlos on 27-03-18.
 */

public interface CardRepository {
    Observable<List<Card>> getCards(String email);
    Observable<Boolean> deleteCard(Card data,String email);
    Observable<Card> getDefaultCard(String email);
    Observable<Boolean> setDefaultCard(Card data,String email);
}
