<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : category-list.xsl
    Created on : July 7, 2018, 11:06 AM
    Author     : Bui Quan
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                version="1.0"
                xmlns:ns="http://netbeans.org/DTO">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:apply-templates select="/ns:categoryDTOs"/>
    </xsl:template>

    <xsl:template match="ns:categoryDTOs">
        <xsl:for-each select="ns:category">
            <a href="http://localhost:8084/Crawling_XML/gameListServlet?category={ns:id}">
                <xsl:value-of select="ns:cateName"/>
            </a>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
