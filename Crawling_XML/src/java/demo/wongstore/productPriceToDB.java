/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.wongstore;

import demo.dao.productDAO;
import demo.dao.productPriceDAO;
import static demo.wongstore.getProduct.productList;
import java.sql.SQLException;

/**
 *
 * @author Bui Quan
 */
public class productPriceToDB {

    public void productPriceToDB()
            throws ClassNotFoundException, SQLException {
        if (productList != null) {
            productDAO proDAO = new productDAO();
            productPriceDAO priceDAO = new productPriceDAO();
            for (int i = 0; i < productList.size(); i++) {
                Float price = convertToFloat(productList.get(i).getPrice());
                int id = proDAO.getProductId(productList.get(i).getProductName());
                if (price != -99) {
                    //true means already exist
                    if (priceDAO.checkExistence(id, productList.get(i).getCreditName())) {
                        priceDAO.updatePrice(price, productList.get(i).getHref(), id, productList.get(i).getCreditName());
                        System.out.println("update price successful");
                    } else {
                        priceDAO.insertProductPrice(price, id, productList.get(i).getCreditName(), productList.get(i).getHref());
                        System.out.println("insert price");
                    }
                }

            }
        }
    }

    private float convertToFloat(String productPrice) {
        //eliminate . in vietnam currency
        String price = productPrice.replaceAll("\\.", "");

        //replace from vietnam standard to global standard in currency
        price = price.replaceAll(",", "\\.");

        //eliminate VND currency
        price = price.replace("VNĐ", "");
        price = price.replace("VN?", "");
        price = price.trim();

        if (price.equals("")) {
            return -99;
        } else {
            return new Float(Float.parseFloat(price));

        }
    }
}
