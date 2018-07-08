/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.utils;

import demo.dto.productPriceDTO;
import java.util.List;

/**
 *
 * @author Bui Quan
 */
public class comparisonUtilities {

    public productPriceDTO comparePrices(List<productPriceDTO> productPriceList) {
        productPriceDTO cheapestPrice = new productPriceDTO();
        cheapestPrice.setPrice(productPriceList.get(0).getPrice());
        for (int i = 1; i < productPriceList.size(); i++) {
            if (cheapestPrice.getPrice() < productPriceList.get(i).getPrice()) {
                cheapestPrice = productPriceList.get(i);
            }
        }
        return cheapestPrice;
    }
}
