package com.cencosud.scanandgo.wallet.data.repository.mapper;

import com.cencosud.scanandgo.order_history.utils.DateUtils;
import com.cencosud.scanandgo.wallet.data.entity.CardEntity;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.utils.NameCard;
import com.core.data.repository.mapper.Mapper;
import javax.inject.Inject;

/**
 * Created by carlos on 27-03-18.
 */

public class CardToDomainMapper extends Mapper<CardEntity,Card> {

    @Inject
    public CardToDomainMapper() {}

    @Override public Card map(CardEntity value) {
        Card result = new Card();
        result.cardId=value.cardId;
        result.cardNumber="****   ****   ****   "+value.digits.substring(12,16);
        result.cardHolderName=value.fullName;
        result.defaultCard=value.defaultCard;
        result.token=value.token;
        result.digits=value.digits;
        result.cardType = value.cardType;
        result.counter = value.counter;
        if(value.nameCard!=null){
            result.nameCard = value.nameCard;
        }else{
            if(value.tenderCode.equals("12")){
                result.nameCard = NameCard.MASTER_CENCOSUD;
            }
        }
        result.tenderCode = value.tenderCode;
        result.expirationDate = value.expirationDate;
        result.mpcenc = value.mpcenc;
        if(value.firstTransaction!= null && !value.firstTransaction.trim().isEmpty()){
            result.firstTransaction = DateUtils.formaterToDate("yyyy-MM-dd HH:mm:ss", value.firstTransaction);
        }
        if(value.lastTransaction!= null && !value.lastTransaction.trim().isEmpty()){
            result.lastTransaction = DateUtils.formaterToDate("yyyy-MM-dd HH:mm:ss", value.lastTransaction);
        }
        return result;
    }

    @Override public CardEntity reverseMap(Card value) {
        CardEntity result = new CardEntity();
        result.cardId=value.cardId;
        result.defaultCard=value.defaultCard;
        result.digits=value.digits;
        result.fullName=value.cardHolderName;
        result.token=value.token;
        result.cardType =value.cardType;
        result.counter = value.counter;
        result.nameCard = value.nameCard;
        result.tenderCode = value.tenderCode;
        result.expirationDate = value.expirationDate;
        result.mpcenc = value.mpcenc;
        if(value.firstTransaction!=null){
            result.firstTransaction = DateUtils.formaterToString("yyyy-MM-dd HH:mm:ss", value.firstTransaction);
        }
        if(value.lastTransaction!= null){
            result.lastTransaction = DateUtils.formaterToString("yyyy-MM-dd HH:mm:ss", value.lastTransaction);
        }

        return result;
    }
}
