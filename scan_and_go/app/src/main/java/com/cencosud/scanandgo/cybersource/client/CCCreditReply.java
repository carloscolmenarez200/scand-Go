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

public class CCCreditReply extends AttributeContainer implements KvmSerializable
{

    
    public BigInteger reasonCode=BigInteger.ZERO;
    
    public String requestDateTime;
    
    public String amount;
    
    public String reconciliationID;
    
    public String purchasingLevel3Enabled;
    
    public String enhancedDataEnabled;
    
    public String authorizationXID;
    
    public String forwardCode;
    
    public String ownerMerchantID;
    
    public String reconciliationReferenceNumber;
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
        if (info.name.equals("purchasingLevel3Enabled"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.purchasingLevel3Enabled = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.purchasingLevel3Enabled = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("enhancedDataEnabled"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.enhancedDataEnabled = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.enhancedDataEnabled = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("authorizationXID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.authorizationXID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.authorizationXID = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("forwardCode"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.forwardCode = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.forwardCode = (String)obj;
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
        if (info.name.equals("reconciliationReferenceNumber"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.reconciliationReferenceNumber = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.reconciliationReferenceNumber = (String)obj;
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
            return this.requestDateTime!=null?this.requestDateTime:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.amount!=null?this.amount:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.reconciliationID!=null?this.reconciliationID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.purchasingLevel3Enabled!=null?this.purchasingLevel3Enabled:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.enhancedDataEnabled!=null?this.enhancedDataEnabled:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.authorizationXID!=null?this.authorizationXID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==7)
        {
            return this.forwardCode!=null?this.forwardCode:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==8)
        {
            return this.ownerMerchantID!=null?this.ownerMerchantID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==9)
        {
            return this.reconciliationReferenceNumber!=null?this.reconciliationReferenceNumber:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 10;
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
            info.name = "requestDateTime";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "amount";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "reconciliationID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "purchasingLevel3Enabled";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "enhancedDataEnabled";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "authorizationXID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==7)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "forwardCode";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==8)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ownerMerchantID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==9)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "reconciliationReferenceNumber";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

    
}

