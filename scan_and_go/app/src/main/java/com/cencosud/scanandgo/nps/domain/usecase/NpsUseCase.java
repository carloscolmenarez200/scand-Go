package com.cencosud.scanandgo.nps.domain.usecase;

import com.cencosud.scanandgo.nps.data.remote.request.NpsAnswer;
import com.cencosud.scanandgo.nps.data.remote.request.NpsRequest;
import com.cencosud.scanandgo.nps.data.remote.request.NpsTag;
import com.cencosud.scanandgo.nps.domain.repository.NpsRepository;
import com.cencosud.scanandgo.order_history.utils.DateUtils;
import com.core.domain.usecase.UseCase;


import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by joseamaro on 12-06-18.
 */

public class NpsUseCase extends UseCase<Boolean> {

    private final NpsRepository repository;
    private NpsRequest npsRequest;

    @Inject
    public NpsUseCase(NpsRepository repository) {
        this.repository = repository;
    }

    public NpsUseCase setData(String name, String email, String idLocal, String phone, String nameLocal, String amount, String typeCard, String queue, double score, String justification) {

        npsRequest = new NpsRequest();
        npsRequest.answers = new ArrayList<>();
        npsRequest.tags = new ArrayList<>();
        npsRequest.campaignCode = "10";

        NpsAnswer npsAnswer = new NpsAnswer();
        npsAnswer.name = name;
        npsAnswer.email = email;
        npsAnswer.score = score;
        npsAnswer.justification = justification;
        npsAnswer.createTime = new Date();
        npsRequest.answers.add(npsAnswer);

        NpsTag npsTag = new NpsTag();
        npsTag.name = "Id_Local";
        npsTag.value = idLocal;
        npsRequest.tags.add(npsTag);

        npsTag = new NpsTag();
        npsTag.name = "Phone";
        npsTag.value = phone;
        npsRequest.tags.add(npsTag);

        npsTag = new NpsTag();
        npsTag.name = "Monto";
        npsTag.value = amount;
        npsRequest.tags.add(npsTag);

        npsTag = new NpsTag();
        npsTag.name = "Name_Local";
        npsTag.value = nameLocal;
        npsRequest.tags.add(npsTag);

        npsTag = new NpsTag();
        npsTag.name = "MedioDePago";
        npsTag.value = typeCard;
        npsRequest.tags.add(npsTag);

        npsTag = new NpsTag();
        npsTag.name = "CheckOut";
        npsTag.value = queue;
        npsRequest.tags.add(npsTag);

        npsTag = new NpsTag();
        npsTag.name = "FCompra";
        npsTag.value = DateUtils.formaterToString("dd MMMM yyyy HH:mm", new Date());
        npsRequest.tags.add(npsTag);

        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.sendNps(npsRequest);
    }
}