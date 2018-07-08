/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.dao;

import demo.dto.productDTO;
import demo.dto.productDisplay;
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
public class productDAO implements Serializable {

    public void insertToDB(String productName, String avaUrl, String href)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblProduct(productName, avatarUrl, href)"
                        + " VALUES (?,?,?)";
                ptm = con.prepareStatement(sql);
                ptm.setString(1, productName);
                ptm.setString(2, avaUrl);
                ptm.setString(3, href);
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

    public List<productDisplay> paginationProducts(int proPerPage, int page) throws SQLException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<productDisplay> productList = new ArrayList<productDisplay>();
        int startPoint = 0;
        int endPoint = 0;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                if (page == 1) {
                    startPoint = 1;
                    endPoint = 1 + (proPerPage - 1);
                } else {
                    startPoint = (page - 1) * proPerPage + 1;
                    endPoint = startPoint + proPerPage - 1;
                }
                String sql = "SELECT *\n"
                        + "FROM\n"
                        + "(\n"
                        + "SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS Seq\n"
                        + "FROM tblProduct\n"
                        + ") p\n"
                        + "WHERE Seq BETWEEN ? AND ?";
                ptm = con.prepareStatement(sql);
                ptm.setInt(1, startPoint);
                ptm.setInt(2, endPoint);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("productName");
                    String avaUrl = rs.getString("avatarUrl");
                    String href = rs.getString("href");
                    productDisplay dto = new productDisplay();
                    dto.setAvaUrl(avaUrl);
                    dto.setProductName(productName);
                    dto.setHref(href);
                    productList.add(dto);
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
        return productList;
    }

    public productDisplay getProductDisplay(int productId)
            throws SQLException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        productDisplay product = new productDisplay();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM tblProduct WHERE id=?";
                ptm = con.prepareStatement(sql);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String productName = rs.getString("productName");
                    String href = rs.getString("href");
                    String avaUrl = rs.getString("avatarUrl");
                    product.setProductName(productName);
                    product.setAvaUrl(avaUrl);
                    product.setHref(href);
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
        return product;

    }

    public List<productDTO> getProductList()
            throws SQLException {
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<productDTO> productList = new ArrayList<productDTO>();
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM tblProduct";
                ptm = con.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("productName");
                    String avaUrl = rs.getString("avatarUrl");
                    String href = rs.getString("href");
                    productDTO dto = new productDTO();
                    dto.setAvaUrl(avaUrl);
                    dto.setProductName(productName);
                    dto.setHref(href);
                    productList.add(dto);
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
        return productList;

    }
}
