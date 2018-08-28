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

public class PaySubscriptionUpdateReply extends AttributeContainer implements KvmSerializable
{

    
    public BigInteger reasonCode=BigInteger.ZERO;
    
    public String subscriptionID;
    
    public String subscriptionIDNew;
    
    public String ownerMerchantID;
    
    public String instrumentIdentifierID;
    
    public String instrumentIdentifierStatus;
    
    public String instrumentIdentifierNew;
    
    public String instrumentIdentifierSuccessorID;
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
        if (info.name.equals("subscriptionID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.subscriptionID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.subscriptionID = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("subscriptionIDNew"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.subscriptionIDNew = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.subscriptionIDNew = (String)obj;
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
        if (info.name.equals("instrumentIdentifierID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.instrumentIdentifierID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.instrumentIdentifierID = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("instrumentIdentifierStatus"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.instrumentIdentifierStatus = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.instrumentIdentifierStatus = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("instrumentIdentifierNew"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.instrumentIdentifierNew = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.instrumentIdentifierNew = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("instrumentIdentifierSuccessorID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.instrumentIdentifierSuccessorID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.instrumentIdentifierSuccessorID = (String)obj;
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
            return subscriptionID;
        }
        if(propertyIndex==2)
        {
            return this.subscriptionIDNew!=null?this.subscriptionIDNew:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.ownerMerchantID!=null?this.ownerMerchantID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.instrumentIdentifierID!=null?this.instrumentIdentifierID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.instrumentIdentifierStatus!=null?this.instrumentIdentifierStatus:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.instrumentIdentifierNew!=null?this.instrumentIdentifierNew:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==7)
        {
            return this.instrumentIdentifierSuccessorID!=null?this.instrumentIdentifierSuccessorID:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 8;
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
            info.name = "subscriptionID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "subscriptionIDNew";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "ownerMerchantID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "instrumentIdentifierID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "instrumentIdentifierStatus";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "instrumentIdentifierNew";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==7)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "instrumentIdentifierSuccessorID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

    
}

