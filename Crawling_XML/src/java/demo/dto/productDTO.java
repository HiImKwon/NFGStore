/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bui Quan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productDTO", propOrder = {
    "productName",
    "avaUrl",
    "creditName",
    "price",
    "href",
    "category"
})
public class productDTO implements Serializable {

    @XmlElement
    private String productName;
    @XmlElement
    private String avaUrl;
    @XmlElement
    private String creditName;
    @XmlElement
    private String price;
    @XmlElement
    private String href;
    @XmlElement
    private String category;

    public productDTO() {
    }

    public productDTO(String productName, String avaUrl, String creditName, String price, String href, String category) {
        this.productName = productName;
        this.avaUrl = avaUrl;
        this.creditName = creditName;
        this.price = price;
        this.href = href;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAvaUrl() {
        return avaUrl;
    }

    public void setAvaUrl(String avaUrl) {
        this.avaUrl = avaUrl;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
