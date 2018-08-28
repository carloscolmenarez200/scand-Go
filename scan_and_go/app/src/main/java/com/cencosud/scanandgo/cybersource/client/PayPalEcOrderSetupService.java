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

public class PayPalEcOrderSetupService extends AttributeContainer implements KvmSerializable
{

    
    public String paypalToken;
    
    public String paypalPayerId;
    
    public String paypalCustomerEmail;
    
    public String paypalDesc;
    
    public String paypalEcSetRequestID;
    
    public String paypalEcSetRequestToken;
    
    public String promoCode0;
    
    public String invoiceNumber;
    
    public String run;
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
        if (inObj.hasAttribute("run"))
        {	
            java.lang.Object j = inObj.getAttribute("run");
            if (j != null)
            {
                run = j.toString();
            }
        }

    }

    
    protected boolean loadProperty(PropertyInfo info,SoapObject soapObject,ExtendedSoapSerializationEnvelope __envelope)
    {
        java.lang.Object obj = info.getValue();
        if (info.name.equals("paypalToken"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.paypalToken = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.paypalToken = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("paypalPayerId"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.paypalPayerId = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.paypalPayerId = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("paypalCustomerEmail"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.paypalCustomerEmail = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.paypalCustomerEmail = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("paypalDesc"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.paypalDesc = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.paypalDesc = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("paypalEcSetRequestID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.paypalEcSetRequestID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.paypalEcSetRequestID = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("paypalEcSetRequestToken"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.paypalEcSetRequestToken = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.paypalEcSetRequestToken = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("promoCode0"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.promoCode0 = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.promoCode0 = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("invoiceNumber"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.invoiceNumber = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.invoiceNumber = (String)obj;
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
            return this.paypalToken!=null?this.paypalToken:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.paypalPayerId!=null?this.paypalPayerId:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.paypalCustomerEmail!=null?this.paypalCustomerEmail:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.paypalDesc!=null?this.paypalDesc:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.paypalEcSetRequestID!=null?this.paypalEcSetRequestID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.paypalEcSetRequestToken!=null?this.paypalEcSetRequestToken:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.promoCode0!=null?this.promoCode0:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==7)
        {
            return this.invoiceNumber!=null?this.invoiceNumber:SoapPrimitive.NullSkip;
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
            info.name = "paypalToken";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "paypalPayerId";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "paypalCustomerEmail";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "paypalDesc";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "paypalEcSetRequestID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "paypalEcSetRequestToken";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "promoCode0";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==7)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "invoiceNumber";
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
            info.name = "run";
            info.namespace= "";
            if(this.run!=null)
            {
                info.setValue(this.run);
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

