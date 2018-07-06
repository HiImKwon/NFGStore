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
public class proCateDAO implements Serializable {

    public void insertToDB(int productId, int categoryId)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblProduct_tblCategory(productId,categoryId) VALUES (?,?)";
                ptm = con.prepareStatement(sql);
                ptm.setInt(1, productId);
                ptm.setInt(2, categoryId);
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
