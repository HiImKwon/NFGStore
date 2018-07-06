/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.dao;

import demo.utils.DBUtilities;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Bui Quan
 */
public class productPriceDAO implements Serializable {

    public void insertProductPrice(float productPrice, int productId, String creditName)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblProductPrice(price, productId, creditName) VALUES(?,?,?)";
                ptm = con.prepareStatement(sql);
                ptm.setFloat(1, productPrice);
                ptm.setInt(2, productId);
                ptm.setString(3, creditName);
                ptm.executeUpdate();
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
