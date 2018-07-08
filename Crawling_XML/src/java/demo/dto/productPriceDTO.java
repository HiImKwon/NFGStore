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
@XmlType(name = "productPriceDTO", propOrder = {
    "creditName",
    "price",
    "productId",
    "href"
})
public class productPriceDTO implements Serializable {

    @XmlElement
    private String creditName;
    @XmlElement
    private float price;
    @XmlElement
    private int productId;
    @XmlElement
    private String href;

    public productPriceDTO() {
    }

    public productPriceDTO(String creditName, float price, int productId, String href) {
        this.creditName = creditName;
        this.price = price;
        this.productId = productId;
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getCreditName() {
        return creditName;
    }

    public float getPrice() {
        return price;
    }

    public int getProductId() {
        return productId;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}
