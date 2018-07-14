<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : filter-list.xsl
    Created on : July 11, 2018, 5:52 PM
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
            <div onclick="getFilter({ns:id},'{ns:cateName}')">
                <xsl:value-of select="ns:cateName"/>
            </div>
            <!--            <option value="{ns:id}">
                <xsl:value-of select="ns:cateName"/>
            </option>-->
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
