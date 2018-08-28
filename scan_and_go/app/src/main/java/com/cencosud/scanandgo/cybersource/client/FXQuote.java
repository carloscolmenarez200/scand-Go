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

public class FXQuote extends AttributeContainer implements KvmSerializable
{

    
    public String id;
    
    public String rate;
    
    public String type;
    
    public String expirationDateTime;
    
    public String currency;
    
    public String fundingCurrency;
    
    public String receivedDateTime;
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
        if (info.name.equals("id"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.id = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.id = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("rate"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.rate = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.rate = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("type"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.type = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.type = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("expirationDateTime"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.expirationDateTime = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.expirationDateTime = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("currency"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.currency = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.currency = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("fundingCurrency"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.fundingCurrency = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.fundingCurrency = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("receivedDateTime"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.receivedDateTime = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.receivedDateTime = (String)obj;
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
            return this.id!=null?this.id:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.rate!=null?this.rate:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.type!=null?this.type:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.expirationDateTime!=null?this.expirationDateTime:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.currency!=null?this.currency:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.fundingCurrency!=null?this.fundingCurrency:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.receivedDateTime!=null?this.receivedDateTime:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 7;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "id";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "rate";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "type";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "expirationDateTime";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "currency";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "fundingCurrency";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "receivedDateTime";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

    
}

