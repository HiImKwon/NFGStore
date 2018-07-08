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
@XmlType(name = "categoryDTOs", propOrder = {
    "category"
})
@XmlRootElement(name = "categoryDTOs")
public class categoryDTOs implements Serializable {

    @XmlElement
    private List<categoryDTO> category;

    public categoryDTOs() {
    }
    
    

    public categoryDTOs(List<categoryDTO> category) {
        this.category = category;
    }

    
    public List<categoryDTO> getCategory() {
        return category;
    }

    public void setCategory(List<categoryDTO> category) {
        this.category = category;
    }
    
    
}
