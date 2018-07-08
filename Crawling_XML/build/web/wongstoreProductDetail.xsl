<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : wongstoreProductDetail.xsl
    Created on : July 1, 2018, 7:32 PM
    Author     : Bui Quan
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns="http://netbeans.org/wongstoreProductDetail"
                version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes"/>

    <xsl:template match="/">
        <xsl:element name="detail">
            <xsl:element name="productName">
                <xsl:value-of select="//div[@class='container']//div[@class='row']//div/text()"/>
            </xsl:element>
            <xsl:element name="displayUrl">
                <xsl:value-of select="//div[@class='row info']//div[@class='col-lg-5 price-product']//img/@src"/>
            </xsl:element>
            <xsl:for-each select="//div[@class='container']//div[@class='row album-img']//div[@class='slider-nav']//div[contains(@class,'item')]">
                <xsl:element name="imgUrl">
                    <xsl:value-of select="./img/@src"/>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
