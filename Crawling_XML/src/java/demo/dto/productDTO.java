/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.dto;

import java.io.Serializable;

/**
 *
 * @author Bui Quan
 */
public class productDTO implements Serializable {

    private String productName;
    private String avaUrl;
    private String creditName;
    private String price;
    private String href;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public productDTO() {
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
