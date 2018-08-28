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
import java.util.ArrayList;
import org.ksoap2.serialization.PropertyInfo;

public class APOptionsReply extends AttributeContainer implements KvmSerializable
{

    
    public BigInteger reasonCode=BigInteger.ZERO;
    
    public String responseCode;
    
    public String offset;
    
    public String count;
    
    public String totalCount;
    
    public ArrayList< APOptionsOption> option =new ArrayList<APOptionsOption >();
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
        if (info.name.equals("offset"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.offset = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.offset = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("count"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.count = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.count = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("totalCount"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.totalCount = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.totalCount = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("option"))
        {
            if(obj!=null)
            {
                if(this.option==null)
                {
                    this.option = new java.util.ArrayList< APOptionsOption>();
                }
                java.lang.Object j =obj;
                APOptionsOption j1= (APOptionsOption)__envelope.get(j,APOptionsOption.class,false);
                this.option.add(j1);
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
            return this.responseCode!=null?this.responseCode:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==2)
        {
            return this.offset!=null?this.offset:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==3)
        {
            return this.count!=null?this.count:SoapPrimitive.NullSkip;
        }
        if(propertyIndex==4)
        {
            return this.totalCount!=null?this.totalCount:SoapPrimitive.NullSkip;
        }
        if(propertyIndex>=5 && propertyIndex < 5+this.option.size())
        {
            java.lang.Object option = this.option.get(propertyIndex-(5));
            return option!=null?option:SoapPrimitive.NullNilElement;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 5+option.size();
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
            info.name = "responseCode";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==2)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "offset";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==3)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "count";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex==4)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "totalCount";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex>=5 && propertyIndex < 5+this.option.size())
        {
            info.type = APOptionsOption.class;
            info.name = "option";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

    
}

