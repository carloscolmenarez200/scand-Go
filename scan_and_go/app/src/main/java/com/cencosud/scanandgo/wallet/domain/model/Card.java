package com.cencosud.scanandgo.wallet.domain.model;

import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.wallet.utils.NameCard;

import java.util.Date;

/**
 * Created by carlos on 27-03-18.
 */

public class Card {
    public String cardId;
    public String cardNumber;
    public String cardHolderName;
    public String expirationDate;
    public String cardType;
    public String cvv;
    public boolean defaultCard;
    public String token;
    public String digits;
    public int counter;
    public NameCard nameCard;
    public String tenderCode;
    public int mpcenc;
    public Date firstTransaction;
    public Date lastTransaction;

    public Card(String number) {
        this.cardNumber=number.trim().replace(" ","");
        this.nameCard = NameCard.fromCardNumber(cardNumber);
        this.digits=cardNumber.substring(cardNumber.length()-4);
    }

    public Card() {}

    public int getCardTypeImage() {
        int cardImageResource = -1;
        if(nameCard ==null)return R.drawable.ic_add;
        switch (nameCard) {
            case AMEX: {
                cardImageResource = R.drawable.img_american_express;
                break;
            }
            case VISA: {
                cardImageResource = R.drawable.visa;
                break;
            }
            case MASTERCARD: {
                cardImageResource = R.drawable.img_master_card;
                break;
            }
            case MASTER_CENCOSUD: {
                cardImageResource = R.drawable.img_master_card;
                break;
            }
            case DISCOVER:
            case DINERSCLUB: {
                cardImageResource = R.drawable.ic_diners_club;
                break;
            }
            case JCB: {
                cardImageResource = R.drawable.ic_jcb;
                break;
            }
            default: {
                // do not use generic image by default, if it's not one of the above, it's not
                // valid, or it's maestro
                break;
            }
        }
        return cardImageResource;
    }

    public int getCardBackground() {
            if(tenderCode.equals("12")){
                return R.drawable.tarjeta_cenco_front;
            }else return R.drawable.img_card;
    }

    public int getCardBackgroundBack() {
        if(tenderCode.equals("12")){
            return R.drawable.tarjeta_cenco_back;
        }else return R.drawable.img_card_back;
    }

    public int getButtonBackgroundBack() {
        if(tenderCode.equals("12")){
            return R.drawable.button_card_back_cenco;
        }else return R.drawable.button_card_payment_back;
    }


}
