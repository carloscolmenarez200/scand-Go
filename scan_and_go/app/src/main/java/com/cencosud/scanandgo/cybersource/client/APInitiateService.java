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

public class APInitiateService extends AttributeContainer implements KvmSerializable
{

    
    public String returnURL;
    
    public String productName;
    
    public String productDescription;
    
    public String reconciliationID;
    
    public String bankID;
    
    public String countryCode;
    
    public String escrowAgreement;
    
    public String languageInterface;
    
    public String intent;
    
    public String successURL;
    
    public String cancelURL;
    
    public String failureURL;
    
    public String overridePaymentMethod;
    
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
        if (info.name.equals("returnURL"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.returnURL = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.returnURL = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("productName"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.productName = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.productName = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("productDescription"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.productDescription = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.productDescription = (String)obj;
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
        if (info.name.equals("bankID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.bankID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.bankID = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("countryCode"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.countryCode = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.countryCode = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("escrowAgreement"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.escrowAgreement = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.escrowAgreement = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("languageInterface"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.languageInterface = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.languageInterface = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("intent"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.intent = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.intent = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("successURL"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.successURL = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.successURL = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("cancelURL"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.cancelURL = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.cancelURL = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("failureURL"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.failureURL = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.failureURL = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("overridePaymentMethod"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.overridePaymentMethod = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.overridePaymentMethod = (String)obj;
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
            return this.returnURL!=null?this.returnURL:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.productName!=null?this.productName:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.productDescription!=null?this.productDescription:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.reconciliationID!=null?this.reconciliationID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.bankID!=null?this.bankID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.countryCode!=null?this.countryCode:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==6)
        {
            return this.escrowAgreement!=null?this.escrowAgreement:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==7)
        {
            return this.languageInterface!=null?this.languageInterface:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==8)
        {
            return this.intent!=null?this.intent:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==9)
        {
            return this.successURL!=null?this.successURL:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==10)
        {
            return this.cancelURL!=null?this.cancelURL:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==11)
        {
            return this.failureURL!=null?this.failureURL:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==12)
        {
            return this.overridePaymentMethod!=null?this.overridePaymentMethod:SoapPrimitive.NullSkip;
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
            info.name = "returnURL";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "productName";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "productDescription";
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
            info.name = "bankID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "countryCode";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==6)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "escrowAgreement";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==7)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "languageInterface";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==8)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "intent";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==9)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "successURL";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==10)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "cancelURL";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==11)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "failureURL";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==12)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "overridePaymentMethod";
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

