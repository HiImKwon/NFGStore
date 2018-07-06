<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : divineProduct.xsl
    Created on : July 3, 2018, 2:16 PM
    Author     : Bui Quan
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns="http://netbeans.org/divineProduct"
                version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>

    <xsl:template match="/">
        <xsl:element name="divineProduct">
            <xsl:element name="productName">
                <xsl:value-of select="//div[@class='product-layout product-list col-xs-12']//div[@class='product-thumb']//div[@class='caption']//h4//a/text()"/>
            </xsl:element>
            <xsl:element name="price">
                <xsl:value-of select="//div[@class='product-layout product-list col-xs-12']//div[@class='product-thumb']//div[@class='caption']//p[@class='price']/text()"/>
            </xsl:element>
            <xsl:element name="href">
                <xsl:value-of select="//div[@class='product-layout product-list col-xs-12']//div[@class='product-thumb']//div[@class='image']//a/@href"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
