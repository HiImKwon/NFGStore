<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : Product.xsl
    Created on : June 28, 2018, 10:15 PM
    Author     : Bui Quan
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns="http://netbeans.org/Product"
                version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>

    <xsl:template match="/">
        <xsl:element name="products">
            <xsl:for-each select="//div[@class='row']//div//a">
                <xsl:element name="product">
                    <xsl:element name="href">
                        <xsl:value-of select="./@href"/>
                    </xsl:element>
                    <xsl:element name="avaUrl">
                        <xsl:value-of select=".//div[@class='image']//img/@src"/>
                    </xsl:element>
                    <xsl:element name="price">
                        <xsl:value-of select=".//div[@class='price']//div[@class='final']/text()"/>
                    </xsl:element>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
