/**
 * FaultCode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap.fault._2_0;

public class FaultCode implements java.io.Serializable {
    private javax.xml.namespace.QName _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected FaultCode(javax.xml.namespace.QName value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final javax.xml.namespace.QName _value1 = javax.xml.namespace.QName.valueOf("{http://tresoap.intecbilling.com/fault/2.0}ProtocolException");
    public static final javax.xml.namespace.QName _value2 = javax.xml.namespace.QName.valueOf("{http://tresoap.intecbilling.com/fault/2.0}TuxedoException");
    public static final javax.xml.namespace.QName _value3 = javax.xml.namespace.QName.valueOf("{http://tresoap.intecbilling.com/fault/2.0}TREException");
    public static final javax.xml.namespace.QName _value4 = javax.xml.namespace.QName.valueOf("{http://tresoap.intecbilling.com/fault/2.0}ApplicationException");
    public static final FaultCode value1 = new FaultCode(_value1);
    public static final FaultCode value2 = new FaultCode(_value2);
    public static final FaultCode value3 = new FaultCode(_value3);
    public static final FaultCode value4 = new FaultCode(_value4);
    public javax.xml.namespace.QName getValue() { return _value_;}
    public static FaultCode fromValue(javax.xml.namespace.QName value)
          throws java.lang.IllegalArgumentException {
        FaultCode enumeration = (FaultCode)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static FaultCode fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        try {
            return fromValue(javax.xml.namespace.QName.valueOf(value));
        } catch (Exception e) {
            throw new java.lang.IllegalArgumentException();
        }
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_.toString();}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FaultCode.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/fault/2.0", "FaultCode"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
