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
@XmlType(name = "productPriceDTOs", propOrder = {
    "priceList"
})
@XmlRootElement(name = "productPriceDTOs")
public class productPriceDTOs implements Serializable {

    @XmlElement
    private List<productPriceDTO> priceList;

    public productPriceDTOs() {
    }

    public productPriceDTOs(List<productPriceDTO> priceList) {
        this.priceList = priceList;
    }

    public List<productPriceDTO> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<productPriceDTO> priceList) {
        this.priceList = priceList;
    }

}
