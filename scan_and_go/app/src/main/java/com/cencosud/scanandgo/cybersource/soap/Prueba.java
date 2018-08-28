package com.cencosud.scanandgo.cybersource.soap;

import android.os.AsyncTask;

import com.cencosud.scanandgo.cybersource.SoapResponse;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


/**
 * Created by carlos on 04-04-18.
 */

public class Prueba {

    public Prueba() {
        new Prueba.AsincroSoap().execute();
    }

    private static String SOAP_ACTION = "runTransaction";
    private static String METHOD_NAME = "TransactionProcessor";
    private static String NAME_SPACE = "urn:schemas-cybersource-com:transaction-data-1.146";
    private static String URL = "https://ics2wstesta.ic3.com:443/commerce/1.x/transactionProcessor/CyberSourceTransaction_1.146.xsd";

    private SoapResponse soapResponse;

    public SoapPrimitive antiFraud(){

        SoapObject param = new SoapObject(NAME_SPACE,METHOD_NAME);

        SoapObject request = new SoapObject();

        request.addProperty("merchantID","scandgo");
        request.addProperty("merchantReferenceCode","id_generado");
        request.addProperty("clientLibrary","Java Axis WSS4J");
        request.addProperty("clientLibraryVersion","1.4/1.5.1");
        request.addProperty("clientEnvironment","Windows 10/10.0/Oracle Corporation/1.8.0_101");

        SoapObject billTO = new SoapObject();
        billTO.addProperty("firstName","John");
        billTO.addProperty("lastName","Doe");
        billTO.addProperty("street1","1295 Charleston Road");
        billTO.addProperty("city","Mountain View");
        billTO.addProperty("state","CA");
        billTO.addProperty("postalCode","94043");
        billTO.addProperty("country","US");
        billTO.addProperty("phoneNumber","650-965-6000");
        billTO.addProperty("email","null@cybersource.com");

        request.addProperty("billTO",billTO);

        SoapObject shipTo = new SoapObject();
        shipTo.addProperty("street1","1295 Charleston Road");
        shipTo.addProperty("city","Mountain View");
        shipTo.addProperty("state","CA");
        shipTo.addProperty("postalCode","94043");
        shipTo.addProperty("country","US");

        request.addProperty("shipTo",shipTo);

        PropertyInfo item1 = new PropertyInfo();
        item1.setName("id");
        item1.setValue("1");

        PropertyInfo unitPrice = new PropertyInfo();
        unitPrice.setName("unitPrice");
        unitPrice.setValue("100");

        PropertyInfo quantity = new PropertyInfo();
        quantity.setName("quantity");
        quantity.setValue("2");

        PropertyInfo productCode = new PropertyInfo();
        productCode.setName("productCode");
        productCode.setValue("Ebook");

        PropertyInfo productName = new PropertyInfo();
        productName.setName("productName");
        productName.setValue("Some product");

        PropertyInfo productSKU = new PropertyInfo();
        productSKU.setName("productSKU");
        productSKU.setValue("9788498008357");

        PropertyInfo item2 = new PropertyInfo();
        item2.setName("id");
        item2.setValue("1");

        PropertyInfo unitPrice2 = new PropertyInfo();
        unitPrice2.setName("unitPrice");
        unitPrice2.setValue("100");

        PropertyInfo quantity2 = new PropertyInfo();
        quantity2.setName("quantity");
        quantity2.setValue("2");

        PropertyInfo productCode2 = new PropertyInfo();
        productCode2.setName("productCode");
        productCode2.setValue("Ebook");

        PropertyInfo productName2 = new PropertyInfo();
        productName2.setName("productName");
        productName2.setValue("Some product");

        PropertyInfo productSKU2 = new PropertyInfo();
        productSKU2.setName("productSKU");
        productSKU2.setValue("9788498008357");

        item2.setElementType(unitPrice2);
        item2.setElementType(quantity2);
        item2.setElementType(productCode2);
        item2.setElementType(productName2);
        item2.setElementType(productSKU2);

        request.addProperty(item1);
        request.addProperty(item2);

        SoapObject purchaseTotals = new SoapObject();
        purchaseTotals.addProperty("currency","USD");
        purchaseTotals.addProperty("grandTotalAmount","250");

        request.addProperty("purchaseTotals",purchaseTotals);

        SoapObject recurringSubscriptionInfo = new SoapObject();
        recurringSubscriptionInfo.addProperty("subscriptionID","4834685966686802401014");

        request.addProperty("recurringSubscriptionInfo",recurringSubscriptionInfo);

        PropertyInfo afsService = new PropertyInfo();
        afsService.setName("run");
        afsService.setValue("true");

        request.addProperty(afsService);

        param.addProperty("requestMessage",request);


        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        soapEnvelope.dotNet = true;
        soapEnvelope.setOutputSoapObject(param);
        HttpTransportSE httpTransport = new HttpTransportSE(URL);

        try{
            httpTransport.call(SOAP_ACTION,soapEnvelope);
            return (SoapPrimitive) soapEnvelope.getResponse();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void setSoapResponse(SoapResponse soapResponse) {
        this.soapResponse = soapResponse;
    }

    public class AsincroSoap extends AsyncTask<Void,Void,SoapPrimitive>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected SoapPrimitive doInBackground(Void... voids) {
            return antiFraud();
        }

        @Override
        protected void onPostExecute(SoapPrimitive result) {
            if(result!=null){
                //soapResponse.onSuccess();
            } else{
                //soapResponse.onError();
            }

        }
    }
}
