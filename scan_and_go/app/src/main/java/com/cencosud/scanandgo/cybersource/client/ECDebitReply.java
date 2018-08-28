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

public class ECDebitReply extends AttributeContainer implements KvmSerializable
{

    
    public BigInteger reasonCode=BigInteger.ZERO;
    
    public String settlementMethod;
    
    public String requestDateTime;
    
    public String amount;
    
    public BigInteger verificationLevel;
    
    public String processorTransactionID;
    
    public String reconciliationID;
    
    public String processorResponse;
    
    public String avsCode;
    
    public String avsCodeRaw;
    
    public String verificationCode;
    
    public String verificationCodeRaw;
    
    public String correctedAccountNumber;
    
    public String correctedRoutingNumber;
    
    public String ownerMerchantID;
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
        if (info.name.equals("settlementMethod"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.settlementMethod = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.settlementMethod = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("requestDateTime"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.requestDateTime = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.requestDateTime = (String)obj;
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
        if (info.name.equals("verificationLevel"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.verificationLevel = new BigInteger(j.toString());
                    }
                }
                else if (obj instanceof BigInteger){
                    this.verificationLevel = (BigInteger)obj;
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
        if (info.name.equals("verificationCode"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.verificationCode = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.verificationCode = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("verificationCodeRaw"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.verificationCodeRaw = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.verificationCodeRaw = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("correctedAccountNumber"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.correctedAccountNumber = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.correctedAccountNumber = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("correctedRoutingNumber"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.correctedRoutingNumber = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.correctedRoutingNumber = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("ownerMerchantID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.ownerMerchantID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.ownerMerchantID = (String)obj;
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
            return this.settlementMethod!=null?this.settlementMethod:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.requestDateTime!=null?this.requestDateTime:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.amount!=null?this.amount:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.verificationLevel!=null?this.verificationLevel.toString():SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.processorTransactionID!=null?this.processorTransactionID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.reconciliationID!=null?this.reconciliationID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==7)
        {
            return this.processorResponse!=null?this.processorResponse:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==8)
        {
            return this.avsCode!=null?this.avsCode:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==9)
        {
            return this.avsCodeRaw!=null?this.avsCodeRaw:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==10)
        {
            return this.verificationCode!=null?this.verificationCode:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==11)
        {
            return this.verificationCodeRaw!=null?this.verificationCodeRaw:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==12)
        {
            return this.correctedAccountNumber!=null?this.correctedAccountNumber:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==13)
        {
            return this.correctedRoutingNumber!=null?this.correctedRoutingNumber:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==14)
        {
            return this.ownerMerchantID!=null?this.ownerMerchantID:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 15;
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
            info.name = "settlementMethod";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "requestDateTime";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "amount";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "verificationLevel";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "processorTransactionID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "reconciliationID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==7)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "processorResponse";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==8)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "avsCode";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==9)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "avsCodeRaw";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==10)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "verificationCode";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==11)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "verificationCodeRaw";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==12)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "correctedAccountNumber";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==13)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "correctedRoutingNumber";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==14)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ownerMerchantID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

    
}

