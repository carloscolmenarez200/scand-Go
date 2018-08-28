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

public class APAuthReply extends AttributeContainer implements KvmSerializable
{

    
    public BigInteger reasonCode=BigInteger.ZERO;
    
    public String transactionID;
    
    public String status;
    
    public String processorResponse;
    
    public String amount;
    
    public String dateTime;
    
    public String providerResponse;
    
    public String paymentStatus;
    
    public String responseCode;
    
    public String authorizationCode;
    
    public String merchantURL;
    
    public String reconciliationID;
    
    public String processorTransactionID;
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
        if (info.name.equals("transactionID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.transactionID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.transactionID = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("status"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.status = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.status = (String)obj;
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
        if (info.name.equals("dateTime"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.dateTime = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.dateTime = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("providerResponse"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.providerResponse = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.providerResponse = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("paymentStatus"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.paymentStatus = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.paymentStatus = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("responseCode"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.responseCode = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.responseCode = (String)obj;
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
        if (info.name.equals("merchantURL"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.merchantURL = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.merchantURL = (String)obj;
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
        if (info.name.equals("processorTransactionID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.processorTransactionID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.processorTransactionID = (String)obj;
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
            return this.transactionID!=null?this.transactionID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.status!=null?this.status:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.processorResponse!=null?this.processorResponse:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.amount!=null?this.amount:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.dateTime!=null?this.dateTime:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.providerResponse!=null?this.providerResponse:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==7)
        {
            return this.paymentStatus!=null?this.paymentStatus:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==8)
        {
            return this.responseCode!=null?this.responseCode:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==9)
        {
            return this.authorizationCode!=null?this.authorizationCode:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==10)
        {
            return this.merchantURL!=null?this.merchantURL:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==11)
        {
            return this.reconciliationID!=null?this.reconciliationID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==12)
        {
            return this.processorTransactionID!=null?this.processorTransactionID:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 13;
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
            info.name = "transactionID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "status";
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
            info.name = "amount";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "dateTime";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "providerResponse";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==7)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "paymentStatus";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==8)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "responseCode";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==9)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "authorizationCode";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==10)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "merchantURL";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==11)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "reconciliationID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==12)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "processorTransactionID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

    
}

