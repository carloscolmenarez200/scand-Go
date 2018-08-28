package com.cencosud.scanandgo.checkout.domain.usecase;

import com.cencosud.scanandgo.checkout.data.repository.CheckoutRepository;
import com.core.domain.usecase.UseCase;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * Created by carlos on 06-06-18.
 */

public class GetTransactionStatusUseCase extends UseCase<String> {

    private final CheckoutRepository repository;

    private String transactionId;

    @Inject
    public GetTransactionStatusUseCase(CheckoutRepository repository){
        this.repository = repository;
    }

    public GetTransactionStatusUseCase setData(String transactionId){
        this.transactionId = transactionId;
        return this;
    }


    @Override
    protected Observable<String> createObservableUseCase() {
        return repository.getTransactionStatus(transactionId);
    }
}
