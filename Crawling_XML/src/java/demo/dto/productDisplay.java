/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.dto;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bui Quan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productDisplay", propOrder = {
    "productName",
    "avaUrl",
    "href",
    "prices",
    "cheapestPrice",
    "cheapestPriceString"
})
public class productDisplay implements Serializable {

    @XmlElement
    private String productName;
    @XmlElement
    private String avaUrl;
    @XmlElement
    private String href;
    @XmlElement
    private List<productPriceDTO> prices;
    @XmlElement
    private float cheapestPrice;
    @XmlElement
    private String cheapestPriceString;

    public productDisplay() {
    }

    public productDisplay(String productName, String avaUrl, String href, List<productPriceDTO> prices, float cheapestPrice) {
        this.productName = productName;
        this.avaUrl = avaUrl;
        this.href = href;
        this.prices = prices;
        this.cheapestPrice = cheapestPrice;
    }

    public String getCheapestPriceString() {
        return cheapestPriceString;
    }

    public void setCheapestPriceString(String cheapestPriceString) {
        this.cheapestPriceString = cheapestPriceString;
    }

    public float getCheapestPrice() {
        return cheapestPrice;
    }

    public void setCheapestPrice(float cheapestPrice) {
        this.cheapestPrice = cheapestPrice;
    }

    public String getAvaUrl() {
        return avaUrl;
    }

    public String getHref() {
        return href;
    }

    public List<productPriceDTO> getPrices() {
        return prices;
    }

    public String getProductName() {
        return productName;
    }

    public void setAvaUrl(String avaUrl) {
        this.avaUrl = avaUrl;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setPrices(List<productPriceDTO> prices) {
        this.prices = prices;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
