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

public class TaxReplyItemJurisdiction extends AttributeContainer implements KvmSerializable
{

    
    public String country;
    
    public String region;
    
    public String type;
    
    public String code;
    
    public String taxable;
    
    public String rate;
    
    public String taxAmount;
    
    public String name;
    
    public String taxName;
    
    public BigInteger jurisId=BigInteger.ZERO;
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
        if (inObj.hasAttribute("jurisId"))
        {	
            java.lang.Object j = inObj.getAttribute("jurisId");
            if (j != null)
            {
                jurisId = new BigInteger(j.toString());
            }
        }

    }

    
    protected boolean loadProperty(PropertyInfo info,SoapObject soapObject,ExtendedSoapSerializationEnvelope __envelope)
    {
        java.lang.Object obj = info.getValue();
        if (info.name.equals("country"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.country = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.country = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("region"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.region = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.region = (String)obj;
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
        if (info.name.equals("code"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.code = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.code = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("taxable"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.taxable = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.taxable = (String)obj;
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
        if (info.name.equals("taxAmount"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.taxAmount = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.taxAmount = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("name"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.name = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.name = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("taxName"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.taxName = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.taxName = (String)obj;
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
            return this.country!=null?this.country:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.region!=null?this.region:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.type!=null?this.type:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.code!=null?this.code:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.taxable!=null?this.taxable:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.rate!=null?this.rate:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.taxAmount!=null?this.taxAmount:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==7)
        {
            return name;
        }
        if(propertyIndex==8)
        {
            return taxName;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 9;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "country";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "region";
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
            info.name = "code";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "taxable";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "rate";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "taxAmount";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==7)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "name";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==8)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "taxName";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }



    @Override
    public int getAttributeCount() {
        return 1;
    }
    
    @Override
    public void getAttributeInfo(int index, AttributeInfo info) {
        if(index==0)
        {
            info.name = "jurisId";
            info.namespace= "";
            if(this.jurisId!=null)
            {
                info.setValue(this.jurisId);
            }
            
        }
    }

    @Override
    public void getAttribute(int i, AttributeInfo attributeInfo) {

    }

    @Override
    public void setAttribute(AttributeInfo attributeInfo) {

    }    
}

