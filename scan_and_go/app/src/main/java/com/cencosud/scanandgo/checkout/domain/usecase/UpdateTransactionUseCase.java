package com.cencosud.scanandgo.checkout.domain.usecase;

import com.cencosud.scanandgo.checkout.data.repository.CheckoutRepository;
import com.core.domain.usecase.UseCase;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * Created by carlos on 06-06-18.
 */

public class UpdateTransactionUseCase extends UseCase<Boolean> {

    private final CheckoutRepository repository;

    private String transactionId;
    private String cardId;
    private String email;
    private String paymentAuthorizationId;
    private String referenceNumber;

    @Inject
    public UpdateTransactionUseCase(CheckoutRepository repository) {
        this.repository = repository;
    }


    public UpdateTransactionUseCase setData(String transactionId,String cardId, String email, String paymentAuthorizationId, String referenceNumber){
        this.transactionId = transactionId;
        this.cardId = cardId;
        this.email = email;
        this.paymentAuthorizationId = paymentAuthorizationId;
        this.referenceNumber = referenceNumber;
        return this;
    }


    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.updateTransaction(transactionId,cardId,email,paymentAuthorizationId,referenceNumber);
    }
}
