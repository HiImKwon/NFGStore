/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.wongstore;

import demo.dao.productDAO;
import static demo.wongstore.getProduct.productList;
import java.sql.SQLException;

/**
 *
 * @author Bui Quan
 */
public class productToDB {

    public void productToDB()
            throws SQLException, ClassNotFoundException {
        if (productList != null) {
            productDAO dao = new productDAO();
            for (int i = 0; i < productList.size(); i++) {
                int id = dao.getProductId(productList.get(i).getProductName());
                if (id != -99) {
                    dao.updateProduct(productList.get(i).getHref(), productList.get(i).getAvaUrl(), productList.get(i).getProductName(), id);
                    System.out.println("update product successful");

                } else {
                    dao.insertToDB(productList.get(i).getProductName(), productList.get(i).getAvaUrl(), productList.get(i).getHref());
                    System.out.println("insert product successful");
                }

            }
        }
    }
}
