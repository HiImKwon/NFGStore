<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : products-top-list.xsl
    Created on : July 7, 2018, 1:40 PM
    Author     : Bui Quan
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns="http://netbeans.org/DTO"
                version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <xsl:apply-templates select="/ns:productDisplays"/>
    </xsl:template>

    <xsl:template match="ns:productDisplays">
        <xsl:for-each select="ns:productList">
            <div>
                <a class="display-product" href="http://localhost:8084/Crawling_XML/gameDetailServlet?productName={ns:productName}">
                    <div class="img">
                        <img src="{ns:avaUrl}" alt="{ns:productName}"/>
                    </div>
                    <div class="price">
                        <xsl:value-of select="ns:cheapestPrice"/>
                    </div>
                </a>
            </div>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
