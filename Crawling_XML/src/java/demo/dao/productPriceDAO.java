/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.dao;

import demo.dto.productDTO;
import demo.dto.productPriceDTO;
import demo.utils.DBUtilities;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<productPriceDTO> getProductPrice(int productId) throws SQLException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<productPriceDTO> priceNCredit = new ArrayList<productPriceDTO>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT price, creditName FROM tblProductPrice where productId=?";
                ptm = con.prepareStatement(sql);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    Float price = rs.getFloat("price");
                    String creditName = rs.getString("creditName");
                    productPriceDTO dto = new productPriceDTO();
                    dto.setPrice(price);
                    dto.setProductId(productId);
                    dto.setCreditName(creditName);
                    priceNCredit.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return priceNCredit;
    }
}
