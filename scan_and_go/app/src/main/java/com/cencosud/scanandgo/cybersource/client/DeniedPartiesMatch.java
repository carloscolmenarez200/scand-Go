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
import java.util.ArrayList;
import org.ksoap2.serialization.PropertyInfo;

public class DeniedPartiesMatch extends AttributeContainer implements KvmSerializable
{

    
    public String list;
    
    public ArrayList< String> name =new ArrayList<String >();
    
    public ArrayList< String> address =new ArrayList<String >();
    
    public ArrayList< String> program =new ArrayList<String >();
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
        if (info.name.equals("list"))
        {
            if(obj!=null)
            {
                if (obj.getClass().equals(SoapPrimitive.class))
                {
                    SoapPrimitive j =(SoapPrimitive) obj;
                    if(j.toString()!=null)
                    {
                        this.list = j.toString();
                    }
                }
                else if (obj instanceof String){
                    this.list = (String)obj;
                }
            }
            return true;
        }
        if (info.name.equals("name"))
        {
            if(obj!=null)
            {
                if(this.name==null)
                {
                    this.name = new java.util.ArrayList< String>();
                }
                java.lang.Object j =obj;
                String j1= j.toString();
                this.name.add(j1);
            }
            return true;
        }
        if (info.name.equals("address"))
        {
            if(obj!=null)
            {
                if(this.address==null)
                {
                    this.address = new java.util.ArrayList< String>();
                }
                java.lang.Object j =obj;
                String j1= j.toString();
                this.address.add(j1);
            }
            return true;
        }
        if (info.name.equals("program"))
        {
            if(obj!=null)
            {
                if(this.program==null)
                {
                    this.program = new java.util.ArrayList< String>();
                }
                java.lang.Object j =obj;
                String j1= j.toString();
                this.program.add(j1);
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
            return this.list!=null?this.list:SoapPrimitive.NullSkip;
        }
        if(propertyIndex>=1 && propertyIndex < 1+this.name.size())
        {
            java.lang.Object name = this.name.get(propertyIndex-(1));
            return name!=null?name:SoapPrimitive.NullNilElement;
        }
        if(propertyIndex>=1+this.name.size() && propertyIndex < 1+this.name.size()+this.address.size())
        {
            java.lang.Object address = this.address.get(propertyIndex-(1+this.name.size()));
            return address!=null?address:SoapPrimitive.NullNilElement;
        }
        if(propertyIndex>=1+this.name.size()+this.address.size() && propertyIndex < 1+this.name.size()+this.address.size()+this.program.size())
        {
            java.lang.Object program = this.program.get(propertyIndex-(1+this.name.size()+this.address.size()));
            return program!=null?program:SoapPrimitive.NullNilElement;
        }
        return null;
    }


    @Override
    public int getPropertyCount() {
        return 1+name.size()+address.size()+program.size();
    }

    @Override
    public void getPropertyInfo(int propertyIndex, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info)
    {
        if(propertyIndex==0)
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "list";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex>=1 && propertyIndex < 1+this.name.size())
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "name";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex>=1+this.name.size() && propertyIndex < 1+this.name.size()+this.address.size())
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "address";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
        if(propertyIndex>=1+this.name.size()+this.address.size() && propertyIndex < 1+this.name.size()+this.address.size()+this.program.size())
        {
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "program";
            info.namespace= "urn:schemas-cybersource-com:transaction-data-1.146";
        }
    }
    
    @Override
    public void setProperty(int arg0, java.lang.Object arg1)
    {
    }

    
}

