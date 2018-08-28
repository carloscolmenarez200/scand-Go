package com.cencosud.scanandgo.cybersource.payment;

import android.util.Log;
import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.cencosud.scanandgo.cybersource.ConfigCyberSource;
import com.cencosud.scanandgo.cybersource.SoapResponse;
import com.cencosud.scanandgo.cybersource.client.BillTo;
import com.cencosud.scanandgo.cybersource.client.CCAuthService;
import com.cencosud.scanandgo.cybersource.client.CCCaptureService;
import com.cencosud.scanandgo.cybersource.client.IServiceEvents;
import com.cencosud.scanandgo.cybersource.client.ITransactionProcessor;
import com.cencosud.scanandgo.cybersource.client.Item;
import com.cencosud.scanandgo.cybersource.client.OperationResult;
import com.cencosud.scanandgo.cybersource.client.PurchaseTotals;
import com.cencosud.scanandgo.cybersource.client.RecurringSubscriptionInfo;
import com.cencosud.scanandgo.cybersource.client.ReplyMessage;
import com.cencosud.scanandgo.cybersource.client.RequestMessage;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by carlos on 04-06-18.
 */

public class PaymentUseCase {

    private SoapResponse soapResponse;
    private User user;
    private Card card;
    private List<ProductDiscount> products;
    private String deviceFingerprintID;
    private String referenceNumber;
    private String merchantID;

    @Inject
    public PaymentUseCase(){}


    public PaymentUseCase setData(User user, Card card, List<ProductDiscount> products, String deviceFingerprintID, String referenceNumber){
        this.user = user;
        this.products = products;
        this.card = card;
        this.deviceFingerprintID = deviceFingerprintID;
        this.referenceNumber = referenceNumber;
        this.merchantID = card.counter!=0?ConfigCyberSource.MERCHANT_ID:ConfigCyberSource.MERCHANT_ID_WITH_CVN;
        return this;
    }


    public void call(){

        RequestMessage param0 = new RequestMessage();
        param0.merchantID = merchantID;
        param0.merchantReferenceCode = referenceNumber;
        param0.clientLibrary = "clientLibrary";
        param0.clientLibraryVersion = "clientLibraryVersion";
        param0.clientEnvironment = "Windows 10/10.0/Oracle Corporation/1.8.0_101";
        //param0.deviceFingerprintID = deviceFingerprintID;

        BillTo billTo = new BillTo();
        billTo.firstName = user.firstName;
        billTo.lastName = user.lastName;
        billTo.city = "Santiago";
        billTo.state = "CL-RM";
        billTo.postalCode = "7591538";
        billTo.country = "CL";
        billTo.email = user.email;

        param0.billTo = billTo;

        if(merchantID.equals(ConfigCyberSource.MERCHANT_ID_WITH_CVN)){
            com.cencosud.scanandgo.cybersource.client.Card card_ = new com.cencosud.scanandgo.cybersource.client.Card();
            card_.cvNumber = card.cvv;
            param0.card = card_;
        }

        double grandTotalAmount = 0;
        if(products!= null && products.size()>0){

            ArrayList<Item> items = new ArrayList<>();

            for (int i=0; i<products.size();i++){

                Item item = new Item();
                item.id = BigInteger.valueOf(i);
                item.unitPrice = String.valueOf(products.get(i).totalWithDiscount/products.get(i).quantity);
                item.quantity = String.valueOf((int) Math.floor(products.get(i).quantity));
                item.productCode = products.get(i).name;
                item.productName = products.get(i).name;
                item.productSKU = products.get(i).ean; //validar con cibersource
                items.add(item);
                grandTotalAmount = grandTotalAmount + products.get(i).totalWithDiscount;
            }

            param0.item = items;
        }

        PurchaseTotals purchaseTotals = new PurchaseTotals();
        purchaseTotals.currency = "CLP";
        purchaseTotals.grandTotalAmount = String.valueOf(grandTotalAmount);

        param0.purchaseTotals = purchaseTotals;

        RecurringSubscriptionInfo recurringSubscriptionInfo = new RecurringSubscriptionInfo();
        recurringSubscriptionInfo.subscriptionID = card.token;

        param0.recurringSubscriptionInfo = recurringSubscriptionInfo;

        CCAuthService ccAuthService = new CCAuthService();
        ccAuthService.reconciliationID = referenceNumber;
        ccAuthService.run = "true";

        CCCaptureService ccCaptureService = new CCCaptureService();
        ccCaptureService.run = "true";
        ccCaptureService.reconciliationID = referenceNumber;

        param0.ccAuthService = ccAuthService;
        param0.ccCaptureService = ccCaptureService;




        final ITransactionProcessor service = new ITransactionProcessor(new IServiceEvents() {
            @Override
            public void Starting() {
            }
            @Override
            public void Completed(OperationResult result) {
                ReplyMessage res = (ReplyMessage)result.Result;

                if(res!=null){
                    if(res.decision.equals("ACCEPT")){
                        if(res.ccAuthReply!=null && res.ccAuthReply.authorizationCode!=null){
                            soapResponse.onSuccess("Payment",res.ccAuthReply.authorizationCode,res.merchantReferenceCode);
                        }
                        else {
                            //soapResponse.onError("Payment");
                            soapResponse.onSuccess("Payment","paymentAuthorization",res.merchantReferenceCode);
                        }
                    }
                    else soapResponse.onError("Payment");

                    Log.d("Payment",res.decision);
                }
            }
        }, ConfigCyberSource.URL);

        Element headers[] = new Element[1];
        headers[0]= new Element().createElement(ConfigCyberSource.SECURITY_NAME_SPACE, "Security");
        headers[0].setAttribute(null, "mustUnderstand", "1");
        Element security = headers[0];

        //user token
        Element usernametoken = new Element().createElement(security.getNamespace(), "UsernameToken");

        //username
        Element username = new Element().createElement(security.getNamespace(), "Username");
        username.addChild(Node.TEXT, merchantID);
        usernametoken.addChild(Node.ELEMENT,username);

        // password
        Element password = new Element().createElement(security.getNamespace(), "Password");
        password.setAttribute(null, "Type", ConfigCyberSource.PASSWORD_TYPE_VALUE);
        password.addChild(Node.TEXT, merchantID.equals(ConfigCyberSource.MERCHANT_ID_WITH_CVN)?ConfigCyberSource.REQ_HEADER_PASSWORD_WITH_CVN:ConfigCyberSource.REQ_HEADER_PASSWORD);
        usernametoken.addChild(Node.ELEMENT,password);

        headers[0].addChild(Node.ELEMENT, usernametoken);
        service.headerElement = security;

        service.enableLogging=true;
        service.runTransactionAsync (param0);
    }

    public void setSoapResponse(SoapResponse soapResponse) {
        this.soapResponse = soapResponse;
    }
}
