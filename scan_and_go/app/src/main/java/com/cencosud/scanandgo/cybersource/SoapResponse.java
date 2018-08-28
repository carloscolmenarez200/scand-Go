package com.cencosud.scanandgo.cybersource;

/**
 * Created by carlos on 04-04-18.
 */

public interface SoapResponse {
    void onSuccess(String tag, String paymentAuthorizationId, String referenceNumber);
    void onError(String tag);
}
