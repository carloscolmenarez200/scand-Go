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

public class BankTransferRefundService extends AttributeContainer implements KvmSerializable
{

    
    public String bankTransferRequestID;
    
    public String bankTransferRealTimeRequestID;
    
    public String reconciliationID;
    
    public String bankTransferRealTimeReconciliationID;
    
    public String bankTransferRequestToken;
    
    public String bankTransferRealTimeRequestToken;
    
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
        if (info.name.equals("bankTransferRequestID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.bankTransferRequestID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.bankTransferRequestID = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("bankTransferRealTimeRequestID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.bankTransferRealTimeRequestID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.bankTransferRealTimeRequestID = (String)obj;
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
        if (info.name.equals("bankTransferRealTimeReconciliationID"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.bankTransferRealTimeReconciliationID = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.bankTransferRealTimeReconciliationID = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("bankTransferRequestToken"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.bankTransferRequestToken = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.bankTransferRequestToken = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("bankTransferRealTimeRequestToken"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.bankTransferRealTimeRequestToken = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.bankTransferRealTimeRequestToken = (String)obj;
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
            return this.bankTransferRequestID!=null?this.bankTransferRequestID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==1)
        {
            return this.bankTransferRealTimeRequestID!=null?this.bankTransferRealTimeRequestID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.reconciliationID!=null?this.reconciliationID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.bankTransferRealTimeReconciliationID!=null?this.bankTransferRealTimeReconciliationID:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.bankTransferRequestToken!=null?this.bankTransferRequestToken:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==5)
        {
            return this.bankTransferRealTimeRequestToken!=null?this.bankTransferRealTimeRequestToken:SoapPrimitive.NullSkip;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 6;
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "bankTransferRequestID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==1)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "bankTransferRealTimeRequestID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "reconciliationID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "bankTransferRealTimeReconciliationID";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "bankTransferRequestToken";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==5)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "bankTransferRealTimeRequestToken";
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

