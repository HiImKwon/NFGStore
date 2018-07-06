<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : slideShow.xsl
    Created on : June 21, 2018, 8:01 PM
    Author     : Bui Quan
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns="http://netbeans.org/slideShow"
                version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>

    <xsl:template match="/">
        <xsl:element name="slides">
            <xsl:for-each select="//div[@class='col-lg-12 slick-slider']/div">
                <xsl:element name="slide">
                    <xsl:element name="slideLink">
                        <xsl:value-of select="./a/@href"/>
                    </xsl:element>
                    <xsl:element name="slideImg">
                        <xsl:value-of select="./a/img/@src"/>
                    </xsl:element>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
