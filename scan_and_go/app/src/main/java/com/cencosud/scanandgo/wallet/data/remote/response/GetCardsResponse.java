package com.cencosud.scanandgo.wallet.data.remote.response;

import com.cencosud.scanandgo.wallet.data.entity.CardEntity;
import java.util.List;

/**
 * Created by carlos on 27-03-18.
 */

public class GetCardsResponse {
    public String email;
    public List<CardEntity> cards;
    public CardEntity card;
    public String idCard;
}
