/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.wongstore;

import demo.dao.categoryDAO;
import demo.dao.proCateDAO;
import demo.dao.productDAO;
import static demo.wongstore.getProduct.productList;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bui Quan
 */
public class categoryToDB {
    
    private String[] categoryList = null;
    
    public void cateToDB()
            throws ClassNotFoundException, SQLException {
        if (productList != null) {
            proCateDAO proCateDAO = new proCateDAO();
            productDAO proDAO = new productDAO();
            
            for (int i = 0; i < productList.size(); i++) {
                int productId = proDAO.getProductId(productList.get(i).getProductName());
                getListCategory(productList.get(i).getCategory());
                for (int j = 0; j < categoryList.length; j++) {
                    proCateDAO.insertToDB(productId, Integer.parseInt(categoryList[j]));
                }
            }
        }
    }
    
    private void getListCategory(String cateList) {
        categoryList = cateList.split(",");
    }
}
