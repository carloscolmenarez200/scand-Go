package com.cencosud.scanandgo.cybersource.client;

//----------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 5.5.0.6
//
// Created by Quasar Development 
//
//---------------------------------------------------


import java.util.Hashtable;
import org.ksoap2.serialization.*;
import java.math.BigInteger;

public class CCSaleReply extends AttributeContainer implements KvmSerializable
{

    
    public BigInteger reasonCode=BigInteger.ZERO;
    
    public String amount;
    
    public String authorizationCode;
    
    public String processorResponse;
    
    public String avsCode;
    
    public String avsCodeRaw;
    
    public String cvCode;
    
    public String cvCodeRaw;
    
    public String cavvResponseCode;
    
    public String cavvResponseCodeRaw;
    
    public String cardGroup;
    
    public String paymentNetworkTransactionID;
    
    public String cardCategory;
    
    public String accountBalance;
    
    public String authorizedDateTime;
    
    public String requestAmount;
    
    public String reconciliationID;
    
    public String accountBalanceCurrency;
    
    public String accountBalanceSign;
    private transient java.lang.Object __source;    
    

    
    
    
    public void loadFromSoap(java.lang.Object paramObj,ExtendedSoapSerializationEnvelope __envelope)
    {
        if (paramObj == null)
            return;
        AttributeContainer inObj=(AttributeContainer)paramObj;
        __source=inObj; 
        if(inObj instanceof SoapObject)
        {
            SoapObject soapObject=(SoapObject)inObj;
            int size = soapObject.getPropertyCount();
            for (int i0=0;i0< size;i0++)
            {
                PropertyInfo info=soapObject.getPropertyInfo(i0);
                if(!loadProperty(info,soapObject,__envelope))
                {
                }
            } 
        }

    }

    
    protected boolean loadProperty(PropertyInfo info,SoapObject soapObject,ExtendedSoapSerializationEnvelope __envelope)
    {
        java.lang.Object obj = info.getValue();
        if (info.name.equals("reasonCode"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.reasonCode = new BigInteger(j.toString());
                    }
                }
                else if (obj instanceof BigInteger){
                    this.reasonCode = (BigInteger)obj;
                }
            }
            return true;
        }
        if (info.name.equals("amount"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.amount = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.amount = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("authorizationCode"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.authorizationCode = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.authorizationCode = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("processorResponse"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.processorResponse = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.processorResponse = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("avsCode"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.avsCode = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.avsCode = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("avsCodeRaw"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.avsCodeRaw = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.avsCodeRaw = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("cvCode"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.cvCode = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.cvCode = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("cvCodeRaw"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.cvCodeRaw = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.cvCodeRaw = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("cavvResponseCode"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.cavvResponseCode = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.cavvResponseCode = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("cavvResponseCodeRaw"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.cavvResponseCodeRaw = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.cavvResponseCodeRaw = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("cardGroup"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.cardGroup = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.cardGroup = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("paymentNetworkTransactionID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.paymentNetworkTransactionID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.paymentNetworkTransactionID = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("cardCategory"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.cardCategory = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.cardCategory = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("accountBalance"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.accountBalance = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.accountBalance = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("authorizedDateTime"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.authorizedDateTime = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.authorizedDateTime = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("requestAmount"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.requestAmount = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.requestAmount = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("reconciliationID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.reconciliationID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.reconciliationID = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("accountBalanceCurrency"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.accountBalanceCurrency = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.accountBalanceCurrency = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("accountBalanceSign"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.accountBalanceSign = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.accountBalanceSign = (String)obj;
                }
            }
            return true;
        }
        return false;
    }
    
    public java.lang.Object getOriginalXmlSource()
    {
        return __source;
    }    
    

    @Override
    public java.lang.Object getProperty(int propertyIndex) {
        //!!!!! If you have a compilation error here then you are using old version of ksoap2 library. Please upgrade to the latest version.
        //!!!!! You can find a correct version in Lib folder from generated zip file!!!!!
        if(propertyIndex==0)
        {
            return this.reasonCode!=null?this.reasonCode.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.amount!=null?this.amount:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.authorizationCode!=null?this.authorizationCode:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.processorResponse!=null?this.processorResponse:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.avsCode!=null?this.avsCode:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.avsCodeRaw!=null?this.avsCodeRaw:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.cvCode!=null?this.cvCode:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==7)
        {
            return this.cvCodeRaw!=null?this.cvCodeRaw:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==8)
        {
            return this.cavvResponseCode!=null?this.cavvResponseCode:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==9)
        {
            return this.cavvResponseCodeRaw!=null?this.cavvResponseCodeRaw:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==10)
        {
            return this.cardGroup!=null?this.cardGroup:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==11)
        {
            return this.paymentNetworkTransactionID!=null?this.paymentNetworkTransactionID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==12)
        {
            return this.cardCategory!=null?this.cardCategory:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==13)
        {
            return this.accountBalance!=null?this.accountBalance:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==14)
        {
            return this.authorizedDateTime!=null?this.authorizedDateTime:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==15)
        {
            return this.requestAmount!=null?this.requestAmount:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==16)
        {
            return this.reconciliationID!=null?this.reconciliationID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==17)
        {
            return this.accountBalanceCurrency!=null?this.accountBalanceCurrency:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==18)
        {
            return this.accountBalanceSign!=null?this.accountBalanceSign:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 19;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "reasonCode";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "amount";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "authorizationCode";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "processorResponse";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "avsCode";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "avsCodeRaw";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "cvCode";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==7)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "cvCodeRaw";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==8)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "cavvResponseCode";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==9)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "cavvResponseCodeRaw";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==10)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "cardGroup";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==11)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "paymentNetworkTransactionID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==12)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "cardCategory";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==13)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "accountBalance";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==14)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "authorizedDateTime";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==15)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "requestAmount";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==16)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "reconciliationID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==17)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "accountBalanceCurrency";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==18)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "accountBalanceSign";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

    
}

