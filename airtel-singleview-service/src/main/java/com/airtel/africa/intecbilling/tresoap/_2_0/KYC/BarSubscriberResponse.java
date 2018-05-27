/**
 * BarSubscriberResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.intecbilling.tresoap._2_0.KYC;

public class BarSubscriberResponse  implements java.io.Serializable {
    private com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponseRESULT RESULT;
    
    private org.apache.axis.client.Call _call;

    public BarSubscriberResponse() {
    }

    public BarSubscriberResponse(
           com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponseRESULT RESULT) {
           this.RESULT = RESULT;
    }


    /**
     * Gets the RESULT value for this BarSubscriberResponse.
     * 
     * @return RESULT
     */
    public com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponseRESULT getRESULT() {
        return RESULT;
    }


    /**
     * Sets the RESULT value for this BarSubscriberResponse.
     * 
     * @param RESULT
     */
    public void setRESULT(com.airtel.africa.intecbilling.tresoap._2_0.KYC.BarSubscriberResponseRESULT RESULT) {
        this.RESULT = RESULT;
    }
    
    public org.apache.axis.client.Call get_call() {
		return _call;
	}

	public void set_call(org.apache.axis.client.Call _call) {
		this._call = _call;
	}

	private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BarSubscriberResponse)) return false;
        BarSubscriberResponse other = (BarSubscriberResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RESULT==null && other.getRESULT()==null) || 
             (this.RESULT!=null &&
              this.RESULT.equals(other.getRESULT())));
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
        if (getRESULT() != null) {
            _hashCode += getRESULT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BarSubscriberResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "barSubscriberResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESULT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "RESULT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/KYC", "barSubscriberResponse.RESULT"));
        elemField.setNillable(false);
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
