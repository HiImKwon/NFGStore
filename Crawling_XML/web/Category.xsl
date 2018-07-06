<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : Category.xsl
    Created on : June 25, 2018, 1:43 PM
    Author     : Bui Quan
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="http://netbeans.org/Category"
                version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>

    <xsl:template match="/">
        <xsl:element name="categories">
            <xsl:for-each select="//div/nav/div[@id='navbarSupportedContent']/ul/li[2]/div/a">
                <xsl:element name="category">
                    <xsl:value-of select="./text()"/>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
