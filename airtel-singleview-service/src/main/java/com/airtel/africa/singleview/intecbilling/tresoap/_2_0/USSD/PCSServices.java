/**
 * PCSServices.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD;

public class PCSServices  implements java.io.Serializable {
    private com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSServiceList[] XMLServices;

    public PCSServices() {
    }

    public PCSServices(
           com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSServiceList[] XMLServices) {
           this.XMLServices = XMLServices;
    }


    /**
     * Gets the XMLServices value for this PCSServices.
     * 
     * @return XMLServices
     */
    public com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSServiceList[] getXMLServices() {
        return XMLServices;
    }


    /**
     * Sets the XMLServices value for this PCSServices.
     * 
     * @param XMLServices
     */
    public void setXMLServices(com.airtel.africa.singleview.intecbilling.tresoap._2_0.USSD.CSSServiceList[] XMLServices) {
        this.XMLServices = XMLServices;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PCSServices)) return false;
        PCSServices other = (PCSServices) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.XMLServices==null && other.getXMLServices()==null) || 
             (this.XMLServices!=null &&
              java.util.Arrays.equals(this.XMLServices, other.getXMLServices())));
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
        if (getXMLServices() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getXMLServices());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getXMLServices(), i);
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
        new org.apache.axis.description.TypeDesc(PCSServices.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "pCSServices"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XMLServices");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "XMLServices"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "CSSServiceList"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tresoap.intecbilling.com/2.0/USSD", "SServiceList"));
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
