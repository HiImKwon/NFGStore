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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bui Quan
 */
public class productDAO implements Serializable {

    public void insertToDB(String productName, String avaUrl)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblProduct(productName, avatarUrl)"
                        + " VALUES (?,?)";
                ptm = con.prepareStatement(sql);
                ptm.setString(1, productName);
                ptm.setString(2, avaUrl);
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

    public int getProductId(String productName)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT id FROM tblProduct where productName=?";
                ptm = con.prepareStatement(sql);
                ptm.setString(1, productName);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    return id;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return -99;
    }
}
