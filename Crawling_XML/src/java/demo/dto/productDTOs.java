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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Bui Quan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productDTOs", propOrder = {
    "productList"
})
@XmlRootElement(name = "productDTOs")
public class productDTOs implements Serializable {

    @XmlElement
    private List<productDTO> productList;

    public productDTOs() {
    }

    public productDTOs(List<productDTO> productList) {
        this.productList = productList;
    }

    public List<productDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<productDTO> productList) {
        this.productList = productList;
    }

}
