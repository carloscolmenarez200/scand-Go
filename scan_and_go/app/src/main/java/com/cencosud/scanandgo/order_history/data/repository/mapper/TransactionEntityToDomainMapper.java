package com.cencosud.scanandgo.order_history.data.repository.mapper;

import com.cencosud.scanandgo.checkout.data.repository.mapper.ProductDiscountEntityToDomainMapper;
import com.cencosud.scanandgo.order_history.data.entity.TransactionEntity;
import com.cencosud.scanandgo.order_history.domain.model.Transaction;
import com.cencosud.scanandgo.order_history.utils.DateUtils;
import com.core.data.repository.mapper.Mapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;

/**
 * Created by joseamaro on 19-07-18.
 */

public class TransactionEntityToDomainMapper extends Mapper<TransactionEntity, Transaction> {

    private final ProductDiscountEntityToDomainMapper productDiscountMapper;

    @Inject
    TransactionEntityToDomainMapper(ProductDiscountEntityToDomainMapper productDiscountMapper){
        this.productDiscountMapper = productDiscountMapper;
    }


    @Override
    public Transaction map(TransactionEntity value) {

        Transaction purchaseHistory = new Transaction();
        purchaseHistory.cardId="**** **** **** "+value.cardId.substring(12,16);
        purchaseHistory.rut = value.rut;
        purchaseHistory.storeId = value.storeId;
        purchaseHistory.transactionId = value.transactionId;
        purchaseHistory.referenceNumber = value.referenceNumber;
        purchaseHistory.paymentAuthorization = value.paymentAuthorization;
        purchaseHistory.date = DateUtils.formaterToDate("yyyy-MM-dd HH:mm:ss", value.date);
        purchaseHistory.totalAmount = value.totalAmount;

        if(value.products!=null){
            purchaseHistory.productDiscounts = productDiscountMapper.map(value.products);
        }
        return purchaseHistory;

    }

    @Override
    public TransactionEntity reverseMap(Transaction value) {

        TransactionEntity purchaseHistoryEntity = new TransactionEntity();
        purchaseHistoryEntity.cardId = value.cardId;
        purchaseHistoryEntity.rut = value.rut;
        purchaseHistoryEntity.storeId = value.storeId;
        purchaseHistoryEntity.transactionId = value.transactionId;
        purchaseHistoryEntity.referenceNumber = value.referenceNumber;
        purchaseHistoryEntity.paymentAuthorization = value.paymentAuthorization;
        purchaseHistoryEntity.date = DateUtils.formaterToString("yyyy-MM-dd HH:mm:ss", value.date);
        purchaseHistoryEntity.totalAmount = value.totalAmount;

        if(value.productDiscounts!=null){
            purchaseHistoryEntity.products = productDiscountMapper.reverseMap(value.productDiscounts);
        }
        return purchaseHistoryEntity;
    }
}
