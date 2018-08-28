package com.cencosud.scanandgo.wallet.data.repository;

import com.cencosud.scanandgo.BuildConfig;
import com.cencosud.scanandgo.wallet.data.entity.CardEntity;
import com.cencosud.scanandgo.wallet.data.remote.WalletApi;
import com.cencosud.scanandgo.wallet.data.remote.response.GetCardsResponse;
import com.cencosud.scanandgo.wallet.data.repository.mapper.CardToDomainMapper;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.domain.repository.CardRepository;
import com.core.data.entity.response.ResponseBaseEntity;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by carlos on 27-03-18.
 */

public class WalletRepository implements CardRepository {

    private final WalletApi api;
    private final CardToDomainMapper cardMapper;

    public WalletRepository(WalletApi api, CardToDomainMapper cardMapper) {
        this.api = api;
        this.cardMapper = cardMapper;
    }

    @Override
    public Observable<List<Card>> getCards(String email) {
        return api.getCards(BuildConfig.ApiKey, email).map(new Function<ResponseBaseEntity<GetCardsResponse>, List<Card>>() {
            @Override
            public List<Card> apply(ResponseBaseEntity<GetCardsResponse> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                return cardMapper.map(response.payload.cards);
            }
        });
    }

    @Override
    public Observable<Boolean> deleteCard(Card data,String email) {
        GetCardsResponse body = new GetCardsResponse();
        body.idCard = data.cardId;
        body.email = email;
        return api.removeCard(BuildConfig.ApiKey, body).map(new Function<ResponseBaseEntity<String>, Boolean>() {
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
    public Observable<Card> getDefaultCard(String email) {
        return api.getDefaultCard(BuildConfig.ApiKey, email).map(new Function<ResponseBaseEntity<CardEntity>, Card>() {
            @Override
            public Card apply(ResponseBaseEntity<CardEntity> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                return cardMapper.map(response.payload);
            }
        });
    }

    @Override
    public Observable<Boolean> setDefaultCard(Card data,String email) {
        GetCardsResponse body = new GetCardsResponse();
        body.idCard = data.cardId;
        body.email = email;
        return api.setDefaultCard(BuildConfig.ApiKey, body).map(new Function<ResponseBaseEntity<String>, Boolean>() {
            @Override
            public Boolean apply(ResponseBaseEntity<String> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                return true;
            }
        });
    }

}