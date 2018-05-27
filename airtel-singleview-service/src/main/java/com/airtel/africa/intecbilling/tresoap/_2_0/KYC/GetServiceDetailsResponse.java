/**
 * GetServiceDetailsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

public class GetServiceDetailsResponse  implements java.io.Serializable {
    private com.airtel.africa.intecbilling.tresoap._2_0.KYC.ROWSET[] _return;
    
    private org.apache.axis.client.Call _call;

    public GetServiceDetailsResponse() {
    }

    public GetServiceDetailsResponse(
           com.airtel.africa.intecbilling.tresoap._2_0.KYC.ROWSET[] _return) {
           this._return = _return;
    }


    /**
     * Gets the _return value for this GetServiceDetailsResponse.
     * 
     * @return _return
     */
    public com.airtel.africa.intecbilling.tresoap._2_0.KYC.ROWSET[] get_return() {
        return _return;
    }


    /**
     * Sets the _return value for this GetServiceDetailsResponse.
     * 
     * @param _return
     */
    public void set_return(com.airtel.africa.intecbilling.tresoap._2_0.KYC.ROWSET[] _return) {
        this._return = _return;
    }

    public org.apache.axis.client.Call get_call() {
		return _call;
	}

	public void set_call(org.apache.axis.client.Call _call) {
		this._call = _call;
	}

	private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceDetailsResponse)) return false;
        GetServiceDetailsResponse other = (GetServiceDetailsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this._return==null && other.get_return()==null) || 
             (this._return!=null &&
              java.util.Arrays.equals(this._return, other.get_return())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (get_return() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(get_return());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(get_return(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceDetailsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "getServiceDetailsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_return");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "return"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "ROWSET"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "ROWSET"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
