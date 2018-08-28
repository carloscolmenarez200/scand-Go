package com.cencosud.scanandgo.cybersource.anti_fraud;

import android.util.Log;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.cencosud.scanandgo.cybersource.ConfigCyberSource;
import com.cencosud.scanandgo.cybersource.SoapResponse;
import com.cencosud.scanandgo.cybersource.client.Field;
import com.cencosud.scanandgo.cybersource.client.MerchantDefinedData;
import com.cencosud.scanandgo.cybersource.utils.Utils;
import com.cencosud.scanandgo.cybersource.client.AFSService;
import com.cencosud.scanandgo.cybersource.client.BillTo;
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
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by carlos on 04-06-18.
 */

public class AntiFraudUseCase {

    private SoapResponse soapResponse;
    private User user;
    private Card card;
    private List<ProductDiscount> products;
    private String deviceFingerprintID;
    private String merchantID;
    private String idLocal;
    private String nameLocal;
    private String countCards;

    @Inject
    public AntiFraudUseCase(){}

    public AntiFraudUseCase setData(User user, Card card, List<ProductDiscount> products, String deviceFingerprintID, String idLocal, String nameLocal, String countCards){
        this.user = user;
        this.products = products;
        this.deviceFingerprintID = deviceFingerprintID;
        this.card = card;
        this.merchantID = card.counter!=0?ConfigCyberSource.MERCHANT_ID:ConfigCyberSource.MERCHANT_ID_WITH_CVN;
        this.idLocal = idLocal;
        this.nameLocal = nameLocal;
        this.countCards = countCards;
        return this;
    }

    private String generateMerchantReferenceCode(){
        SimpleDateFormat parseador = new SimpleDateFormat("ddMMyyyyhhmmssSSS");
        String fecha = parseador.format(new Date());
        return fecha+"A";
    }

    private int agePurchase(Date date){
        if(date!=null){
            return (int) ((new Date().getTime()-date.getTime())/86400000);
        }
        return 0;
    }

    public void call(){

        RequestMessage param0 = new RequestMessage();
        param0.merchantID = merchantID;
        param0.merchantReferenceCode = generateMerchantReferenceCode();
        param0.clientLibrary = "clientLibrary";
        param0.clientLibraryVersion = "clientLibraryVersion";
        param0.clientEnvironment = "Windows 10/10.0/Oracle Corporation/1.8.0_101";
        param0.deviceFingerprintID = deviceFingerprintID;
        param0.deviceFingerprintRaw = Boolean.TRUE;

        BillTo billTo = new BillTo();
        billTo.firstName = user.firstName;
        billTo.lastName = user.lastName;
        billTo.city = "Santiago";
        billTo.state = "CL-RM";
        billTo.postalCode = "7591538";
        billTo.country = "CL";
        billTo.email = user.email;
        billTo.ipAddress = Utils.getPublicIPAddress(App.getInstance());

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

        MerchantDefinedData merchantDefinedData = new MerchantDefinedData();
        merchantDefinedData.field1 = String.valueOf(agePurchase(card.firstTransaction)); //edad primera compra
        merchantDefinedData.field2 = String.valueOf(agePurchase(card.lastTransaction)); // edad ultima compra
        merchantDefinedData.field3 = idLocal;
        merchantDefinedData.field4 = nameLocal;
        merchantDefinedData.field5 = "0"; // cantidad de compras
        merchantDefinedData.field6 = "0"; // media de monto gastos
        merchantDefinedData.field7 = user.document;
        merchantDefinedData.field8 = card.cardHolderName;
        merchantDefinedData.field9 = countCards;

        param0.merchantDefinedData = merchantDefinedData;

        AFSService afsService = new AFSService();
        afsService.run = "true";

        param0.afsService = afsService;


        final ITransactionProcessor service = new ITransactionProcessor(new IServiceEvents() {
            @Override
            public void Starting() {
            }
            @Override
            public void Completed(OperationResult result) {
                ReplyMessage res = (ReplyMessage)result.Result;

                if(res!=null){
                    if(res.decision.equals("ACCEPT")){
                        soapResponse.onSuccess("AntiFraud", null,res.merchantReferenceCode);
                    }
                    else soapResponse.onError("AntiFraud");

                    Log.d("AntiFraud",res.decision);
                }else soapResponse.onError("AntiFraud");
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
